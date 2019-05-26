package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Javadoc comment.
 */
class GameUnitTest {
    @Mock
    private Game gameMock;

    @Mock
    private Object progressLockMock;

    /**
     * Testing the start() method.
     */
    @Test
    void testStart() {
        assertFalse(gameMock.isInProgress());
    }
}
