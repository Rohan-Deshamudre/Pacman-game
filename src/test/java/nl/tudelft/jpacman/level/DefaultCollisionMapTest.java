package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

/**
 * Testing both PlayerCollisions and CollisionMapTest.
 */
class DefaultCollisionMapTest extends PlayerCollisionsTest {
    /**
     * Setting up the test suite.
     */
    @BeforeEach
    void setUp() {
        super.pointCalculator = Mockito.mock(PointCalculator.class);
        super.playerCollisions = new DefaultPlayerInteractionMap(pointCalculator);
    }
}
