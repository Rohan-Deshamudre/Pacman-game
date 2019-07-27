package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.List;

/**
 * A game with a single player and multiple levels.
 */
public class MultiLevelGame extends Game {

    private final Player player;
    private final List<Level> levels;
    private  int currentLevel = 0;

    /**
     * <code>true</code> if the game is in progress.
     */
    private boolean inProgress;

    /**
     * Object that locks the start and stop methods.
     */
    private final Object progressLock = new Object();

    /**
     * The algorithm used to calculate the points that
     * they player gets whenever some action happens.
     */
    private PointCalculator pointCalculator;

    /**
     * Creates a new game.
     *
     * @param pointCalculator
     *              The way to calculate points upon collisions.
     * @param player
     *              The player in the game.
     * @param levels
     *              The list of levels we will import from.
     */
    public MultiLevelGame(PointCalculator pointCalculator, Player player, List<Level> levels) {
        super(pointCalculator);
        this.pointCalculator = pointCalculator;
        inProgress = false;
        this.player = player;
        this.levels = levels;
        this.levels.get(currentLevel).registerPlayer(player);
    }



    /**
     * Starts or resumes the game.
     */
    @Override
    public void start() {
        synchronized (progressLock) {
            if (isInProgress()) {
                return;
            }
            if (getLevel().isAnyPlayerAlive() && getLevel().remainingPellets() > 0) {
                inProgress = true;
                getLevel().addObserver(this);
                getLevel().start();
            }
        }
    }

    /**
     * Pauses the game.
     */
    @Override
    public void stop() {
        synchronized (progressLock) {
            if (!isInProgress()) {
                return;
            }
            inProgress = false;
            getLevel().stop();
        }
    }

    /**
     * @return <code>true</code> iff the game is started and in progress.
     */
    @Override
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * @return An immutable list of the participants of this game.
     */
    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    /**
     * @return The level currently being played.
     */
    @Override
    public Level getLevel() {
        return levels.get(currentLevel);
    }

    /**
     * Moves the specified player one square in the given direction.
     *
     * @param player
     *            The player to move.
     * @param direction
     *            The direction to move in.
     */
    @Override
    public void move(Player player, Direction direction) {
        if (isInProgress()) {
            // execute player move.
            getLevel().move(player, direction);
            pointCalculator.pacmanMoved(player, direction);
        }
    }

    @Override
    public void levelWon() {
        if (currentLevel + 1 >= levels.size()) {
            stop();
        } else {
            currentLevel++;
            getLevel().registerPlayer(player);
            stop();
            start();
        }
    }

    @Override
    public void levelLost() {
        stop();
    }
}
