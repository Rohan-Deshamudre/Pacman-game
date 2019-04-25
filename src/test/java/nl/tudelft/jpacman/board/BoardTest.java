package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Board.
 */
class BoardTest {
    private Board board;

    /**
     * Construct a board with a (1 x 1) grid, with one
     * correct BasicSquare on it.
     * Since this is a valid board, your test should pass.
     */
    @Test
    void testBoard() {
        Square[][] grid = new Square[1][1];
        grid[0][0] = new BasicSquare();
        board = new Board(grid);
        assertThat(board.squareAt(0, 0)).isEqualTo(grid[0][0]);
    }
}
