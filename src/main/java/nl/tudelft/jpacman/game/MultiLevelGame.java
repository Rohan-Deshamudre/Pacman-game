package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.List;

/**
 * This is the class for the multi-level game.
 */
public class MultiLevelGame extends Game {
    private List<Level> levels;
    private Player player;
    private Level level;

    private int numLevel;

    /**
     * Constructor.
     * @param pc the point calculator
     * @param levels the list of levels
     * @param player the player
     */
    public MultiLevelGame(PointCalculator pc, List<Level> levels, Player player) {
        super(pc);

        assert player != null;
        assert levels != null;
        this.levels = levels;
        this.player = player;
        this.numLevel = 0;
        this.level = levels.get(numLevel);
        this.level.registerPlayer(player);
    }

    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void levelWon() {
        if (numLevel < levels.size() - 1) {
            stop();
            numLevel++;

            levels.get(numLevel).registerPlayer(player);
        } else {
            stop();
        }

    }
}
