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
 * Inky takes into consideration : Blinky's location, 2 spaces ahead of Pac-Man
 * It draws a line between Blinky & 2 spaces ahead of Pac-Man & doubles it.
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
        inky.occupy(boardFactory.createGround());

        //Creating the player - PacMan
        playerFactory = new PlayerFactory(sprites);
        player = playerFactory.createPacMan();
    }

    /**
     * Testing the condition 'blinky == null || player == null'.
     * The PLAYER is not on the level so findNearest will return null
     * This is a bad weather test
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
     * Testing the condition 'blinky == null || player == null'.
     * The ghost BLINKY is not on the level so findNearest will return null
     * This is another bad weather test
     */
    @Test
    void testBlinkyNull() {
        map = new ArrayList<>();
        map.add("#############");
        map.add("#P         I#");
        map.add("#############");

        Level level = ghostMapParser.parseMap(map);
        level.registerPlayer(player);
        player.setDirection(Direction.NORTH);
        inky = Navigation.findUnitInBoard(inky.getClass(), level.getBoard());

        assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
    }

    /**
     * Testing the next condition 'path != null && !path.isEmpty()'.
     * Since there is no valid path as per the map, the condition will not apply.
     * There is no shortest path from inky's square to the player destination and
     * the array list does not contain any directions.
     * This is a bad weather test
     */
    @Test
    void testPathEmpty() {
        map = new ArrayList<>();
        map.add("#####################");
        map.add("#P                   ");
        map.add("#####################");
        map.add("#B                 I#");
        map.add("#####################");

        Level level = ghostMapParser.parseMap(map);
        level.registerPlayer(player);
        player.setDirection(Direction.NORTH);
        inky = Navigation.findUnitInBoard(inky.getClass(), level.getBoard());
        blinky = Navigation.findUnitInBoard(blinky.getClass(), level.getBoard());

        assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
    }

    /**
     * Testing the condition 'path != null && !path.isEmpty()'.
     *
     * There is a valid path from Inky to the destination square and so
     * the shortestPath() does work, providing an array list of directions
     *
     * If Inky is alongside Blinky when they are behind Pac-Man, Inky will usually
     * follow Blinky the whole time.
     *
     * This is a good weather test.
     */
    @Test
    void testPathNotNull() {
        map = new ArrayList<>();
        map.add("######################");
        map.add("#      I  B    P     #");
        map.add("######################");

        Level level = ghostMapParser.parseMap(map);
        level.registerPlayer(player);
        player.setDirection(Direction.WEST);
        inky = Navigation.findUnitInBoard(inky.getClass(), level.getBoard());
        blinky = Navigation.findUnitInBoard(blinky.getClass(), level.getBoard());

        assertThat(inky.nextAiMove()).isEqualTo(Optional.ofNullable(blinky.getDirection()));
    }

    /**
     * Testing the condition 'path != null && !path.isEmpty()'.
     *
     * Again, there is a valid path from Inky to the destination square and so
     * the shortestPath() does work, providing an array list of directions
     *
     * Since Blinky is far behind Pac-Man with Inky ahead, Inky moves away from
     * the Pac-Man.
     *
     * This is a good weather test.
     */
    @Test
    void testPathNotNull2() {
        map = new ArrayList<>();
        map.add("######################");
        map.add("#             P      #");
        map.add("#     I              #");
        map.add("#                    #");
        map.add("#   B                #");
        map.add("######################");

        Level level = ghostMapParser.parseMap(map);
        level.registerPlayer(player);
        player.setDirection(Direction.SOUTH);
        inky = Navigation.findUnitInBoard(inky.getClass(), level.getBoard());
        blinky = Navigation.findUnitInBoard(blinky.getClass(), level.getBoard());

        assertThat(inky.nextAiMove()).isEqualTo(Optional.ofNullable(Direction.WEST));
    }
}
