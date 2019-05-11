package nl.tudelft.jpacman.board;

import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing the squaresAheadOf method in Unit.
 */
class UnitTest {
    private Unit unit;
    private Direction target;
    private Square destination;

    /**
     * Setting up for the tests.
     */
    @BeforeEach
    void setUp() {
        unit = new Unit() {
            public Sprite getSprite() {
                return unit.getSprite();
            }
        };
        target = unit.getDirection();
        destination = new BasicSquare();
        unit.occupy(destination);
    }

    /**
     * Wrote an initial test with 1 non-null square.
     */
    @Test
    void testSquaresAheadOf() {
        int amountToLookAhead = 1;
        for (int i = 0; i < amountToLookAhead; i++) {
            destination = destination.getSquareAt(target);
        }
        assertThat(unit.squaresAheadOf(amountToLookAhead)).isEqualTo(destination);
    }
}
