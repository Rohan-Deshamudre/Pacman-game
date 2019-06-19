package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ghfhgfghj.
 */
class GameTest {
    private Launcher launcher;

    /**
     * JHTGFJHGHFGJHFG.
     * @return the game.
     */
    public Game getGame() {
        return launcher.getGame();
    }

    /**
     * Setting up the test suite.
     */
    @BeforeEach
    void setUp() {
        launcher = new Launcher();
    }

    /**
     * Closing launcher after every test.
     */
    @AfterEach
    void closeLauncher() {
        launcher.dispose();
    }

    /**
     * start() should start the game at launch.
     */
    @Test
    void launchStart() {
        launcher.withMapFile("/smallmap.txt").launch();
        getGame().start();
        assertTrue(getGame().isInProgress());
    }

    /**
     * When the game is paused, start() should unpause it.
     */
    @Test
    void pauseGameStart() {
        launchStart();
        getGame().stop();
        assertFalse(getGame().isInProgress());

        getGame().start();
        assertTrue(getGame().isInProgress());
    }

    /**
     * When the game is started, stop() will pause it.
     */
    @Test
    void startGameStop() {
        launchStart();
        getGame().stop();
        assertFalse(getGame().isInProgress());
    }

    /**
     * A hard reset will take it back to launch.
     */
    @Test
    void startGameReset() {
        launcher.withMapFile("/smallmap.txt").launch();
        assertFalse(getGame().isInProgress());
    }

    /**
     * fhgjfhg.
     */
    @Test
    void pauseGameReset() {
        launchStart();
        getGame().stop();
        assertFalse(getGame().isInProgress());

        launcher.withMapFile("/smallmap.txt").launch();
        assertFalse(getGame().isInProgress());
    }

    /**
     * When a player collides with a ghost...
     */
    @Test
    void startGamePlayerCollidesGhost() {
        launcher.withMapFile("/ghostmap.txt").launch();
        getGame().start();
        assertTrue(getGame().isInProgress());

        Player player = getGame().getPlayers().get(0);
        getGame().move(player, Direction.EAST);
        assertFalse(player.isAlive());
    }

    /**
     * When a player wins...
     */
    @Test
    void startGamePlayerWins() {
        launcher.withMapFile("/smallmap.txt").launch();
        getGame().start();
        assertTrue(getGame().isInProgress());

        Player player = getGame().getPlayers().get(0);
        getGame().move(player, Direction.EAST);
        assertThat(getGame().getLevel().remainingPellets()).isEqualTo(0);
        assertTrue(player.isAlive());
        assertFalse(getGame().isInProgress());
    }
}
