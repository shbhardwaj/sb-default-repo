Part Two: Soccer League Table

The file football.dat contains the results from the English Premier League for 2001/2. 
The columns labeled ‘F’ and ‘A’ contain the total number of goals scored for and against each team in that season 
(so Arsenal scored 79 goals against opponents, and had 36 goals scored against them). 
Write a program to print the name of the team with the smallest difference in ‘for’ and ‘against’ goals.

Solution: The solution works BUT certainly its NOT the most elegant. Created a small maven based project using java 1.7

Following are some of the assumptions/shortcomings of the solution which can be improved further.

1) Didn't take into account for large files, used MappedByteBuffer, But read the full file in memory in one go. This can be improved for large files.
2) While parsing the data its assumed that the column doesn't interchange otherwise the solution will fail.
3) To find the data line in the dat file regex is used. If regex check fails then exception is thrown except in case of Ignored regex match.
4) Reading of data starts after the "<pre>" is found and stops when "</pre>" tag is found in the file.
5) Full details of the text file is parsed which is not necessary for the calculation of results. Done this if more statistics needed in future. 
6) Documentation is not written as the code is self explanatory.
7) No checking of duplicate data in file is done. Doesn't have any impact as long as the data is same.
8) Log statements are not put in place.

This is a java 1.7, maven based project with Integration tests in "StatisticsCalculator.java" file. 
On command prompt "mvn clean install" this will create the jar and would run the tests in "StatisticsCalculator.java".

In your preferred IDE import this project as maven project and run the test to see the code flow.
