package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
     * The first test, testing the ...
     */
    @Test
    void testGetLevel() {
        Mockito.when(level.isInProgress()).thenReturn(game.isInProgress());
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(!game.getPlayers().isEmpty());
        Mockito.when(level.remainingPellets()).thenReturn(0);

        //

//        Mockito.verify(level).isAnyPlayerAlive();
//        Mockito.verify(level).remainingPellets();
        game.start();

    }
}
