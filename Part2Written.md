Exercise 9
- Flaky tests are tests that can both pass and randomly fail with the same code. With regards to the smoke test, ...
- A test can also become flaky when it grows too large in size, there are other threads or tests running in parallel that can disrupt its flow,
it is dependent on certain 3rd party systems, there are bugs in the testing framework, certain network issues etc.
- Thus, developers should aim to write tests that are small in size, keep tests isolated so they can execute without interfering each other, limit
calls to external systems and develop tools that can monitor flakiness in tests and file bugs for developers to reduce the flakiness.

Source:
Gang, The Code. “Flaky Tests - A War That Never Ends.” Hacker Noon, Hacker Noon, 5 Dec. 2017, 
<https://hackernoon.com/flaky-tests-a-war-that-never-ends-9aa32fdef359>
Stričević, Nebojša. “How to Deal With and Eliminate Flaky Tests.” Semaphore, 27 May 2015, 
<https://semaphoreci.com/community/tutorials/how-to-deal-with-and-eliminate-flaky-tests>
Micco, John. “Flaky Tests at Google and How We Mitigate Them.” Google Testing Blog, 27 May 2016, 
<https://testing.googleblog.com/2016/05/flaky-tests-at-google-and-how-we.html>

Exercise 10
- 100% code coverage is a good thing to have as all areas of the program are exercised by a set of test cases and can indirectly indicate the quality of the product.
However, having 100% code coverage reflects successful coverage of only the test code that has been written - thus, it does not ensure that the program is fully bug-free.
Thus, if a certain required function has not been implemented, structural testing methods (such as code coverage) are quite limited as they only look at the code structure already there.
- It is important that a variety of testing methods and metrics are used, aside from code coverage, to ensure that the code is tested as well as possible and there are no bugs.

Exercise 11
- While mocks are quite advantageous, they add extra complexity to the code and also results in tests which are difficult to reuse if you are planning to write a large number
of them. Thus, mock methods often end up being repeated for each test and you end up with objects communicating in complex ways, as opposed to a function without dependencies.

Source: Llopis, Noel. “Mock Objects: Friends Or Foes?” Games from Within, 26 July 2010, 
<http://gamesfromwithin.com/mock-objects-friends-or-foes>

Exercise 12
- A test can execute slow if it initially tests the individual components (unit test) but then grows to test, say, the communication between those individual
components (integration testing). In fact, it is often found that slow tests tend to be integration and/or UI-based (acceptance) testing.
- It is important to keep the tests fast as developers need quick feedback on each commit; otherwise, developers will see no point in running them.
- The best way to mitigate this is to isolate these tests in their own test suite - they should then be made/re-written to test business logic without unnecessary 
usage of the database or other storage systems.

Source:
Serna, Benito. “Slow Tests?... How to Remove the Database from Your Tests and Make Them Fast!” Benito Serna, 
<https://bhserna.com/slow-rails-test-suite-how-to-remove-the-database-from-your-tests-and-make-them-fast.html>
Kapelonis, Kostis. “Software Testing Anti-Patterns.” Codepipes Blog, 21 Apr. 2018, 
<http://blog.codepipes.com/testing/software-testing-antipatterns.html#anti-pattern-7---having-flaky-or-slow-tests>

Exercise 13
- Mocking is good to use when working with code which cannot be used in isolation and allows for the functionality of the code to be tested in isolation. Thus, unit tests that interface
with the code can still be carried out without having to restart the project with a new codebase. However, if the code allows for individual component testing, then it is better to use
the state and not mock as it will add unnecessary complexity and also will not be reusable as the project is scaled up with more tests being written.

Exercise 14
- When Pacman is moving West and has eaten 15 pellets, the score starts alternating between a large negative number and the positive score for each extra pellet eaten.
- In some situations, after eating 28 pellets, pacman starts to lose 15 points from the score for each pellet eaten.
- Sometimes, Pacman randomly dies.
- Sometimes the game randomly crashes.


Exercise 15

Graph of normal behaviour
![](doc/img/Security Testing/Default.png)

Graph of abnormal behaviour
![](doc/img/Security Testing/Amazing 1.png)
