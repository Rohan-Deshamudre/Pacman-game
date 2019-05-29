package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing the MapParser class.
 * Use Mockito to mock the factories and use Mockito to verify that reading a map leads
 * to the proper interactions with those factories.
 */
class MapParserTest {
    private MapParser mapParser;

    private LevelFactory levelFactory;
    private BoardFactory boardFactory;

    /**
     * Set up.
     */
    @BeforeEach
    void setMapParser() {
        levelFactory = Mockito.mock(LevelFactory.class);
        boardFactory = Mockito.mock(BoardFactory.class);

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

    //Bad weather tests
    /**
     * First bad weather, testing illegal characters.
     */
    @Test
    void testIllegalCharacters() {
        List<String> map = new ArrayList<>();
        map.add("hgjfhgjfhgjhg");

        assertThrows(
            PacmanConfigurationException.class,
            () -> mapParser.parseMap(map), "Invalid character");
    }

    /**
     * Second bad weather, testing a null map.
     */
    @Test
    void testNullString() {
        List<String> map = null;

        assertThrows(
            PacmanConfigurationException.class,
            () -> mapParser.parseMap(map), "Input text cannot be null.");
    }

    /**
     * Third bad weather test, testing an empty map.
     */
    @Test
    void testEmptyString() {
        List<String> map = new ArrayList<>();
        map.add("");

        assertThrows(
            PacmanConfigurationException.class,
            () -> mapParser.parseMap(map), "Input text must consist of at least 1 row.");
    }

    /**
     * Fourth bad weather, testing the width = 0.
     */
    @Test
    void testWidthEqualsZero() {
        List<String> map = new ArrayList<>();
        map.add("");
        map.add("");

        assertThrows(
            PacmanConfigurationException.class,
            () -> mapParser.parseMap(map), "Input text lines cannot be empty.");
    }

    /**
     * Fifth bad weather, testing that length & width aren't same.
     */
    @Test
    void testNotSquare() {
        List<String> map = new ArrayList<>();
        map.add("##");
        map.add("#");

        assertThrows(
            PacmanConfigurationException.class,
            () -> mapParser.parseMap(map), "Input text lines are not of equal width.");
    }
}
