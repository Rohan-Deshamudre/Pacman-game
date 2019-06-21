package nl.tudelft.jpacman.integration;

import nl.tudelft.jpacman.Launcher;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing scenarios 2.1 to 2.5 of User Story 2.
 */
class MovePlayerSystemTest {
    private Launcher launcher;
    private static final int score = 10;

    private Game getGame() {
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
    void shutOffLauncher() {
        launcher.dispose();
    }

    /**
     * Testing that the player consumes pellet.
     */
    @Test
    void testPlayerConsumes() {
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);
        int startPellets = getGame().getLevel().remainingPellets();

        Square startSquare = player.getSquare();

        getGame().move(player, Direction.EAST);
        assertThat(player.getScore()).isEqualTo(score);
        assertThat(player.getSquare()).isNotEqualTo(startSquare);
        assertThat(getGame().getLevel().remainingPellets()).isEqualTo(startPellets - 1);
    }

    /**
     * Testing that the player moves to an empty square.
     */
    @Test
    void testMoveToEmptySquare() {
        launcher.withMapFile("/smallmap.txt").launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        getGame().move(player, Direction.WEST);
        assertThat(player.getScore()).isEqualTo(0);
    }

    /**
     * Testing that the player cant move into a wall.
     */
    @Test
    void moveFails() {
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        Square startSquare = player.getSquare();

        getGame().move(player, Direction.NORTH);
        assertThat(startSquare).isEqualTo(player.getSquare());
    }

    /**
     * Testing that the player dies when he collides with a Ghost.
     */
    @Test
    void playerDies() {
        launcher.withMapFile("/ghostmap.txt").launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        getGame().move(player, Direction.EAST);
        assertThat(player.isAlive()).isFalse();
    }

    /**
     * Testing that the player wins when he completes the game.
     */
    @Test
    void playerWins() {
        launcher.withMapFile("/smallmap.txt").launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        getGame().move(player, Direction.EAST);
        assertThat(getGame().getLevel().remainingPellets()).isEqualTo(0);
        assertThat(player.isAlive()).isTrue();
        assertThat(getGame().isInProgress()).isFalse();
    }
}
