Exercise 7
- There is now 100% coverage of all methods and lines in the PlayerCollisions and DefaultPlayerInteractionMap classes which were previously
completely untested. As a result, all the possible collisions between units - player, ghost and pellet - have been covered as per the decision 
table and thus, we have achieved 94% line coverage of the CollisionInteractionMap, indicating high coverage of the overall collision functionality.
- The collision functionality, in CollisionInteractionMap, that is unchecked involves the handling of the collision between two colliding parties, if the handler is null or if the
collider is null. Moreover, it's not tested that when there are interfaces that the class inherits from, they will get added to the list of classes and interfaces that is output.
