# 2.10 Principles of Software Testing

## Why can't we exhaustively test our entire software project? What should we do instead?
There is a limit to the number of inputs and preconditions that can be tested, so it is not possible to test all the parameters out there.
Instead, it is better to test what is important regarding the code and aim for the minimum number of tests required for the desired test coverage.
A typical rule of thumb would be to:
 - Go through the code and cover all the boundaries with test cases.
 - Include tests for every variable with extreme inputs and special cases which can lead to errors.
 - Include tests for every data flow.

## What is the pesticide paradox about and what does it imply to software testers?
When the same tests are used over and over again to fix bugs, they leave residues that lead to the same test cases becoming ineffective against new bugs. 
These new bugs could have appeared after changes were applied to the code and the dated tests would pass, despite being incapable of identifying these new bugs, as they would be focusing on solving the old bugs which have already been fixed.
So, in order to verify new bugs or defects, testers should keep updating test cases and new test cases should be added to raise the coverage (Deb Chaudhary, 2015).

## Why should we automate, as much as possible, the test execution?
Automated tests are able to record and analyze results with a far greater degree of accuracy, lengthy software tests can be done without expensive labor and it is easier to replicate the tests as well, like for each release of the software.
Since automation decreases manual effort, it would provide the functional engineer/tester with additional time to identify new scenarios, update existing test cases and create new software tests which can identify new bugs.

# References
Deb Chaudhary, Sanhita. “DEFECT CLUSTERING AND PESTICIDE PARADOX:” PITS Blog, 31 Mar. 2015, <www.pitsolutions.ch/blog/defect-clustering-and-pesticide-paradox/>.
