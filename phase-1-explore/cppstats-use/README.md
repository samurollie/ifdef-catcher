# CPPSTATS test

The folder **fake_project** already have the **cppstats_discipline.csv** file, with the metrics.

The file **cppstats_discipline.csv** have the following columns:

- undisciplinedknown
- undisciplinedunknown
- disciplined/overallblocks
- overallblocks

to get the number of undisciplined and disciplined annotations, compute the calculations:

- number of undisciplined: undisciplinedknown + undisciplinedunknown
- number of disciplined: disciplined/overallblocks * overallblocks
(you can check the number of disciplined with: overallblocks - number of undisciplined)

To run again, first install the cppstats. After that, follow the steps:

1. Edit the **cppstats_input.txt** with the full path of the project **fake_project**
2. Run `cppstats --kind discipline` in this folder (**cppstats-use**)
3. Open the file **cppstats_discipline.csv** inside **fake_project**

Obs: if you need to replace **fake_project** for another project, be sure the new project have **source** folder, with all c files.