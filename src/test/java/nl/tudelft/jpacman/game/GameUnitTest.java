package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
     * Testing the start method when the game is not in progress already.
     */
    @Test
    void testGetLevel() {
        Mockito.when(level.isInProgress()).thenReturn(game.isInProgress());
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(!game.getPlayers().isEmpty());
        Mockito.when(level.remainingPellets()).thenReturn(1);

        game.start();
        Mockito.verify(level).start();
        assertTrue(game.isInProgress());
    }

    /**
     * Testing the start method when the game is already in progress.
     */
    @Test
    void testIsInProgress() {
        testGetLevel();
        Mockito.when(level.isInProgress()).thenReturn(game.isInProgress());

        game.start();
        assertTrue(game.isInProgress());
    }
}
