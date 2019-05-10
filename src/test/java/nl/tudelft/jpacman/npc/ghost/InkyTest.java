package nl.tudelft.jpacman.npc.ghost;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Write at least 5 junit tests for the nextAiMove() method.
 * 2 good weather tests and 3 bad weather tests.
 * Inky takes into consideration : Blinky's location, 2 spaces ahead of Pac-Man
 * Draws a line between Blinky & 2 spaces ahead of Pac-Man & doubles it.
 * Inky & Blinky < Pac-Man      -Inky follows Blinky
 * Blinky < Pac-Man < Inky      -Inky usually moves away
 */
class InkyTest {
    private Ghost inky;
    private Ghost blinky;
    private List<Ghost> ghosts;

    private LevelFactory lvlFactory;
    private BoardFactory boardFactory;
    private GhostFactory ghostFactory;

    private GhostMapParser ghostMapParser;
    private List<String> map;

    private PlayerFactory playerFactory;
    private Player player;

    /**
     * Setting up the ghostMapParser, the ghosts and the player.
     */
    @BeforeEach
    void setUp() {
        //Instantiating the creators
        PacManSprites sprites = new PacManSprites();
        ghostFactory = new GhostFactory(sprites);
        boardFactory = new BoardFactory(sprites);
        lvlFactory = new LevelFactory(sprites, ghostFactory, mock(PointCalculator.class));
        ghostMapParser = new GhostMapParser(lvlFactory, boardFactory, ghostFactory);

        //Creating the ghosts - Inky & Blinky
        ghosts = new ArrayList<>();
        inky = ghostFactory.createInky();
        blinky = ghostFactory.createBlinky();
        ghosts.add(inky);
        ghosts.add(blinky);

        boardFactory.createGround().isAccessibleTo(inky);
        inky.occupy(boardFactory.createGround());

        //Creating the player - PacMan
        playerFactory = new PlayerFactory(sprites);
        player = playerFactory.createPacMan();
        player.setDirection(Direction.WEST);
    }

    /**
     * Testing the condition 'blinky == null || player == null' - BAD weather.
     * The PLAYER is not on the level so findNearest will return null.
     */
    @Test
    void testPlayerNull() {
        map = new ArrayList<>();
        map.add("#############");
        map.add("#I         B#");
        map.add("#############");

        Level level = ghostMapParser.parseMap(map);
        inky = Navigation.findUnitInBoard(inky.getClass(), level.getBoard());
        blinky = Navigation.findUnitInBoard(blinky.getClass(), level.getBoard());
        assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
    }

    /**
     * Testing the condition 'blinky == null || player == null' - BAD weather.
     * The ghost BLINKY is not on the level so findNearest will return null.
     */
    @Test
    void testBlinkyNull() {
        map = new ArrayList<>();
        map.add("#############");
        map.add("#P         I#");
        map.add("#############");

        Level level = ghostMapParser.parseMap(map);
        level.registerPlayer(player);
        inky = Navigation.findUnitInBoard(inky.getClass(), level.getBoard());
        assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
    }
}
