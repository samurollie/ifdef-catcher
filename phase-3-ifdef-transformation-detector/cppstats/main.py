import os

from pathlib import Path

OUTPUT_DIR = 'output'
REPORT_PATH = os.path.join(OUTPUT_DIR, 'report.csv')

def run_cppstats(dir):
    # TODO implement run_cppstats(dir)
    pass

def get_results(file):
    # TODO implement get_results(file) -> disc, undisc
    pass

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