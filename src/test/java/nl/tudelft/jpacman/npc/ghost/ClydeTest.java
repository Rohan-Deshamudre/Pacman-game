package nl.tudelft.jpacman.npc.ghost;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import nl.tudelft.jpacman.board.*;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Write at least 4 Junit tests for the nextAiMove() method.
 * 2 good weather cases & 2 bad weather cases.
 */
class ClydeTest {
    private Ghost clyde;
    private List<Ghost> ghosts;

    private LevelFactory lvlFactory;
    private BoardFactory boardFactory;
    private GhostFactory ghostFactory;

    private GhostMapParser ghostMapParser;
    private List<String> map;

    private PlayerFactory playerFactory;
    private Player player;

    /**
     * Instantiating the GhostMapParser object.
     */
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        ghostFactory = new GhostFactory(sprites);
        boardFactory = new BoardFactory(sprites);
        lvlFactory = new LevelFactory(sprites, ghostFactory, mock(PointCalculator.class));
        ghostMapParser = new GhostMapParser(lvlFactory, boardFactory, ghostFactory);

        map = new ArrayList<>();
        map.add("############");
        map.add("#P        C#");
        map.add("############");

        ghosts = new ArrayList<>();
        clyde = ghostFactory.createClyde();
        //clyde.setDirection(clyde.nextMove());
        ghosts.add(clyde);
        boardFactory.createGround().isAccessibleTo(clyde);
        clyde.occupy(boardFactory.createGround());

        playerFactory = new PlayerFactory(sprites);
        player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
    }

    /**
     * Building the level based on map.
     * MapParser.parseMap() receives the map and returns Level that uses the same map.
     * We can now test the IN-point for the condition 'nearest == null' so taking it to be null.
     */
    @Test
    void testNullCase() {
        Level level = ghostMapParser.parseMap(map);
        //Put clyde on the level but NOT the player
        clyde = Navigation.findUnitInBoard(clyde.getClass(), level.getBoard());
        assertThat(clyde.nextAiMove()).isEqualTo(Optional.empty());
    }

    /**
     * As the test involves a player on the map, we register it using Level.registerPlayer().
     * We can now test the OFF-point for the condition 'nearest == null' so taking it as not null.
     */
    @Test
    void testNullCase2() {
        Level level = ghostMapParser.parseMap(map);
        //Putting both clyde and the player on the level
        level.registerPlayer(player);
        clyde = Navigation.findUnitInBoard(clyde.getClass(), level.getBoard());
        assertThat(clyde.nextAiMove()).isNotEqualTo(Optional.empty());
    }

    /**
     * Testing the ON point for the condition 'path != null && !path.isEmpty()' and
     * we expect the test to pass.
     * Unit nearest = Navigation.findNearest(Player.class, getSquare())
     * List<Direction> path = Navigation.shortestPath(getSquare(), target, this)
     */
    @Test
    void testPathNotNull() {
        Level level = ghostMapParser.parseMap(map);
        level.registerPlayer(player);
        clyde = Navigation.findUnitInBoard(clyde.getClass(), level.getBoard());
        Square target = Navigation.findNearest(player.getClass(), clyde.getSquare()).getSquare();
        Direction direction = Navigation.shortestPath(clyde.getSquare(), target, clyde).get(0);
        assertThat(clyde.nextAiMove()).isEqualTo(Optional.of(direction));
    }

    /**
     * Testing the OFF point for the condition 'path != null && !path.isEmpty()'.
     * Unit nearest = Navigation.findNearest(Player.class, getSquare())
     * List<Direction> path = Navigation.shortestPath(getSquare(), target, this)
     */
    @Test
    void testPathIsNull() {
        Level level = ghostMapParser.parseMap(map);
        clyde = Navigation.findUnitInBoard(clyde.getClass(), level.getBoard());
        Unit nearest = Navigation.findNearest(player.getClass(), clyde.getSquare());
        assertThat(nearest).isEqualTo(null);
    }
}
