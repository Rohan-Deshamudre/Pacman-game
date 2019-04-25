# 2.10 Principles of Software Testing

## Why can't we exhaustively test our entire software project? What should we do instead?
There is a limit to the number of inputs and preconditions that can be tested, so it is not possible to test all parameters.
Instead, it is better to test what is important regarding the code and aim for the minimum number of tests required for the desired test coverage.
A typical rule of thumb would be to:
 - Go through the code and cover every line with a test case.
 - Include tests for every variable with extreme inputs and special cases.
 - Include tests for every data flow.

## What is the pesticide paradox about and what does it imply to software testers?
Deb Chaudhary, Sanhita. “DEFECT CLUSTERING AND PESTICIDE PARADOX:” PITS Blog, 31 Mar. 2015, www.pitsolutions.ch/blog/defect-clustering-and-pesticide-paradox/.
When the same tests are repeated over and over again, the same test cases cannot find new bugs in the code. These new bugs could have appeared after changes were applied to the code and the dated tests would pass, despite being incapable of identifying these new bugs, as they would be focusing on solving the old bugs which have already been fixed.

So, in order to verify new bugs or defects, testers should keep updating test cases and new test cases should be added to raise the coverage.

## Why should we automate, as much as possible, the test execution?
“What Is The Benefit of Test Automation and Why Should We Do It?” SmartBear, smartbear.com/solutions/automated-testing/.
Automated tests are able to record and analyze results with a far greater degree of accuracy, lengthy software tests can be done without expensive labor and it is easier to repeat tests as well, like for each release of the software.
Since automation decreases manual effort, it would give the functional engineer/tester additional time to identify new scenarios, update existing test cases and create new software tests which can identify new bugs.
