void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome Conway's Game of Life");
    while (true) {
        System.out.println("Setting grid size");
        System.out.print("Input amount of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Input amount of columns: ");
        int cols = scanner.nextInt();
        GameOfLife game = new GameOfLife(rows, cols);
        System.out.println("Choose initial generation type:");
        System.out.println("1. Random generation\n" +
                "2. Glider generation (Grid must be at least 3x3)");
        int generationType = scanner.nextInt();
        if (generationType == 1) {
            game.initRandom();
        } else if (generationType == 2) {
            while (rows < 3 || cols < 3) {
                System.out.println("Grid too small for a glider. Minimum size is 3x3.");
                System.out.print("Enter number of rows: ");
                rows = scanner.nextInt();
                System.out.print("Enter number of columns: ");
                cols = scanner.nextInt();
                game = new GameOfLife(rows, cols); // recreate game with new size
            }
            // ask user for starting position, make sure it's within 3x3 glider boundaries
            int maxStartRow = rows - 3;
            int maxStartCol = cols - 3;

            System.out.println("Enter initial row (0 to " + maxStartRow + "): ");
            int startRow = Math.min(scanner.nextInt(), maxStartRow);
            System.out.println("Enter initial column (0 to " + maxStartCol + "): ");
            int startCol = Math.min(scanner.nextInt(), maxStartCol);

            game.initGlider(startRow, startCol);
        }
        System.out.println("Choose how many generations (0 to run until stable): ");
        int generations = scanner.nextInt();
        if (generations == 0) {
            while (true) {
                game.printGrid();
                if (game.nextGeneration()) {
                    break;
                }
            }
        } else {
            for (int i = 0; i < generations; i++) {
                game.nextGeneration();
                game.printGrid();
            }
        }
        System.out.println("Generate a new game or exit? Type 0 to exit");
        int choice = scanner.nextInt();
        if(choice==0){
            System.out.println("Game over!");
            break;
        }
    }
    scanner.close();
}
