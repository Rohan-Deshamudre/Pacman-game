package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Testing the MapParser class.
 */
class MapParserTest {
    private MapParser mapParser;

    @Mock
    private LevelFactory levelCreatorMock;
    @Mock
    private BoardFactory boardCreatorMock;
    @Mock
    private PlayerFactory playerFactoryMock;

    /**
     * Setting up/instantiating the Map Parser.
     */
    @BeforeEach
    void setParser() {
        //Creating the map parser
        mapParser = new MapParser(levelCreatorMock, boardCreatorMock);
    }

    /**
     * Testing the first parse Map.
     */
    @Test
    void testParseMap() {
        List<String> map = new ArrayList<>();
        map.add("            ");
        map.add("            ");
        map.add("P          G");
        map.add("            ");
        map.add("            ");

        Level level = mapParser.parseMap(map);
        level.registerPlayer(playerFactoryMock.createPacMan());
        playerFactoryMock.createPacMan().setDirection(Direction.SOUTH);

        Square[][] grid = new Square[map.get(0).length()][map.size()];
        List<Ghost> ghosts = new ArrayList<>();
        List<Square> startPositions = new ArrayList<>();

        Square playerSquare = boardCreatorMock.createGround();
        startPositions.add(playerSquare);

        Square ghostSquare = boardCreatorMock.createGround();
        ghosts.add(levelCreatorMock.createGhost());
        levelCreatorMock.createGhost().occupy(ghostSquare);

        Level expectedLevel = levelCreatorMock.createLevel(
            boardCreatorMock.createBoard(grid), ghosts, startPositions);

        assertThat(level).isEqualTo(expectedLevel);
    }
}
