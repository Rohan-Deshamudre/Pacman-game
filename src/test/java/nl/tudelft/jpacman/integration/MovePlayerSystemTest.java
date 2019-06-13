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
import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;

/**
 * HGFGHFJHG.
 */
class MovePlayerSystemTest {
    private Launcher launcher;


    private Game getGame(){
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


        getGame().move(player,Direction.EAST);
        assertThat(player.getScore()).isEqualTo(10);
        assertThat(player.getSquare()).isNotEqualTo(startSquare);
        assertThat(getGame().getLevel().remainingPellets()).isEqualTo(startPellets-1);

    }


    /**
     * Testing that the player moves to an empty square
     */
    @Test
    void testMoveToEmptySquare(){
        launcher.withMapFile("/emptysquaremap.txt").launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        getGame().move(player,Direction.EAST);
        assertThat(player.getScore()).isEqualTo(0);
    }

    /**
     * Testing that the player cant move into a wall
     */
    @Test
    void moveFails(){
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        Square startSquare = player.getSquare();

        getGame().move(player,Direction.NORTH);
        assertThat(startSquare).isEqualTo(player.getSquare());

    }

    /**
     *
     */
    @Test
    void playerDies(){
        launcher.withMapFile("/ghostmap.txt").launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        getGame().move(player,Direction.EAST);
        assertThat(player.isAlive()).isFalse();


    }


}
