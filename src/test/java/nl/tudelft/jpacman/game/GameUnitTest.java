package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
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
     * The first test, testing the if statements.
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
     * The second test to achieve full branch coverage.
     */
    @Test
    void testIsInProgress() {
        Mockito.when(level.isInProgress()).thenReturn(false);

        game.start();
        assertFalse(game.isInProgress());
    }
}
