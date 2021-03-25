projects = {}

with open('result.csv', 'r') as f:
    skip_line = True
    for line in f:
        if skip_line:
            skip_line = False
            continue
        line = line.strip()
        projectname = line.split(',')[0]
        if projectname not in projects:
            projects[projectname] = [line]
        else:
            projects[projectname].append(line)

with open('compare.csv', 'w') as f:
    f.write('projectname,diff loc,diff blocks,diff % disciplined,diff disciplined\n')
    for project in projects:
        if len(projects[project]) < 2:
            continue
        first, second = ['','']
        if int(projects[project][0].split(',')[3]) < int(projects[project][1].split(',')[3]):
            first = projects[project][0].split(',')
            second = projects[project][1].split(',')
        else:
            first = projects[project][1].split(',')
            second = projects[project][0].split(',')
        diff_loc = int(second[3]) - int(first[3])
        diff_blocks = int(second[4]) - int(first[4])
        diff_disciplined = float(second[5]) - float(first[5])
        diff_n_disciplined = int(second[-1]) - int(first[-1])

        f.write(project + ',' + str(diff_loc) + ',' + str(diff_blocks) + ',' + \
            str(round(diff_disciplined,2)) + ',' + str(diff_n_disciplined) + '\n')