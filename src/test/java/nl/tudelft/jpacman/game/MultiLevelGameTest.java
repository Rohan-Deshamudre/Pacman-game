package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.MultiLevelLauncher;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nl.tudelft.jpacman.board.Direction.EAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Testing the MultiLevel game.
 */
public class MultiLevelGameTest extends GameTest {
    /**
     * @return the game.
     */
    public Game getGame() {
        return getLauncher().getGame();
    }

    /**
     * Setting up using the MultiLevelLauncher.
     */
    @BeforeEach
    public void setup() {
        MultiLevelLauncher launcher = new MultiLevelLauncher();
        setMultiLevelLauncher(launcher);
    }

    /**
     * Making sure that the game stops after you win.
     */
    @Test
    public void winningGame() {
        MultiLevelLauncher launcher = getMultiLevelLauncher();
        List<String> levels = new ArrayList<>();
        levels.add("/smallMap.txt");
        launcher.withMapFile(levels);
        launcher.launch();

        Player player = getGame().getPlayers().get(0);

        //Pacman moves onto the square with the last pellet.
        getGame().getLevel().move(player, EAST);
        //Assert that the game is no longer in progress
        assertThat(getGame().isInProgress()).isFalse();
    }

    /**
     * Assert that current level is not last level, after winning.
     */
    @Test
    public void levelChangeTest() {
        MultiLevelLauncher launcher = getMultiLevelLauncher();
        List<String> levels = new ArrayList<>();
        levels.add("/0.txt");
        levels.add("/1.txt");
        launcher.withMapFile(levels);


        launcher.launch();
        getGame().start();

        Player player = getGame().getPlayers().get(0);

        Level oldLevel = getGame().getLevel();
        //winning the game
        getGame().getLevel().move(player, EAST);
        assertNotEquals(oldLevel, getGame().getLevel());
    }


    /**
     * Assert that game stops after winning all level.
     */
    @Test
    public void lastLevelWinTest() {
        MultiLevelLauncher launcher = getMultiLevelLauncher();
        launcher.withMapFile("/2.txt");
        launcher.launch();
        getGame().start();

        Player player = getGame().getPlayers().get(0);
        getGame().getLevel().move(player, EAST);
        assertFalse(getGame().isInProgress());
    }
}
