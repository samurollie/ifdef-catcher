import json
import os

from project import Project
from gitrepo.utils import *
from utils.io import *
from cppstats.main import *

PROJECTS_JSON = "domain_map.json"

PROCESSING_DIR = 'processing'
DIR_PROCESSING_PREV_VERSION = os.path.join(PROCESSING_DIR, 'prev_ver')
DIR_PROCESSING_CURR_VERSION = os.path.join(PROCESSING_DIR, 'curr_ver')
CPPSTATS_BASE = os.path.join(PROCESSING_DIR, 'cppstats_base')
DIR_CPPSTATS_PROCESSING = os.path.join(CPPSTATS_BASE, 'source')
CPPSTATS_INPUT_PATH = os.path.join(PROCESSING_DIR, 'cppstats_input.txt')
CPPSTATS_RESULT_FILE = os.path.join(CPPSTATS_BASE, 'cppstats_discipline.csv')

def get_all_projects():
    project_list = []

    with open(PROJECTS_JSON, 'r') as f:
        data = json.load(f)
    
    for key in data.keys():
        proj = Project.get_instance(key, data[key])
        if proj == None:
            continue
        project_list.append(proj)
    
    return project_list

def clear_directories():
    clear_directory(DIR_PROCESSING_CURR_VERSION)
    clear_directory(DIR_PROCESSING_PREV_VERSION)

def create_empty_files():
    create_empty_file(DIR_PROCESSING_PREV_VERSION)
    create_empty_file(DIR_PROCESSING_CURR_VERSION)

def clear_cppstats_processing():
    clear_directory(CPPSTATS_BASE)
    clear_directory(DIR_CPPSTATS_PROCESSING)
    create_empty_file(DIR_CPPSTATS_PROCESSING)

def clone_tag(base_url, tag, destiny):
    repo = clone(base_url, destiny)
    repo.git.checkout(tag)

def main():
    write_file(CPPSTATS_INPUT_PATH, os.path.abspath(CPPSTATS_BASE))
    projects = get_all_projects()
    
    while len(projects):
        project = projects.pop()

        clear_directories()
        
        # clone versions
        clone_tag(project.base_url, project.tag_prev, DIR_PROCESSING_PREV_VERSION)
        clone_tag(project.base_url, project.tag_curr, DIR_PROCESSING_CURR_VERSION)

        # list all files of prev dir
        for file in list_source_files(DIR_PROCESSING_PREV_VERSION):
            # compare each file of prev with curr
            curr_path = change_path(file, DIR_PROCESSING_PREV_VERSION, DIR_PROCESSING_CURR_VERSION)
            #   skip equals and if not have same path
            if not path_exists(curr_path) or equals(file, curr_path):
                continue
            #   run cppstats on each file and get metrics
            clear_cppstats_processing()
            copy_file(file, DIR_CPPSTATS_PROCESSING)
            run_cppstats(PROCESSING_DIR)
            disc1, undisc1 = get_results(CPPSTATS_RESULT_FILE)

            clear_cppstats_processing()
            copy_file(curr_path, DIR_CPPSTATS_PROCESSING)
            run_cppstats(PROCESSING_DIR)
            disc2, undisc2 = get_results(CPPSTATS_RESULT_FILE)
            # write report
            write_report(project, file, curr_path, disc1, undisc1, disc2, undisc2)
        
        # TODO remove later
        break
    
    clear_directories()
    clear_cppstats_processing()
    create_empty_files()

main()