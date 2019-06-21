package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.Level;

import java.io.IOException;

/**
 * This is the class for the multi-level functionality.
 */
class MultiLevelLauncher extends Launcher {
    private MultiLevelGame multiGame;
    public static final String STRING_MAPS = "/level1.txt, /level2.txt, /level3.txt";

    /**
     * This is the getGame() method.
     * @return the multiGame
     */
    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }

    /**
     * Creates a new MultiLevelGame using the level from {@link #makeLevel()}.
     *
     * @return a new MultiLevelGame.
     */
    public MultiLevelGame makeGame() {
        GameFactory gf = getGameFactory();
        Level level = makeLevel();
        multiGame = gf.createSinglePlayerGame(level, loadPointCalculator());
        return multiGame;
    }

    /**
     * Main execution method for the MultiLevelLauncher.
     *
     * @param args
     *            The command line arguments - which are ignored.
     * @throws IOException
     *             When a resource could not be read.
     */
    public static void main(String[] args) throws IOException {
        new MultiLevelLauncher().launch();
    }
}
