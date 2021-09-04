package battleship;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        // Write your code here
        Grid myGrid = new Grid(10, 10);
        Grid myGrid2 = new Grid(10, 10);
        myGrid.makeGrid();
        myGrid2.makeGrid();
        Ship aircraftCarrier = new Ship(5, "Aircraft Carrier", "A");
        Ship battleship = new Ship(4, "Battleship", "B");
        Ship submarine = new Ship(3, "Submarine", "C");
        Ship cruiser = new Ship(3, "Cruiser", "D");
        Ship destroyer = new Ship(2, "Destroyer", "E");

        Ship aircraftCarrier2 = new Ship(5, "Aircraft Carrier", "A");
        Ship battleship2 = new Ship(4, "Battleship", "B");
        Ship submarine2 = new Ship(3, "Submarine", "C");
        Ship cruiser2 = new Ship(3, "Cruiser", "D");
        Ship destroyer2 = new Ship(2, "Destroyer", "E");

        Ship[] ships = {aircraftCarrier, battleship, submarine, cruiser, destroyer};
        Ship[] ships2 = {aircraftCarrier2, battleship2, submarine2, cruiser2, destroyer2};
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlayer 1, place your ships on the game field\n");
        myGrid.printGrid();
        boolean stopIt;
        for (Ship ship : ships) {
            stopIt = false;
            ship.askCoord(myGrid.getFields(), myGrid.getRows(), myGrid.getColumns());
            while (!stopIt) {
                ship.enterCoord(scanner.next(), scanner.next());
                stopIt = ship.getStopIt();
                if (stopIt) {
                    myGrid.printGrid();
                    myGrid.setFields(ship.getFields());
                }
            }
        }
        promptEnterKey();

        System.out.println("Player 2, place your ships on the game field\n");
        myGrid2.printGrid();
        for (Ship ship2 : ships2) {
            stopIt = false;
            ship2.askCoord(myGrid2.getFields(), myGrid2.getRows(), myGrid2.getColumns());
            while (!stopIt) {
                ship2.enterCoord(scanner.next(), scanner.next());
                stopIt = ship2.getStopIt();
                if (stopIt) {
                    myGrid2.printGrid();
                    myGrid2.setFields(ship2.getFields());
                }
            }
        }

        boolean stopIt1 = false;
        boolean stopIt2 = false;
        int player = 1;
        while (!stopIt1 && !stopIt2) {
            promptEnterKey();
            if (player == 1) {
                myGrid2.printGridBlinded();
                System.out.println("---------------------");
                myGrid.printGrid();
                System.out.println("\nPlayer 1, it's your turn:\n");
                player++;
                stopIt1 = myGrid2.shot(scanner.next());
            } else if (player == 2) {
                myGrid.printGridBlinded();
                System.out.println("---------------------");
                myGrid2.printGrid();
                System.out.println("\nPlayer 2, it's your turn:\n");
                player--;
                stopIt2 = myGrid.shot(scanner.next());
            }

        }
        System.out.println(stopIt1 ? "Player 1, You Won!": "Player 2, You Won!");
    }
    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Grid {
    private final int rows;
    private final int columns;
    private String[][] fields;
    int[] score = {5, 4, 3, 3, 2};
    int scoreSum = 17;

    public void setFields(String[][] fields) {
        this.fields = fields;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String[][] getFields() {
        return fields;
    }


    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        fields = new String[rows][columns];
    }

    public void makeGrid() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                fields[i][j] = "~";
            }
        }
    }

    public void printGridBlinded() {
        for (int i = 0; i <= this.columns; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        char colChar = 'A';
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (j == 0) {
                    System.out.print(colChar + " ");
                    colChar++;
                }
                if ("A".equals(this.fields[i][j]) || "B".equals(this.fields[i][j]) || "C".equals(this.fields[i][j]) ||
                        "D".equals(this.fields[i][j]) || "E".equals(this.fields[i][j])) {
                    System.out.print("~ ");
                } else {
                    System.out.print(this.fields[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    public void printGrid() {
        for (int i = 0; i <= this.columns; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        char colChar = 'A';
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (j == 0) {
                    System.out.print(colChar + " ");
                    colChar++;
                }
                if ("A".equals(this.fields[i][j]) || "B".equals(this.fields[i][j]) || "C".equals(this.fields[i][j]) ||
                        "D".equals(this.fields[i][j]) || "E".equals(this.fields[i][j])) {
                    System.out.print("O ");
                } else {
                    System.out.print(this.fields[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    public boolean shot(String shot) {
        int shotRow = shot.substring(0, 1).charAt(0) -'A';
        int shotCol = Integer.parseInt(shot.substring(1)) - 1;
        try  {
            String target = this.fields[shotRow][shotCol];
            if ("A".equals(target) || "B".equals(target) || "C".equals(target) || "D".equals(target) || "E".equals(target)) {
                int index = target.charAt(0) - 'A';
                this.score[index]--;
                this.scoreSum--;
                this.fields[shotRow][shotCol] = "X";
                if (this.score[index] == 0 && this.scoreSum > 0) {
                    System.out.println("\nYou sank a ship!:\n");
                    return  false;
                } else if (score[index] == 0 && this.scoreSum == 0){
                    System.out.println("\nYou sank the last ship. You won. Congratulations!\n");
                    return true;
                } else {
                    System.out.println("\nYou hit a ship!\n");
                    return  false;
                }

            } else if ("X".equals(target) || ("M".equals(target))) {
                System.out.println("\nYou repeated target!\n");
                return  false;
            } else {
                this.fields[shotRow][shotCol] = "M";
                System.out.println("\nYou missed!\n");
                return  false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nError! You entered the wrong coordinates!\n");
            return  false;
        }
    }
}
class Ship {

    int cells;
    String orientation;
    String name;
    static String sign;
    String[] cellState;

    String[][] fields;

    public String[][] getFields() {
        return fields;
    }

    int rows;
    int columns;

    Boolean stopIt = false;

    public Boolean getStopIt() {

        return stopIt;
    }

    int aRow;
    int aCol;
    int bRow;
    int bCol;

    public Ship(int cells, String name, String sign) {
        this.cells = cells;
        this.name = name;
        this.cellState = new String[cells];
        Ship.sign = sign;
        Arrays.fill(this.cellState, sign);
    }

    public void askCoord(String[][] fields, int rows, int columns) {
        this.fields = fields;
        this.rows = rows;
        this.columns = columns;
        System.out.printf("%nEnter the coordinates of the %s (%d cells):%n", this.name, this.cells);
    }
    public void enterCoord(String a, String b) {
        int aXRowTemp = a.substring(0, 1).charAt(0) -'A';
        int aYColTemp = Integer.parseInt(a.substring(1)) - 1;
        int bXRowTemp = b.substring(0, 1).charAt(0) - 'A';
        int bYColTemp = Integer.parseInt(b.substring(1)) - 1;

        if (aXRowTemp >= 0 && aYColTemp >= 0 && bXRowTemp >= 0 && bYColTemp >= 0 && aXRowTemp < this.rows
                && aYColTemp < this.columns && bXRowTemp < this.rows && bYColTemp < this.columns &&
                (aXRowTemp == bXRowTemp ^ aYColTemp == bYColTemp)) {
            if (Math.abs(aXRowTemp - bXRowTemp) + 1 == this.cells || Math.abs(aYColTemp - bYColTemp) + 1 == this.cells) {
                this.aRow = aXRowTemp;
                this.aCol = aYColTemp;
                this.bRow = bXRowTemp;
                this.bCol = bYColTemp;
                this.orientation = shipOrientation();
                if (!isContact()) {
                    shipOnGrid();
                    //Grid.printGrid(fields, rows, columns);
                    this.stopIt = true;
                } else {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }
            } else {
                System.out.printf("Error! Wrong length of the %s! Try again:%n", this.name);
            }
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
        }
    }
    int sideRow1; //first point of the grid to see if ship contact anything
    int sideCol1; //first point of the grid to see if ship contact anything
    int sideRow2; //second point of the grid to see if ship contact anything
    int sideCol2; //second point of the grid to see if ship contact anything

    private String shipOrientation() {
        if (this.aRow == this.bRow) {
            if (this.aCol < this.bCol) {
                this.sideRow1 = this.aRow == 0 ? 0 : this.aRow - 1;
                this.sideCol1 = this.aCol == 0 ? 0 : this.aCol - 1;
                this.sideRow2 = this.bRow == this.rows - 1 ? this.bRow : this.bRow + 1;
                this.sideCol2 = this.bCol == this.columns - 1 ? this.bCol : this.bCol + 1;
                return "EAST";
            } else {
                this.sideRow1 = this.bRow == 0 ? 0 : this.bRow - 1;
                this.sideCol1 = this.bCol == 0 ? 0 : this.bCol - 1;
                this.sideRow2 = this.aRow == this.rows - 1 ? this.aRow : this.aRow + 1;
                this.sideCol2 = this.aCol == this.columns - 1 ? this.aCol : this.aCol + 1;
                return "WEST";
            }
        } else {
            if (this.aRow < this.bRow) {
                this.sideRow1 = this.aRow == 0 ? 0 : this.aRow - 1;
                this.sideCol1 = this.aCol == 0 ? 0 : this.aCol - 1;
                this.sideRow2 = this.bRow == this.rows - 1 ? this.bRow : this.bRow + 1;
                this.sideCol2 = this.bCol == this.columns -1 ? this.bCol : this.bCol + 1;;
                return "SOUTH";
            } else {
                this.sideRow1 = this.bRow == 0 ? 0 : this.bRow - 1;
                this.sideCol1 = this.bCol == 0 ? 0 : this.bCol - 1;
                this.sideRow2 = this.aRow == this.rows - 1 ? this.aRow : this.aRow + 1;
                this.sideCol2 = this.aCol == this.columns -1 ? this.aCol : this.aCol + 1;
                return "NORTH";
            }
        }
    }
    private boolean isContact() {
        for (int i = this.sideRow1; i <= this.sideRow2; i++) {
            for (int j = this.sideCol1; j <= this.sideCol2; j++) {
                if (!"~".equals(fields[i][j])) {
                    return true;
                }
            }
        }

        return false;
    }

    private void shipOnGrid() {
        String[][] fields = this.fields.clone();
        for (int i = 0; i < this.cellState.length; i++) {
            if ("EAST".equals(this.orientation)) {
                fields[this.aRow][this.aCol + i] = this.cellState[i];
            } else if ("WEST".equals(this.orientation)) {
                fields[this.aRow][this.aCol - i] = this.cellState[i];
            } else if ("SOUTH".equals(this.orientation)) {
                fields[this.aRow + i][this.aCol] = this.cellState[i];
            } else {
                fields[this.aRow - i][this.aCol] = this.cellState[i];
            }
        }
        this.fields = fields.clone();
    }
}
