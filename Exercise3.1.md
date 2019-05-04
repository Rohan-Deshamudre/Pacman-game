# 3 Part 1 : Unit tests
## 3.1 Smoke testing

### Exercise 6
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