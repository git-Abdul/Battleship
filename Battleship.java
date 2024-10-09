package Project;

import java.util.*;
class Battleship {

    String[][] generateArray(String[][] grid, int player) {
        int k = 0;
        String s = "";

        while (k < 3) {
            int r = (int) (Math.random() * 6)+1;
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
                if (!grid[i][j].equals(Integer.toString(player)) && !grid[i][j].equals("=")) {
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

    String[][] searchShip(int r, int c, String [][] grid, int playerOpp) {
        int player = (playerOpp == 1)? 2: 1;
        for(int i = 0; i<=11; i++) {
            if(grid[r][i].equals(Integer.toString(playerOpp))) {
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

    public static void main(String[] args) {
        Battleship ob = new Battleship();
        Scanner sc = new Scanner(System.in);
        String grid[][] = new String[7][12];
        String grid2[][] = new String[7][12];

        // Initialize grids
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                grid[i][j] = "♒︎";
                grid2[i][j] = "♒︎";
            }
        }

        grid = ob.generateArray(grid, 1);
        grid2 = ob.generateArray(grid2, 2);

        System.out.println("Press any key to continue: ");
        String in = sc.next();
        if(in.equals("exit"))
            System.exit(0);
        ob.clear();

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
            } else {
                System.out.println("No ship found...");
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
                    break; // Valid input
                } else {
                    System.out.println("Invalid input. Please enter valid row (1-7) and column (1-12) numbers.");
                }
            }

            if (ob.checkWinner(1, p2r, p2c, grid).equals("2true")) {
                counter2++;
                System.out.println("Player 2 found a ship!");
            } else {
                System.out.println("No ship found...");
            }

            System.out.println("Press any key to continue... ");
            String input = sc.next();
            if(input.equals("exit"))
                System.exit(0);
        }
        if(counter1 == 3) {
            ob.clear();
            ob.printPlayerGrids(grid, grid2);
            System.out.println();
            System.out.println("PLAYER 1: WINS BY "+(counter1-counter2)+" POINTS.");
        } else {
            ob.clear();
            ob.printPlayerGrids(grid, grid2);
            System.out.println();
            System.out.println("PLAYER 2: WINS BY "+(counter2-counter1)+" POINTS.");
        }
    }
}
