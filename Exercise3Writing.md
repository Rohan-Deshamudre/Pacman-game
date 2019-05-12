# 3 Part 1 : Unit tests
## 3.1 Smoke testing

### Exercise 6
>>Execute the smoke test, with coverage enabled. Name 2 classes that are not well-tested, and explain why the smoke test does not cover it.

game.Game
- The levelWon method is not tested so there is no way to tell if you won the level or not after collecting all the points.

board.Direction
- The smoke test does not test the direction South.

### Exercise 7
>> Explain the failure you see and to what extent you think the test failure can be helpful in fixing the system
- The smoke test does test the move method but when the line is commented out the test fails and Pacman cannot move around and is stuck in one spot. This shows that the movement is reliant on this one line of code and if there is an error it is hard to detect in which direction the error may be.


### Exercise 8
>>Explain what you see. Was it easy to understand where the problem is?
- When the direction is changed from X to Y, the test fails and can only move upwards and diagonally. This was easy to spot if you run the game with the changed code.

### Exercise 9
>> Explain how Game, Unit, Board and Level classes are related to each other
- The game class is responsible for tracking the process of the game, calculating points Pacman has, moving the player and keeping track of the level and if the level is won or lost.
- This game links to the level class which consists a board with players and Ghosts on it. The level registers a player and the player can only move when the game is in progress. When the game is started, all the NPC's follow their scheduling and when the stop button is pressed they are paused which is also handled in Level.
- Level is linked to board. The board is a grid of squares and is created in the Board class. None of the squares in the board can be null and this class contains methods which give the position of each square.
- The board consists of Squares which have units on them. The unit class contains methods that tells which square the unit is occupying, which direction it is facing, the sprite of the unit and a utility method for the ghost AI.


## 3.4 Understanding your tests

###Exercise 14
>> What can you do to avoid code repetition during the Arrange part of the unit tests?
- In the Arrange stage, the necessary setup for the test is done. The objects of the target class, mock setup (if required) and other variable initialization required is done at this stage. In order to avoid code repetition, in the arrange stage, all the code that is in common for all the tests targeting a single class can be put in a @BeforeEach method so that this code would not have to be repeated in each test and the @BeforeEach runs it automatically before each test.

###Exercise 15
>>What are the advantages of using "clean instances". Eg: setUp() method
- Having a setup Method at the start that runs before each test avoid having to instantiate the same things in each of the tests separately. This avoid code duplication and keeps all the common code of all the tests in one place. The execution times also reduces. 

###Exercise 16
>>assertEquals(1,a) ; or assertTrue(1==a). Which one is better and discuss the differences.
- The assertEquals(1,a) is better because it provides better error information compared to assertTrue. If assertEquals(1,a) fails, it displays the expected result which is 1 and the actual result whereas assertTrue cannot give this information and only whether it was true or false.

###Exercise 17
>>Do the large private tests in MapParser need specific tests for them?
- The private methods should not need specific tests, rather its effects on the public methods that call them need to be tested. Unit tests are clients of the object under test, much like the other classes in the code that are dependent on the object.  The test should only be accessing the class’ public interface. If an object is hard to test via its public interface, it is going to be hard to use in the production code.
