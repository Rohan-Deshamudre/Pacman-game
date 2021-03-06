package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        // Remove the following placeholder:
        assertThat(unit.hasSquare()).isEqualTo(false);
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     * In other words, if a unit is occupied by a(ny) basic square, then one
     * should contain the other.
     */
    @Test
    void testOccupy() {
        // Remove the following placeholder:
        Square target = new BasicSquare();
        unit.occupy(target);
        assertThat(target).isEqualTo(unit.getSquare());
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        // Remove the following placeholder:
        Square target1 = new BasicSquare();
        Square target2 = new BasicSquare();
        unit.occupy(target1);
        unit.occupy(target2);
        assertThat(target2).isEqualTo(unit.getSquare());
    }
}
