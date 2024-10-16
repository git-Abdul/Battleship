import java.util.*;
class Battleship {

    String[] previousInputs = new String[30];
    int inputIndex = 0;

    String[][] generateArray(String[][] grid, int player) {
        int k = 0;
        String s = "";

        while (k < 3) {
            int r = (int) (Math.random() * 6) + 1;
            int c = (int) (Math.random() * 11);
            if (!grid[r][c].equals(" ♒︎") && s.indexOf(Integer.toString(r)) < 0) {
                int len = (int) (Math.random() * (13 - c + 1)) + c;

                for (int m = c; m < c + len && m < 12; m++) {
                    grid[r][m] = Integer.toString(player);
                }
                s += Integer.toString(r);
                k++;
            }
        }
        return grid;
    }

    void printArray(String[][] grid, int player) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                if (grid[i][j].equals(Integer.toString(player)) && !grid[i][j].equals("=")) {
                    System.out.print(String.format("%3s", "♒︎"));
                } else {
                    System.out.print(String.format("%3s", grid[i][j]));
                }
            }
            System.out.println();
        }
    }

    void clear() {
        // Clear console for Unix-based systems
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // For Windows systems
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void printPlayerGrids(String[][] grid, String[][] grid2) {
        Battleship ob = new Battleship();
        System.out.println("=============================================");
        ob.printArray(grid, 1);
        System.out.println("=============================================");
        ob.printArray(grid2, 2);
        System.out.println("=============================================");
        System.out.println();
    }

    String[][] searchShip(int r, int c, String[][] grid, int playerOpp) {
        int player = (playerOpp == 1) ? 2 : 1;
        for (int i = 0; i <= 11; i++) {
            if (grid[r][i].equals(Integer.toString(playerOpp))) {
                grid[r][i] = "=";
            }
        }
        return grid;
    }

    String checkWinner(int playerOpp, int r, int c, String[][] grid) {
        Battleship ob = new Battleship();
        String s = "";
        if (grid[r][c].equals(Integer.toString(playerOpp))) {
            grid[r][c] = "=";
            if (playerOpp == 1) {
                s = "2true";
                grid = ob.searchShip(r, c, grid, playerOpp);
            } else {
                s = "1true";
                grid = ob.searchShip(r, c, grid, playerOpp);
            }
        } else {
            s = "nil";
        }
        return s;
    }

    boolean isValidInput(int r, int c) {
        return r >= 0 && r < 7 && c >= 0 && c < 12;
    }

    void printArt() {
        System.out.println("==============================================");
        System.out.println("   ___       __  __  __        __   _    ");
        System.out.println("  / _ )___ _/ /_/ /_/ /__ ___ / /  (_)__ ");
        System.out.println(" / _  / _ `/ __/ __/ / -_|_-</ _ \\/ / _ \\");
        System.out.println("/____/\\_,_/\\__/\\__/_/\\__/___/_//_/_/ .__/");
        System.out.println("                                  /_/     ");
        System.out.println("==============================================");
        System.out.println();
    }

    void mainMenu() {
        Battleship ob = new Battleship();
        Scanner sc = new Scanner(System.in);

        ob.clear();
        ob.printArt();
        System.out.println("Game modes:");
        System.out.println("    1. Classic 1v1");
        System.out.println("    2. 1v1 Bot");
        System.out.println("    3. Score Rush");
        System.out.println("    4. Score Rush Bot");
        System.out.println("    5. Exit");
        System.out.println();

        System.out.print("Enter choice: ");
        int n = sc.nextInt();
        System.out.println();

        switch (n) {
            case 1:
                System.out.print("Enter number of rounds: ");
                int rounds = sc.nextInt();
                Classic1v1(rounds);
                break;

            case 2:
                System.out.print("Enter number of rounds: ");
                int rounds2 = sc.nextInt();
                Bot1v1(rounds2);
                break;

            case 3:
                ScoreRush(3);
                break;

            case 4:
                ScoreRushBot(3);
                break;

            case 5:
                System.out.print("Program terminated.");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid input.");
                ob.mainMenu();
                break;
        }
    }

    void Classic1v1(int rounds) {
        Battleship ob = new Battleship();
        Scanner sc = new Scanner(System.in);

        String grid[][] = new String[7][12];
        String grid2[][] = new String[7][12];

        while (rounds != 0) {
            // Initialize grids
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 12; j++) {
                    grid[i][j] = "♒︎";
                    grid2[i][j] = "♒︎";
                }
            }

            grid = ob.generateArray(grid, 1);
            grid2 = ob.generateArray(grid2, 2);

            int counter1 = 0, counter2 = 0;

            while (counter1 != 3 && counter2 != 3) {
                ob.clear();
                ob.printPlayerGrids(grid, grid2);

                // Player 1:
                int p1r = 0, p1c = 0;
                while (true) {
                    System.out.print("Player 1: Enter row and column number: ");
                    String s1 = sc.next();
                    String[] input = s1.split(",");
                    p1r = Integer.parseInt(input[0]) - 1;
                    p1c = Integer.parseInt(input[1]) - 1;

                    if (ob.isValidInput(p1r, p1c)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter valid row (1-7) and column (1-12) numbers.");
                    }
                }

                if (ob.checkWinner(2, p1r, p1c, grid2).equals("1true")) {
                    counter1++;
                    System.out.println("Player 1 found a ship!");
                    System.out.println();
                } else {
                    System.out.println("No ship found...");
                    System.out.println();
                }

                // Player 2:
                int p2r = -1, p2c = -1;
                while (true) {
                    System.out.print("Player 2: Enter row and column number: ");
                    String s2 = sc.next();
                    String[] input = s2.split(",");
                    p2r = Integer.parseInt(input[0]) - 1;
                    p2c = Integer.parseInt(input[1]) - 1;

                    if (ob.isValidInput(p2r, p2c)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter valid row (1-7) and column (1-12) numbers.");
                    }
                }

                if (ob.checkWinner(1, p2r, p2c, grid).equals("2true")) {
                    counter2++;
                    System.out.println("Player 2 found a ship!");
                    System.out.println();
                } else {
                    System.out.println("No ship found...");
                    System.out.println();
                }

                System.out.println("Press any key to continue... ");
                String input = sc.next();
                if (input.equals("exit"))
                    System.exit(0);
            }
            if (counter1 == 3) {
                ob.clear();
                ob.printPlayerGrids(grid, grid2);
                System.out.println();
                System.out.println("PLAYER 1: WINS BY " + (counter1 - counter2) + " POINTS.");
                if (rounds > 1) {
                    System.out.println("Press any key to continue...");
                    String s = sc.next();
                }
            } else {
                ob.clear();
                ob.printPlayerGrids(grid, grid2);
                System.out.println();
                System.out.println("PLAYER 2: WINS BY " + (counter2 - counter1) + " POINTS.");
                if (rounds > 1) {
                    System.out.println("Press any key to continue...");
                    String s = sc.next();
                }
            }
            rounds--;
        }
    }

    // Bot Algorithm
    void botAlgorithm(String[][] grid, Battleship ob, int[] counter2) {
        int p2r, p2c, k = 0;

        p2r = (int) (Math.random() * 7);
        p2c = (int) (Math.random() * 12);

        String position = p2r + "," + p2c;

        for (int i = 0; i < inputIndex; i++) {
            if (position.equals(previousInputs[i])) {
                k++;
            }
        }

        if (grid[p2r][p2c].equals("=") || k > 0 || grid[p2r][p2c].equals(" 💥") || grid[p2r][p2c].equals(" 💖")) {
            botAlgorithm(grid, ob, counter2);
        } else {
            switch (ob.checkWinnerSR(1, p2r, p2c, grid)) {
                case "2trueship":
                    counter2[0] += 2;
                    System.out.println("Bot chose: "+position+"\nBot found a ship! (Score: " + counter2[0] + ")");
                    System.out.println();
                    break;

                case "2truebomb":
                    counter2[0]--;
                    System.out.println("Bot chose: "+position+"\nBot found a bomb 💥! (Score: " + counter2[0] + ")");
                    System.out.println();
                    break;

                case "2trueorb":
                    counter2[0]++;
                    System.out.println("Bot chose: "+position+"\nBot found an orb 💖! (Score: " + counter2[0] + ")");
                    System.out.println();
                    break;

                case "nil":
                    System.out.println("Bot chose: "+position+"\nNo ship found... (Score: " + counter2[0] + ")");
                    System.out.println();
                    break;

                default:
                    System.err.print("Error: Wrong value passed to func: checkWinnerSR(); PLAYER1");
                    break;
            }
            if (k == 0) {
                previousInputs[inputIndex++] = position;
            }
        }
    }

    void Bot1v1(int rounds) {
        Battleship ob = new Battleship();
        Scanner sc = new Scanner(System.in);

        String grid[][] = new String[7][12];
        String grid2[][] = new String[7][12];

        while (rounds != 0) {
            // Initialize grids
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 12; j++) {
                    grid[i][j] = "♒︎";
                    grid2[i][j] = "♒︎";
                }
            }

            grid = ob.generateArray(grid, 1);
            grid2 = ob.generateArray(grid2, 2);

            int counter1 = 0;
            int[] counter2 = { 0 };

            while (counter1 != 3 && counter2[0] != 3) {
                ob.clear();
                ob.printPlayerGrids(grid, grid2);

                // Player 1:
                int p1r = 0, p1c = 0;
                while (true) {
                    System.out.print("Player: Enter row and column number: ");
                    String s1 = sc.next();
                    String[] input = s1.split(",");
                    p1r = Integer.parseInt(input[0]) - 1;
                    p1c = Integer.parseInt(input[1]) - 1;

                    if (ob.isValidInput(p1r, p1c)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter valid row (1-7) and column (1-12) numbers.");
                    }
                }

                if (ob.checkWinner(2, p1r, p1c, grid2).equals("1true")) {
                    counter1++;
                    System.out.println("Player found a ship!");
                } else {
                    System.out.println("No ship found...");
                }

                System.out.println();

                ob.botAlgorithm(grid, ob, counter2);

                System.out.println();

                System.out.println("Press any key to continue...");
                String input = sc.next();
                if (input.equals("exit"))
                    System.exit(0);
            }

            if (counter1 == 3) {
                ob.clear();
                ob.printPlayerGrids(grid, grid2);
                System.out.println("\nPLAYER 1: WINS BY " + (counter1 - counter2[0]) + " POINTS.");
                if (rounds > 1) {
                    System.out.println("Press any key to continue...");
                    String s = sc.next();
                }
            } else {
                ob.clear();
                ob.printPlayerGrids(grid, grid2);
                System.out.println("\nPLAYER 2: WINS BY " + (counter2[0] - counter1) + " POINTS.");
                if (rounds > 1) {
                    System.out.println("Press any key to continue...");
                    String s = sc.next();
                }
            }
            rounds--;
        }
    }

    boolean isValidInputSR(int r, int c) {
        return r >= 0 && r < 9 && c >= 0 && c < 14;
    }

    String[][] generateArraySR(String[][] grid, int player) {
        int k = 0;
        String s = "";

        while (k < 3) {
            int r = (int) (Math.random() * 8) + 1;
            int c = (int) (Math.random() * 13);
            if (!grid[r][c].equals(" ♒︎") && s.indexOf(Integer.toString(r)) < 0) {
                int len = (int) (Math.random() * (13 - c + 1)) + c;

                for (int m = c; m < c + len && m < 14; m++) {
                    grid[r][m] = Integer.toString(player);
                }
                s += Integer.toString(r);
                k++;
            }
        }
        return grid;
    }

    String[][] generateOrbsSR(String[][] grid) {
        int k = 0;
        while (k < 4) {
            int rbomb = (int) (Math.random() * 8) + 1;
            int cbomb = (int) (Math.random() * 13);
            int rorb = (int) (Math.random() * 8) + 1;
            int corb = (int) (Math.random() * 13);
            if (grid[rbomb][cbomb].equals("♒︎"))
                grid[rbomb][cbomb] = "💥";
            if (grid[rorb][corb].equals("♒︎"))
                grid[rorb][corb] = "💖";
            k++;
        }
        return grid;
    }

    void printArraySR(String[][] grid, int player) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                if (grid[i][j].equals(Integer.toString(player)) || !grid[i][j].equals("=") || grid[i][j].equals("💖") || grid[i][j].equals("💥")) {
                    System.out.print(String.format("%3s", "♒︎"));
                } else {
                    System.out.print(String.format("%3s", grid[i][j]));
                }
            }
            System.out.println();
        }
    }

    void printPlayerGridsSR(String[][] grid, String[][] grid2) {
        Battleship ob = new Battleship();
        System.out.println("=====================================================");
        ob.printArraySR(grid, 1);
        System.out.println("=====================================================");
        ob.printArraySR(grid2, 2);
        System.out.println("=====================================================");
        System.out.println();
    }

    String checkWinnerSR(int playerOpp, int r, int c, String[][] grid) {
        Battleship ob = new Battleship();
        String s = "";
        if (grid[r][c].equals(Integer.toString(playerOpp))) {
            grid[r][c] = "=";
            if (playerOpp == 1) {
                s = "2trueship";
                grid = ob.searchShip(r, c, grid, playerOpp);
            } else {
                s = "1trueship";
                grid = ob.searchShip(r, c, grid, playerOpp);
            }
        } else if (grid[r][c].equals("💥")) {
            grid[r][c] = " 💥";
            if (playerOpp == 1) {
                s = "2truebomb";
            } else {
                s = "1truebomb";
            }
        } else if (grid[r][c].equals("💖")) {
            grid[r][c] = " 💖";
            if (playerOpp == 1) {
                s = "2trueorb";
            } else {
                s = "1trueorb";
            }
        } else {
            s = "nil";
        }
        return s;
    }

    void ScoreRush(int rounds) {
        Battleship ob = new Battleship();
        Scanner sc = new Scanner(System.in);

        String grid[][] = new String[9][14];
        String grid2[][] = new String[9][14];

        while (rounds != 0) {
            // Initialize grids
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 14; j++) {
                    grid[i][j] = "♒︎";
                    grid2[i][j] = "♒︎";
                }
            }

            grid = ob.generateArraySR(grid, 1);
            grid = ob.generateOrbsSR(grid);

            grid2 = ob.generateArraySR(grid2, 2);
            grid2 = ob.generateOrbsSR(grid2);

            int counter1 = 0, counter2 = 0, k = 0;

            while (k <= 10) {
                ob.clear();
                ob.printPlayerGridsSR(grid, grid2);

                // Player 1:
                int p1r = 0, p1c = 0;
                while (true) {
                    System.out.print("Player 1: Enter row and column number: ");
                    String s1 = sc.next();
                    if (s1.equals("exit"))
                        System.exit(0);
                    String[] input = s1.split(",");
                    p1r = Integer.parseInt(input[0]) - 1;
                    p1c = Integer.parseInt(input[1]) - 1;

                    if (ob.isValidInputSR(p1r, p1c)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter valid row (1-9) and column (1-14) numbers.");
                    }
                }

                switch (ob.checkWinnerSR(2, p1r, p1c, grid2)) {
                    case "1trueship":
                        counter1 += 2;
                        System.out.println("Player 1 found a ship! (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    case "1truebomb":
                        counter1--;
                        System.out.println("Player 1 found a bomb 💥! (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    case "1trueorb":
                        counter1++;
                        System.out.println("Player 1 found an orb 💖! (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    case "nil":
                        System.out.println("No ship found... (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    default:
                        System.err.print("Error: Wrong value passed to func: checkWinnerSR(); PLAYER1");
                        break;
                }

                // Player 2:
                int p2r = -1, p2c = -1;
                while (true) {
                    System.out.print("Player 2: Enter row and column number: ");
                    String s2 = sc.next();
                    if (s2.equals("exit"))
                        System.exit(0);
                    String[] input = s2.split(",");
                    p2r = Integer.parseInt(input[0]) - 1;
                    p2c = Integer.parseInt(input[1]) - 1;

                    if (ob.isValidInputSR(p2r, p2c)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter valid row (1-9) and column (1-14) numbers.");

                    }
                }

                switch (ob.checkWinnerSR(1, p2r, p2c, grid)) {
                    case "2trueship":
                        counter2 += 2;
                        System.out.println("Player 2 found a ship! (Score: " + counter2 + ")");
                        System.out.println();
                        break;

                    case "2truebomb":
                        counter2--;
                        System.out.println("Player 2 found a bomb 💥! (Score: " + counter2 + ")");
                        System.out.println();
                        break;

                    case "2trueorb":
                        counter2++;
                        System.out.println("Player 2 found an orb 💖! (Score: " + counter2 + ")");
                        System.out.println();
                        break;

                    case "nil":
                        System.out.println("No ship found... (Score: " + counter2 + ")");
                        System.out.println();
                        break;

                    default:
                        System.err.print("Error: Wrong value passed to func: checkWinnerSR(); PLAYER1");
                        break;
                }

                System.out.println("Press any key to continue... ");
                String input = sc.next();
                if (input.equals("exit"))
                    System.exit(0);
            }
            k++;
            if (k == 10) {
                if (counter1>counter2) {
                    ob.clear();
                    ob.printPlayerGridsSR(grid, grid2);
                    System.out.println();
                    System.out.println("PLAYER 1: WINS BY " + (counter1 - counter2) + " POINTS.");
                    if (rounds > 1) {
                        System.out.println("Press any key to continue...");
                        String s = sc.next();
                        s = "";
                    }
                } else {
                    ob.clear();
                    ob.printPlayerGridsSR(grid, grid2);
                    System.out.println();
                    System.out.println("PLAYER 2: WINS BY " + (counter2 - counter1) + " POINTS.");
                    if (rounds > 1) {
                        System.out.println("Press any key to continue...");
                        String s = sc.next();
                        s = "";
                    }
                }
            }
            rounds--;
        }
    }

    void ScoreRushBot(int rounds) {
        Battleship ob = new Battleship();
        Scanner sc = new Scanner(System.in);

        String grid[][] = new String[9][14];
        String grid2[][] = new String[9][14];

        while (rounds != 0) {
            // Initialize grids
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 14; j++) {
                    grid[i][j] = "♒︎";
                    grid2[i][j] = "♒︎";
                }
            }

            grid = ob.generateArraySR(grid, 1);
            grid = ob.generateOrbsSR(grid);

            grid2 = ob.generateArraySR(grid2, 2);
            grid2 = ob.generateOrbsSR(grid2);

            int counter1 = 0, k = 0;
            int[] counter2 = { 0 };

            while (k <= 10) {
                ob.clear();
                ob.printPlayerGridsSR(grid, grid2);

                // Player 1:
                int p1r = 0, p1c = 0;
                while (true) {
                    System.out.print("Player 1: Enter row and column number: ");
                    String s1 = sc.next();
                    if (s1.equals("exit"))
                        System.exit(0);
                    String[] input = s1.split(",");
                    p1r = Integer.parseInt(input[0]) - 1;
                    p1c = Integer.parseInt(input[1]) - 1;

                    if (ob.isValidInputSR(p1r, p1c)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter valid row (1-9) and column (1-14) numbers.");
                    }
                }

                switch (ob.checkWinnerSR(2, p1r, p1c, grid2)) {
                    case "1trueship":
                        counter1 += 2;
                        System.out.println("Player 1 found a ship! (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    case "1truebomb":
                        counter1--;
                        System.out.println("Player 1 found a bomb 💥! (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    case "1trueorb":
                        counter1++;
                        System.out.println("Player 1 found an orb 💖! (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    case "nil":
                        System.out.println("No ship found... (Score: " + counter1 + ")");
                        System.out.println();
                        break;

                    default:
                        System.err.print("Error: Wrong value passed to func: checkWinnerSR(); PLAYER1");
                        break;
                }

                System.out.println();

                ob.botAlgorithm(grid, ob, counter2);

                System.out.println();

                System.out.println("Press any key to continue... ");
                String input = sc.next();
                if (input.equals("exit"))
                    System.exit(0);
            }
            k++;
            if (k == 10) {
                if (counter1>counter2[0]) {
                    ob.clear();
                    ob.printPlayerGridsSR(grid, grid2);
                    System.out.println();
                    System.out.println("PLAYER 1: WINS BY " + (counter1 - counter2[0]) + " POINTS.");
                    if (rounds > 1) {
                        System.out.println("Press any key to continue...");
                        String s = sc.next();
                        s = "";
                    }
                } else {
                    ob.clear();
                    ob.printPlayerGridsSR(grid, grid2);
                    System.out.println();
                    System.out.println("PLAYER 2: WINS BY " + (counter2[0] - counter1) + " POINTS.");
                    if (rounds > 1) {
                        System.out.println("Press any key to continue...");
                        String s = sc.next();
                        s = "";
                    }
                }
            }
            rounds--;
        }
    }

    public static void main() {
        Battleship ob = new Battleship();
        ob.mainMenu();
    }
}
