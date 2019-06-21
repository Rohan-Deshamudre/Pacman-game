package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.points.PointCalculatorLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the class for the multi-level functionality.
 */
public class MultiLevelLauncher extends Launcher {
    private MultiLevelGame multiGame;
    private static final String LEVEL1_MAP = "/level1.txt";
    private static final String LEVEL2_MAP = "level2.txt";
    private static final String LEVEL3_MAP = "level3.txt";

    private String[] levels = {LEVEL1_MAP, LEVEL2_MAP, LEVEL3_MAP};

    /**
     * This is the getGame() method.
     * @return the multiGame
     */
    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }

    private PointCalculator loadPointCalculator() {
        return new PointCalculatorLoader().load();
    }

    /**
     * Creates a new MultiLevelGame using the level from {@link #makeLevel()}.
     *
     * @return a new MultiLevelGame.
     */
    public MultiLevelGame makeGame() {
        Player player = new PlayerFactory(getSpriteStore()).createPacMan();
        multiGame = new MultiLevelGame(loadPointCalculator(), getLevels(), player);
        return multiGame;
    }

    /**
     * Creates a new level list containing all the levels.
     * @return the list of levels
     */
    public List<Level> getLevels() {
        List<Level> lvlList = new ArrayList<>();
        for (String str : levels) {
            try {
                lvlList.add(getMapParser().parseMap(str));
            } catch (IOException e) {
                throw new PacmanConfigurationException(
                    "Unable to create level, name = " + getLevelMap(), e);
            }
        }
        return lvlList;
    }

    /**
     * Set the name of the file containing this level's map.
     *
     * @param file
     *            Map to be used.
     * @return Level corresponding to the given map.
     */
    public Launcher withMapFile(String file) {
        String[] strArray = new String[] {file};
        this.levels = strArray.clone();
        return this;
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
