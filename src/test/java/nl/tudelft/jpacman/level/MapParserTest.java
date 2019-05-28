package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing the MapParser class.
 * Use Mockito to mock the factories and use Mockito to verify that reading a map leads
 * to the proper interactions with those factories.
 */
class MapParserTest {
    private MapParser mapParser;
    private PacManSprites sprites;

    private LevelFactory levelFactory;
    private BoardFactory boardFactory;
    private PlayerFactory playerFactory;

    private Ghost ghost;
    private Player player;

    /**
     * Set up.
     */
    @BeforeEach
    void setMapParser() {
        sprites = new PacManSprites();

        levelFactory = Mockito.mock(LevelFactory.class);
        boardFactory = Mockito.mock(BoardFactory.class);
        playerFactory = Mockito.mock(PlayerFactory.class);

        mapParser = new MapParser(levelFactory, boardFactory);

    }

    /**
     * Testing create ground.
     */
    @Test
    void testCreateGround() {
        List<String> map = new ArrayList<>();
        map.add(" ");
        mapParser.parseMap(map);

        Mockito.verify(boardFactory).createGround();
    }

    /**
     * Testing create Wall.
     */
    @Test
    void testCreateWall() {
        List<String> map = new ArrayList<>();
        map.add("#");
        mapParser.parseMap(map);

        Mockito.verify(boardFactory).createWall();
    }

    /**
     * Testing create Pellet.
     */
    @Test
    void testCreatePellet() {
        List<String> map = new ArrayList<>();
        map.add(".");

        Pellet pellet = Mockito.mock(Pellet.class);
        Mockito.when(levelFactory.createPellet()).thenReturn(pellet);

        Square square = Mockito.mock(Square.class);
        Mockito.when(boardFactory.createGround()).thenReturn(square);

        mapParser.parseMap(map);

        Mockito.verify(levelFactory).createPellet();
        Mockito.verify(boardFactory).createGround();
        Mockito.verify(pellet).occupy(square);
    }

    /**
     * Testing create Ghost.
     */
    @Test
    void testCreateGhost() {

        List<String> map = new ArrayList<>();
        map.add("G");

        Ghost ghost = Mockito.mock(Ghost.class);
        Mockito.when(levelFactory.createGhost()).thenReturn(ghost);

        Square square = Mockito.mock(Square.class);
        Mockito.when(boardFactory.createGround()).thenReturn(square);

        mapParser.parseMap(map);

        Mockito.verify(levelFactory).createGhost();
        Mockito.verify(boardFactory).createGround();
        Mockito.verify(ghost).occupy(square);
    }

    /**
     * Testing create Pac-Man.
     */
    @Test
    void testCreatePlayer() {

        Square square = Mockito.mock(Square.class);
        Mockito.when(boardFactory.createGround()).thenReturn(square);

        List<String> map = new ArrayList<>();
        map.add("P");
        mapParser.parseMap(map);

        Mockito.verify(boardFactory).createGround();
    }

    /**
     * Testing a map with multiple variables.
     */
    @Test
    void testParseMap() {
        List<String> map = new ArrayList<>();
        map.add("################");
        map.add(" G        P     ");
        map.add("                ");
        map.add("################");

        Level level = mapParser.parseMap(map);
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);
        //ghost = Navigation.findUnitInBoard(ghost.getClass(), level.getBoard());

        assertThat(level).isNotEqualTo(null);
        assertThat(level.getBoard()).isNotEqualTo(null);
        assertThat(Navigation.findUnitInBoard(
            player.getClass(), level.getBoard())).isNotEqualTo(null);
        assertThat(Navigation.findUnitInBoard(
            ghost.getClass(), level.getBoard())).isEqualTo(null);
        Mockito.verify(boardFactory);
    }
}
