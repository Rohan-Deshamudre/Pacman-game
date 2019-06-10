package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test case for testing the User Story 4.
 */
class SuspensionSystemTest {
    private Launcher launcher;

    /**
     * Setting up the test suite.
     */
    @BeforeEach
    void setLauncher() {
        launcher = new Launcher();
    }

    /**
     * Disposing off the launcher.
     */
    @AfterEach
    void closeOff() {
        launcher.dispose();
    }

    /**
     * Getting the game to start.
     */
    @Test
    void testGameIsRunning() {
        launcher.launch();

        launcher.getGame().start();

        assertThat(launcher.getGame().isInProgress()).isTrue();
    }

    /**
     * Getting the game to suspend.
     */
    @Test
    void testGameSuspends() {
        testGameIsRunning();

        launcher.getGame().stop();
        assertThat(launcher.getGame().isInProgress()).isFalse();
    }

    /**
     * Getting the game to resume.
     */
    @Test
    void testGameResumes() {
        testGameSuspends();

        launcher.getGame().start();
        assertThat(launcher.getGame().isInProgress()).isTrue();
    }
}
