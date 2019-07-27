package nl.tudelft.jpacman;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.ui.Action;
import nl.tudelft.jpacman.ui.PacManUI;
import nl.tudelft.jpacman.ui.PacManUiBuilder;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Launcher that will allow us to play multiple levels.
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.ForLoopCanBeForeach"})
public class MultiLevelLauncher extends Launcher {

    private static final int N_LEVELS = 3;
    private static final PacManSprites SPRITE_STORE = new PacManSprites();
    public static final String DEFAULT_MAP = "/board.txt";
    private String levelMap = DEFAULT_MAP;
    private PacManUI pacManUI;
    private Game game;
    private boolean useMakeGame = true;
    private List<String> parsedLevels;

    /**
     * @return The game object this launcher will start when {@link #launch()}
     *         is called.
     */
    @Override
    public Game getGame() {
        return game;
    }

    /**
     * The map file used to populate the level.
     *
     * @return The name of the map file.
     */
    @Override
    protected String getLevelMap() {
        return levelMap;
    }

    /**
     * Set the name of the file containing this level's map.
     *
     * @param fileName
     *            Map to be used.
     * @return Level corresponding to the given map.
     */
    @Override
    public MultiLevelLauncher withMapFile(String fileName) {
        parsedLevels = new ArrayList<>();
        parsedLevels.add(fileName);
        useMakeGame = false;
        return this;
    }

    /**
     * Our own cool withmapfile.
     *
     * @param fileNames
     *              The filenames to read in.
     * @return The launcher with custom maps.
     */
    public MultiLevelLauncher withMapFile(List<String> fileNames) {
        parsedLevels = fileNames;
        useMakeGame = false;
        return this;
    }

    /**
     * Creates a new game using the map that corresponds with the current level.
     *
     * @return a new Game.
     */
    @Override
    public Game makeGame() {
        List<Level> levels = new ArrayList<>();
        if (useMakeGame) {
            for (int i = 0; i < N_LEVELS; i++) {
                levels.add(makeCustomLevel(i));
            }
        } else {
            try {
                for (int i = 0; i < parsedLevels.size(); i++) {
                    levels.add(getMapParser().parseMap(parsedLevels.get(i)));
                }
            } catch (IOException e) {
                throw new PacmanConfigurationException(
                    "Unable to create level, name = " + getLevelMap(), e);
            }
        }
        game = new MultiLevelGame(loadPointCalculator(), getPlayerFactory().createPacMan(), levels);
        return game;
    }

    private PointCalculator loadPointCalculator() {
        return new PointCalculatorLoader().load();
    }

    /**
     * Here we use a custom map to make our level.
     * @param level
     *          Where you get a level.
     * @return parsedLevel
     *          The level using the map corresponding to our current leve.
     */
    public Level makeCustomLevel(int level) {
        try {
            return getMapParser().parseMap("/" + level + ".txt");
        } catch (IOException e) {
            throw new PacmanConfigurationException(
                "Unable to create level, name = " + getLevelMap(), e);
        }
    }

    /**
     * Creates a new level. By default this method will use the map parser to
     * parse the default board stored in the <code>board.txt</code> resource.
     *
     * @return A new level.
     */
    @Override
    public Level makeLevel() {
        try {
            return getMapParser().parseMap(getLevelMap());
        } catch (IOException e) {
            throw new PacmanConfigurationException(
                "Unable to create level, name = " + getLevelMap(), e);
        }
    }

    /**
     * @return A new map parser object using the factories from
     *         {@link #getLevelFactory()} and {@link #getBoardFactory()}.
     */
    @Override
    protected MapParser getMapParser() {
        return new MapParser(getLevelFactory(), getBoardFactory());
    }

    /**
     * @return A new board factory using the sprite store from
     *         {@link #getSpriteStore()}.
     */
    @Override
    protected BoardFactory getBoardFactory() {
        return new BoardFactory(getSpriteStore());
    }

    /**
     * @return The default {@link PacManSprites}.
     */
    @Override
    protected PacManSprites getSpriteStore() {
        return SPRITE_STORE;
    }

    /**
     * @return A new factory using the sprites from {@link #getSpriteStore()}
     *         and the ghosts from {@link #getGhostFactory()}.
     */
    @Override
    protected LevelFactory getLevelFactory() {
        return new LevelFactory(getSpriteStore(), getGhostFactory(), loadPointCalculator());
    }

    /**
     * @return A new factory using the sprites from {@link #getSpriteStore()}.
     */
    @Override
    protected GhostFactory getGhostFactory() {
        return new GhostFactory(getSpriteStore());
    }

    /**
     * @return A new factory using the players from {@link #getPlayerFactory()}.
     */
    @Override
    protected GameFactory getGameFactory() {
        return new GameFactory(getPlayerFactory());
    }

    /**
     * @return A new factory using the sprites from {@link #getSpriteStore()}.
     */
    @Override
    protected PlayerFactory getPlayerFactory() {
        return new PlayerFactory(getSpriteStore());
    }

    /**
     * Adds key events UP, DOWN, LEFT and RIGHT to a game.
     *
     * @param builder
     *            The {@link PacManUiBuilder} that will provide the UI.
     */
    @Override
    protected void addSinglePlayerKeys(final PacManUiBuilder builder) {
        builder.addKey(KeyEvent.VK_UP, moveTowardsDirection(Direction.NORTH))
            .addKey(KeyEvent.VK_DOWN, moveTowardsDirection(Direction.SOUTH))
            .addKey(KeyEvent.VK_LEFT, moveTowardsDirection(Direction.WEST))
            .addKey(KeyEvent.VK_RIGHT, moveTowardsDirection(Direction.EAST));
    }

    private Action moveTowardsDirection(Direction direction) {
        return () -> {
            assert game != null;
            getGame().move(getSinglePlayer(getGame()), direction);
        };
    }

    private Player getSinglePlayer(final Game game) {
        List<Player> players = game.getPlayers();
        if (players.isEmpty()) {
            throw new IllegalArgumentException("Game has 0 players.");
        }
        return players.get(0);
    }

    /**
     * Creates and starts a JPac-Man game.
     */
    @Override
    public void launch() {
        makeGame();
        PacManUiBuilder builder = new PacManUiBuilder().withDefaultButtons();
        addSinglePlayerKeys(builder);
        pacManUI = builder.build(getGame());
        pacManUI.start();
    }

    /**
     * Disposes of the UI. For more information see
     * {@link javax.swing.JFrame#dispose()}.
     *
     * Precondition: The game was launched first.
     */
    @Override
    public void dispose() {
        assert pacManUI != null;
        pacManUI.dispose();
    }

    /**
     * Main execution method for the Launcher.
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
