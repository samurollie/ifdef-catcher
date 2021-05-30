import os
import shutil
from pathlib import Path

def create_empty_file(dir):
    file = os.path.join(dir, 'empty')
    with open(file, 'a'):
        os.utime(file, None)

def clear_directory(dir):
    shutil.rmtree(dir)
    Path(dir).mkdir(parents=True, exist_ok=True)

def list_source_files(dir):
    # TODO implement list_source_files(dir)
    pass

def change_path(path, base_dir, destiny_dir):
    # TODO implement change_path(path, base_dir, destiny_dir)
    pass

def path_exists(path):
    # TODO implement path_exists(path)
    pass

def equals(file1, file2):
    # TODO implement equals(file1, file2)
    pass

def copy_file(file, destiny_dir):
    # TODO implement copy_file(file, destiny_dir)
    pass

def write_file(file, content):
    with open(file, 'w') as f:
        f.write(content)