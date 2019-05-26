package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.sprite.SpriteStore;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Testing the MapParser class.
 * Use Mockito to mock the factories and use Mockito to verify that reading a map leads
 * to the proper interactions with those factories.
 */
class MapParserTest {
    private LevelFactory levelCreatorMock;

    private BoardFactory boardCreatorMock;

    private GhostFactory ghostCreatorMock;

    private PlayerFactory playerCreatorMock;

    private Ghost ghost;
    private Player player;

    /**
     * Set up.
     */
    @BeforeEach
    void setMapParser() {
        PacManSprites sprites = new PacManSprites();

        //ghostCreatorMock = new GhostFactory(sprites);
        ghostCreatorMock = Mockito.mock(GhostFactory.class);
        //boardCreatorMock = new BoardFactory(sprites);
        boardCreatorMock = Mockito.mock(BoardFactory.class);

        //levelCreatorMock = new LevelFactory(
        //    sprites, ghostCreatorMock, Mockito.mock(PointCalculator.class));

        levelCreatorMock = Mockito.mock(LevelFactory.class);
        //playerCreatorMock = new PlayerFactory(sprites);
        playerCreatorMock = Mockito.mock(PlayerFactory.class);

        ghost = levelCreatorMock.createGhost();
        ghost.occupy(boardCreatorMock.createGround());

        player = playerCreatorMock.createPacMan();
    }

    /**
     * Testing the parse map method.
     */
    @Test
    void testParseMap() {
        MapParser mapParser = new MapParser(levelCreatorMock, boardCreatorMock);

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
    }
}
