import json
import os

from project import Project
from git.utils import *

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

def main():
    projects = get_all_projects()
    
    while len(projects):
        project = projects.pop()
        
        # clone versions
        clone(project.prev, DIR_PROCESSING_PREV_VERSION)
        clone(project.curr, DIR_PROCESSING_CURR_VERSION)

main()