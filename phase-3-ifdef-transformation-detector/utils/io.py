import os
import shutil
import glob

from pathlib import Path
from shutil import copyfile

def create_empty_file(dir):
    file = os.path.join(dir, 'empty')
    with open(file, 'a'):
        os.utime(file, None)

def clear_directory(dir):
    try:
        shutil.rmtree(dir)
    except:
        pass
    Path(dir).mkdir(parents=True, exist_ok=True)

def list_source_files(dir):
    files = []
    for extension in ('c', 'h'):
        files.extend(glob.iglob(os.path.join(dir, '**', '*.' + extension), recursive=True))
    return files

def change_path(path, base_dir, destiny_dir):
    if path.startswith(base_dir):
        return destiny_dir + path[len(base_dir):]
    return path

def path_exists(path):
    return Path(path).exists()

def equals(file1, file2):
    with open(file1, 'r') as f1, open(file2, 'r') as f2:
        content1 = ''.join(''.join(f1.readlines()).split())
        content2 = ''.join(''.join(f2.readlines()).split())
        return content1 == content2

def copy_file(file, destiny_dir):
    copyfile(file, os.path.join(destiny_dir,file.split('/')[-1]))

def write_file(file, content):
    with open(file, 'w') as f:
        f.write(content)