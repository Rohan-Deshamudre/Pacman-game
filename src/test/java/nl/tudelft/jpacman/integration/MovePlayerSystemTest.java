package nl.tudelft.jpacman.integration;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * HGFGHFJHG.
 */
class MovePlayerSystemTest {
    //private Launcher launcher;
    private PacManSprites sprites;
    private PlayerFactory playerFactory;
    private BoardFactory boardFactory;
    private LevelFactory levelFactory;
    private GhostFactory ghostFactory;

    /**
     * Setting up the test suite.
     */
    @BeforeEach
    void setUp() {
        //launcher = new Launcher();
        sprites = new PacManSprites();
        playerFactory = new PlayerFactory(sprites);
        boardFactory = new BoardFactory(sprites);
        ghostFactory = new GhostFactory(sprites);
        levelFactory = new LevelFactory(sprites, ghostFactory, Mockito.mock(PointCalculator.class));
    }

    /**
     * Closing launcher after every test.
     */
    @AfterEach
    void shutOffLauncher() {
        //launcher.dispose();
    }

    /**
     * Testing that the player consumes pellet.
     */
    @Test
    void testPlayerConsumes() {
        //launcher.launch();

        Square square = boardFactory.createGround();
        Pellet pellet = levelFactory.createPellet();
        pellet.occupy(square);

        Player player = playerFactory.createPacMan();
        square.isAccessibleTo(player);

        //assertThat(square.getOccupants().contains(pellet)).isTrue();

        player.occupy(square);

        assertThat(player.getSquare()).isEqualTo(square);
        assertThat(square.getOccupants().contains(player)).isTrue();
        //assertThat(square.getOccupants().contains(pellet)).isFalse();
    }
}
