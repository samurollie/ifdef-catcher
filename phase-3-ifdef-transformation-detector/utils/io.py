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
