import os

from pathlib import Path

OUTPUT_DIR = 'output'
REPORT_PATH = os.path.join(OUTPUT_DIR, 'report.csv')

def run_cppstats(dir):
    return 0 == os.system("cd " + dir + " && cppstats --kind discipline")

def get_results(file):
    disc, undisc = -1, -1
    with open(file, 'r') as f:
        titles = f.readline().split(';')
        fields = f.readline().split(';')
        _map = {}
        for i,title in enumerate(titles):
            _map[title.strip()] = fields[i].strip()
        
        disc = int(_map['compilationunit']) + int(_map['functiontype']) + int(_map['siblings'])
        undisc = int(_map['overallblocks']) - disc

    return disc, undisc

def create_report():
    Path(OUTPUT_DIR).mkdir(parents=True, exist_ok=True)
    with open(REPORT_PATH, 'w') as f:
        f.write('project, filepath_prev, filepath_curr, disc_prev, undisc_prev,' + \
            'disc_curr, undisc_curr')
        f.write('\n')

def write_report(project, filepath_prev, filepath_curr, disc_prev, undisc_prev, \
    disc_curr, undisc_curr):
    with open(REPORT_PATH, 'a') as f:
        f.write(project + ',')
        f.write(filepath_prev + ',')
        f.write(filepath_curr + ',')
        f.write(str(disc_prev) + ',')
        f.write(str(undisc_prev) + ',')
        f.write(str(disc_curr) + ',')
        f.write(str(undisc_curr))
        f.write('\n')