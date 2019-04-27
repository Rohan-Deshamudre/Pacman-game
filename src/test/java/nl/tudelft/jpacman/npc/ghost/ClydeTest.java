package nl.tudelft.jpacman.npc.ghost;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.jpacman.board.Unit;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Write at least 4 Junit tests for the nextAiMove() method
 * 2 good whether cases & 2 bad whether cases
 */
class ClydeTest {
    private Clyde clyde;

    /**
     * Later...
     */
    @Test
    void testNullCase() {
        //Unit unit = Navigation.findNearest(clyde.getClass(), clyde.getSquare());
        assertEquals(Optional.empty(), clyde.nextAiMove());
    }
}
