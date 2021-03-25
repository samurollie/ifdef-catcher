import glob
from os.path import join

def get_output_line(csv_file):
    line = ""
    with open(csv_file, "r") as f:
        line = f.readlines()[1]
        infos = [x.strip() for x in line.strip().split(';')]
        infos_int = infos[1:-2]
        infos_int.append(infos[-1])
        infos_int = [int(x) for x in infos_int]

        projectname = infos[0]
        disciplined_by_overallblocks = float(infos[-2])
        loc,compilationunit,functiontype,siblings,wrapperif,conditionalcase,\
            conditionalelif,parameter,expression,undisciplinedknown,undisciplinedunknown,\
                overallblocks = infos_int
        
        line = ""

        line += projectname + ',,,'

        line += str(loc) + ','
        line += str(overallblocks) + ','
        line += str(round(disciplined_by_overallblocks * 100, 2)) + ','
        line += str(round(100.0 * wrapperif/overallblocks, 2)) + ','
        line += str(round(100.0 * conditionalcase/overallblocks, 2)) + ','
        line += str(round(100.0 * conditionalelif/overallblocks,2)) + ','
        line += str(round(100.0 * parameter/overallblocks,2)) + ','
        line += str(round(100.0 * expression/overallblocks,2)) + ','
        line += str(round(100.0 * undisciplinedunknown/overallblocks,2))

    return line

def get_header_line():
    return 'projectname,version,domain,#LOC,#AA,%disciplined,%IF,%CA,%EI,%PA,%EX,%NC'

def get_csv_files():
    csv_dir = 'csv_files'
    return sorted(glob.glob(join(csv_dir, '*.csv')))

with open('_result.csv', 'w') as f:
    f.write(get_header_line() + '\n')
    
    for csv_file in get_csv_files():
        f.write(get_output_line(csv_file) + '\n')