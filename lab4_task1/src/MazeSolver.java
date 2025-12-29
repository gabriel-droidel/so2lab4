public class MazeSolver {
    private char[][] maze = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', '#', ' ', ' ', '#'},
            {'#', ' ', '#', '#', ' ', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', '#', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'}
    };

    private int startRow = 1, startCol = 1;
    private int endRow = 5, endCol = 5;

    // Recursive method to find a path from (row, col) to the end
    public boolean findPath(int row, int col) {
        // Boundary check
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }

        // Base case: reached the end
        if (row == endRow && col == endCol) {
            maze[row][col] = '.';
            return true;
        }

        // Check for walls or already visited cells
        if (maze[row][col] == '#' || maze[row][col] == '.') {
            return false;
        }

        // Mark the current cell as part of the path
        maze[row][col] = '.';

        // Try all four directions: up, down, left, right
        if (findPath(row - 1, col) || // up
                findPath(row + 1, col) || // down
                findPath(row, col - 1) || // left
                findPath(row, col + 1)) { // right
            return true;
        }

        // Unmark the cell if no path is found
        maze[row][col] = ' ';
        return false;
    }

    // Method to print the maze
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Getter methods if needed
    public int getStartRow() { return startRow; }
    public int getStartCol() { return startCol; }
}
