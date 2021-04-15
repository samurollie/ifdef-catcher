import json
import os
import shutil

from pathlib import Path

# CONSTANTS

DOMAIN_MAP = "domain_map.json"

VERSION_0_STRING_KEY = 'previous-version'
VERSION_1_STRING_KEY = 'current-version'
VERSION_0_URL_KEY = 'previous-url'
VERSION_1_URL_KEY = 'current-url'
DOMAIN_KEY = 'domain'

DESTINATION_FOLDER = 'csv_files'
REPOS_FOLDER = 'repos'
CPPSTATS_INPUT_TXT = os.path.join(REPOS_FOLDER, 'cppstats_input.txt')
VERSION_0_FOLDER = os.path.join(REPOS_FOLDER, 'version_0')
VERSION_1_FOLDER = os.path.join(REPOS_FOLDER, 'version_1')
VERSION_0_SOURCE_DIR = os.path.join(VERSION_0_FOLDER, 'source')
VERSION_1_SOURCE_DIR = os.path.join(VERSION_1_FOLDER, 'source')

# FUNCTIONS

def get_projects(domain_map):
    with open(domain_map, 'r') as f:
        return json.load(f)

def download(url, destiny):
    if 'google' not in url:
        # wget
        pass # TODO implement
    else:
        # gdown
        pass # TODO implement

def extract(files, output_dir):
    # TODO implement
    pass

def write_path_to_cppstats(path):
    # TODO implement
    pass

def run_preparation():
    # TODO implement
    pass

def run_filter():
    # TODO implement
    pass

def run_analysis():
    # TODO implement
    pass

def get_results():
    # TODO implement
    pass

def run(projects):
    projects_stack = list(projects.keys())
    
    while len(projects_stack) != 0:
        project = projects[projects_stack.pop()]

        # create version 0 and version 1 folders
        Path(VERSION_0_SOURCE_DIR).mkdir(parents=True, exist_ok=True)
        Path(VERSION_1_SOURCE_DIR).mkdir(parents=True, exist_ok=True)

        download(project[VERSION_0_URL_KEY], VERSION_0_SOURCE_DIR)
        download(project[VERSION_1_URL_KEY], VERSION_1_SOURCE_DIR)

        files_version_0 = [f for f in os.listdir(VERSION_0_SOURCE_DIR) if os.path.isfile(os.path.join(VERSION_0_SOURCE_DIR, f))]
        files_version_1 = [f for f in os.listdir(VERSION_1_SOURCE_DIR) if os.path.isfile(os.path.join(VERSION_1_SOURCE_DIR, f))]
        extract(files_version_0, VERSION_0_SOURCE_DIR)
        extract(files_version_1, VERSION_1_SOURCE_DIR)

        write_path_to_cppstats(VERSION_0_FOLDER)
        write_path_to_cppstats(VERSION_1_FOLDER)

        # stage 1
        run_preparation()

        # stage 2
        run_filter()

        # stage 3
        run_analysis()

        # get results
        get_results()

        # delete version 0 and 1 folders
        shutil.rmtree(VERSION_0_FOLDER)
        shutil.rmtree(VERSION_1_FOLDER)

        # TODO remove later
        break

# MAIN

if __name__ == "__main__":
    projects = get_projects(DOMAIN_MAP)
    run(projects)