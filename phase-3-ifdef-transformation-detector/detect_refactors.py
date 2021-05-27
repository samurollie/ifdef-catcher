import json
import os

from project import Project
from gitrepo.utils import *
from utils.io import *

PROJECTS_JSON = "domain_map.json"

DIR_PROCESSING_PREV_VERSION = os.path.join('processing', 'prev_ver')
DIR_PROCESSING_CURR_VERSION = os.path.join('processing', 'curr_ver')

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

def clone_tag(base_url, tag, destiny):
    repo = clone(base_url, destiny)
    repo.git.checkout(tag)

def main():
    projects = get_all_projects()
    
    while len(projects):
        project = projects.pop()

        clear_directories()
        
        # clone versions
        clone_tag(project.base_url, project.tag_prev, DIR_PROCESSING_PREV_VERSION)
        clone_tag(project.base_url, project.tag_curr, DIR_PROCESSING_CURR_VERSION)

        # list all files of prev dir
        # compare each file of prev with curr
        #   skip equals and if not have same path
        #   run cppstats on each file and get metrics
        #   fill csv file (project, commit init, commit end, filepath, disc 1, und 1, disc 2, und 2)
        
        # TODO remove later
        break
    
    clear_directories()
    create_empty_files()

main()