package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Testing collisions between the units.
 */
class PlayerCollisionsTest {
    private CollisionMap playerCollisions;
    private PointCalculator pointCalculator;

    /**
     * Getter for the Point Calculator.
     * @return the point calculator
     */
    public PointCalculator getPointCalculator() {
        return pointCalculator;
    }

    /**
     * Setter for the Point Calculator.
     * @param pointCalculator the point calculator
     */
    public void setPointCalculator(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
    }

    /**
     * Getter for the Player Collisions.
     * @return the player collisions
     */
    public CollisionMap getPlayerCollisions() {
        return playerCollisions;
    }

    /**
     * Setter for the Player Collisions which will be used in Default
     * Collision Map Test.
     * @param collisions the default player interaction map.
     */
    public void setPlayerCollisions(DefaultPlayerInteractionMap collisions) {
        this.playerCollisions = collisions;
    }

    /**
     * Setting up the test suite.
     */
    @BeforeEach
    void setPlayerCollisions() {
        pointCalculator = Mockito.mock(PointCalculator.class);

        playerCollisions = new PlayerCollisions(pointCalculator);
    }

    /**
     * Testing that player collides with ghost.
     */
    @Test
    void testPlayerCollidesGhost() {
        Player player = Mockito.mock(Player.class);
        Ghost ghost = Mockito.mock(Ghost.class);

        playerCollisions.collide(player, ghost);

        Mockito.verify(pointCalculator).collidedWithAGhost(player, ghost);
        Mockito.verify(player).setAlive(false);
        Mockito.verify(player).setKiller(ghost);
    }

    /**
     * Testing that player collides with pellet.
     */
    @Test
    void testPlayerCollidesPellet() {
        Player player = Mockito.mock(Player.class);
        Pellet pellet = Mockito.mock(Pellet.class);

        playerCollisions.collide(player, pellet);

        Mockito.verify(pointCalculator).consumedAPellet(player, pellet);
        Mockito.verify(pellet).leaveSquare();
    }

    /**
     * Testing that ghost collides with player.
     */
    @Test
    void testGhostCollidesPlayer() {
        Ghost ghost = Mockito.mock(Ghost.class);
        Player player = Mockito.mock(Player.class);

        playerCollisions.collide(ghost, player);

        Mockito.verify(pointCalculator).collidedWithAGhost(player, ghost);
        Mockito.verify(player).setAlive(false);
        Mockito.verify(player).setKiller(ghost);
    }

    /**
     * Testing that two ghosts collide with one another.
     */
    @Test
    void testGhostCollidesGhost() {
        Ghost ghost1 = Mockito.mock(Ghost.class);
        Ghost ghost2 = Mockito.mock(Ghost.class);

        playerCollisions.collide(ghost1, ghost2);

        Mockito.verifyZeroInteractions(ghost1, ghost2);
    }

    /**
     * Testing that ghost collides with pellet.
     */
    @Test
    void testGhostCollidesPellet() {
        Ghost ghost = Mockito.mock(Ghost.class);
        Pellet pellet = Mockito.mock(Pellet.class);

        playerCollisions.collide(ghost, pellet);

        Mockito.verifyZeroInteractions(ghost, pellet);
    }

    /**
     * Testing that pellet collides with player.
     */
    @Test
    void testPelletCollidesPlayer() {
        Pellet pellet = Mockito.mock(Pellet.class);
        Player player = Mockito.mock(Player.class);

        playerCollisions.collide(pellet, player);

        Mockito.verify(pointCalculator).consumedAPellet(player, pellet);
        Mockito.verify(pellet).leaveSquare();
    }

    /**
     * Testing that pellet collides with ghost.
     */
    @Test
    void testPelletCollidesGhost() {
        Pellet pellet = Mockito.mock(Pellet.class);
        Ghost ghost = Mockito.mock(Ghost.class);

        playerCollisions.collide(pellet, ghost);

        Mockito.verifyZeroInteractions(pellet, ghost);
    }
}
