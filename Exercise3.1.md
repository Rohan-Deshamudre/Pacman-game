# 3 Part 1 : Unit tests
## 3.1 Smoke testing

### Exercise 6
game.Game.levelWon
game.GameFactory.getPlayerFactory

### Exercise 7
Yes it is covered by the smoke test.

>> Explain the failure you see and to what extent you think the test failure can be helpful in fixing the system

Game.move() fails so LauncherSmokeTest.move() also fails --> 5 test cases fail
Game.stop() fails --> 1 test case fails
Very useful it is only.

### Exercise 8
No it's very difficult to understand where the problem is as the coverage remains the same as well as the test
cases.

### Exercise 9
>> Explain how Game, Unit, Board and Level classes are related to each other
