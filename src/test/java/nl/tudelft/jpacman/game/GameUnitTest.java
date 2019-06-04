package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the game.Start() method.
 */
class GameUnitTest {
    private Game game;
    private Level level;
    private Player player;
    private PointCalculator pointCalculator;

    /**
     * Setting up the test suite.
     */
    @BeforeEach
    void setGame() {
        level = Mockito.mock(Level.class);
        player = Mockito.mock(Player.class);
        pointCalculator = Mockito.mock(PointCalculator.class);

        game = new SinglePlayerGame(player, level, pointCalculator);
    }

    /**
     * Testing the start method when there is a player alive and there are pellets remaining.
     * This tests the 2nd if statement in the start method.
     */
    @Test
    void testGetLevel() {
        Mockito.when(level.isInProgress()).thenReturn(game.isInProgress());
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(!game.getPlayers().isEmpty());
        Mockito.when(level.remainingPellets()).thenReturn(1);

        game.start();
        assertTrue(game.isInProgress());
    }

    /**
     * Testing the first if statement of the start method.
     */
    @Test
    void testIsInProgress() {
        Mockito.when(level.isInProgress()).thenReturn(true);

        game.start();
        assertFalse(game.isInProgress());
    }
}
