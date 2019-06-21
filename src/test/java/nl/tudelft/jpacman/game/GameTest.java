package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the Game class using state transition tree.
 */
class GameTest {
    private Launcher launcher;

    /**
     * Getter for the game.
     * @return the game.
     */
    public Game getGame() {
        return launcher.getGame();
    }

    /**
     * Getter for the level.
     * @return the level.
     */
    public Level getLevel() {
        return launcher.getGame().getLevel();
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
     * Testing game when a player collides with a ghost.
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
     * Testing the game when the player wins.
     */
    @Test
    void startGamePlayerWins() {
        launchStart();
        Player player = getGame().getPlayers().get(0);
        getGame().move(player, Direction.EAST);

        assertThat(getLevel().remainingPellets()).isEqualTo(0);
        assertTrue(player.isAlive());
        assertFalse(getGame().isInProgress());
    }
}
