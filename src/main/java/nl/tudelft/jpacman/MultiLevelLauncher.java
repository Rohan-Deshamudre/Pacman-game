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
    public static final String LEVEL1_MAP = "/level1.txt";
    private String level1Map = LEVEL1_MAP;
    public static final String LEVEL2_MAP = "level2.txt";
    private String level2Map = LEVEL2_MAP;
    public static final String LEVEL3_MAP = "level3.txt";
    private String level3Map = LEVEL3_MAP;


    /**
     * This is the getGame() method.
     * @return the multiGame
     */
    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }

    /**
     * Set the name of the file containing this level's map.
     *
     * @param fileName
     *            Map to be used.
     * @return Level corresponding to the given map.
     */
    public Launcher withMapFile(String fileName) {
        getGame();
        level1Map = fileName;
        if(getGame().getLevel().remainingPellets()==0){
            level2Map=fileName;
        }
        return this;
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
