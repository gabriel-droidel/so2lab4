public class Main {
    public static void main(String[] args) {
        MazeSolver solver = new MazeSolver();

        System.out.println("Initial Maze:");
        solver.printMaze();

        System.out.println("\nFinding path...");
        if (solver.findPath(solver.getStartRow(), solver.getStartCol())) {
            System.out.println("\nPath found:");
        } else {
            System.out.println("\nNo path found.");
        }
        solver.printMaze();
    }
}
