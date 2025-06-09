import java.awt.*;

/**
 * The Board4 class models the ROWS-by-COLS game board using Cell4.
 */
public class Board5 {
    public static final int ROWS = 3;
    public static final int COLS = 3;

    public static final int CANVAS_WIDTH = Cell5.SIZE * COLS;
    public static final int CANVAS_HEIGHT = Cell5.SIZE * ROWS;
    public static final int GRID_WIDTH = 8;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
    public static final Color COLOR_GRID = Color.LIGHT_GRAY;
    public static final int Y_OFFSET = 1;

    Cell5[][] cells;

    /** Constructor */
    public Board5() {
        initGame5();
    }

    /** Initialize the game board */
    public void initGame5() {
        cells = new Cell5[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell5(row, col);
            }
        }
    }

    /** Reset the board content */
    public void newGame5() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].newGame5();
            }
        }
    }

    /**
     * Perform a move by the player and check for game state.
     */
    public State5 stepGame5(Seed5 player, int selectedRow, int selectedCol) {
        cells[selectedRow][selectedCol].content = player;

        if (
                cells[selectedRow][0].content == player &&
                        cells[selectedRow][1].content == player &&
                        cells[selectedRow][2].content == player ||

                        cells[0][selectedCol].content == player &&
                                cells[1][selectedCol].content == player &&
                                cells[2][selectedCol].content == player ||

                        selectedRow == selectedCol &&
                                cells[0][0].content == player &&
                                cells[1][1].content == player &&
                                cells[2][2].content == player ||

                        selectedRow + selectedCol == 2 &&
                                cells[0][2].content == player &&
                                cells[1][1].content == player &&
                                cells[2][0].content == player
        ) {
            return (player == Seed5.CROSS) ? State5.CROSS_WON : State5.NOUGHT_WON;
        } else {
            for (int row = 0; row < ROWS; ++row) {
                for (int col = 0; col < COLS; ++col) {
                    if (cells[row][col].content == Seed5.NO_SEED) {
                        return State5.PLAYING;
                    }
                }
            }
            return State5.DRAW;
        }
    }

    /** Render the board */
    public void paint(Graphics g) {
        g.setColor(COLOR_GRID);
        for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(0, Cell5.SIZE * row - GRID_WIDTH_HALF,
                    CANVAS_WIDTH - 1, GRID_WIDTH,
                    GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(Cell5.SIZE * col - GRID_WIDTH_HALF, Y_OFFSET,
                    GRID_WIDTH, CANVAS_HEIGHT - 1,
                    GRID_WIDTH, GRID_WIDTH);
        }

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint(g);
            }
        }
    }
}