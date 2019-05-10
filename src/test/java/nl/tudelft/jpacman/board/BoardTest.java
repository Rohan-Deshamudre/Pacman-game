package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for the board.
 */
class BoardTest {
    private Board board;
    private Square[][] grid;

    /**
     * Construct a board with a (1 x 1) grid, with one
     * correct BasicSquare on it.
     * Since this is a valid board, we expect the test to pass.
     */
    @Test
    void testBoard() {
        grid = new Square[1][1];
        grid[0][0] = new BasicSquare();
        board = new Board(grid);
        assertThat(board.squareAt(0, 0)).isEqualTo(grid[0][0]);
    }

    /**
     * Verify the desired behaviour of the boundary values of the board.
     * @param x Horizontal coordinate / the width component.
     * @param y Vertical coordinate / the height component.
     * We can construct a (5 x 5) matrix for this test.
     */
    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "-1, 2",
        "5, 3",
        "3, 4",
        "1, 0",
        "2, -1",
        "3, 5",
        "4, 3"
    })
    void testWithinBorders(int x, int y) {
        grid = new Square[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }
        board = new Board(grid);
        assertTrue(board.withinBorders(x, y));
    }
}
