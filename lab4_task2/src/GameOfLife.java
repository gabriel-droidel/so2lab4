import java.util.Random;

public class GameOfLife {
    private boolean[][] grid;
    private int rows, cols;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new boolean[rows][cols];
    }

    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] ? "█ " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }


    // Initialize with glider pattern
    public void initGlider(int startRow, int startCol) {
        grid[startRow][startCol] = true;
        grid[startRow + 1][startCol + 2] = true;
        grid[startRow + 2][startCol] = true;
        grid[startRow + 2][startCol + 1] = true;
        grid[startRow + 2][startCol + 2] = true;
    }

    private int countNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void initRandom() {
        Random rand = new Random();

        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                grid[j][k] = rand.nextBoolean();
            }
        }
    }

    public boolean nextGeneration() {
        boolean[][] nextGen = new boolean[rows][cols];
        boolean isStable = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // get amount of neighbors of current cell
                int neighbors = countNeighbors(i, j);
                // cells with 2 or 3 alive neighbors stay alive
                if ((neighbors == 2 || neighbors == 3) && grid[i][j]) {
                    nextGen[i][j] = true;
                    // cells with 3 alive neighbors, and which are dead become alive
                } else if ((neighbors == 3) && !grid[i][j]) {
                    nextGen[i][j] = true;
                    // else the cell dies
                } else {
                    nextGen[i][j] = false;
                }
            }
        }
        // check if the simulation reached a stable point where no changes happen anymore
        isStable = checkStable(nextGen);

        // copy the new generated simulation in the original array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = nextGen[i][j];
            }
        }
        return isStable;
    }

    public boolean checkStable(boolean[][] nextGen) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != nextGen[i][j])
                    return false;
            }
        }
        return true;
    }
}
