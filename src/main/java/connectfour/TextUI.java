package connectfour;

import java.util.Scanner;

/**
 * Class used to show all the Meesage to the User and get the Inputs from the User
 * @author
 *
 */
public class TextUI {

    /**
     * Method to display the Board with the current Grid.
     */

    public void displayBoard(char[][] grid) {
        String banner = "";
        for (int i = 0; i < grid.length; i++) {
            banner = banner + " " + i;
        }

        System.out.println(banner);
        System.out.println("---------------");
        for (int row = 0; row < grid.length; row++) {
            System.out.print("|");
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println(banner);
        System.out.println();
    }

  /**
     * Method to get the User Input for the Column.
     * @return
     */
  public String getUserInput(char player, Scanner scan) {

    System.out.print("Player " + player + ", choose a column: ");
    return scan.next();

  }

  /**
     * Method to print the final results.
     */
  public void printResult(boolean winner, char player) {
    if (winner) {
      System.out.println(player + " WON");
    } else {
      System.out.println("Tie game");
    }
  }

  /**
     * Input from User asking whether to Save the current board to File.
     */

  public void saveBoardInputMessage() {
    System.out.println("TYPE EXIT ANYTIME TO EXIT OR TYPE SAVE TO SAVE THE BOARD TO FILE");
  }

  /**
     * Input from User asking whether to load the current board from File.
     */
  public String loadBoardInput(Scanner scan) {
    System.out.println("WOULD YOU LIKE TO LOAD BOARD FROM THE SAVED FILE (Y/N) ? ");
    return scan.next();
  }
  
  public String getFileName(Scanner scan) {
      System.out.println("Enter the CSV File Name (Default exampleboard.csv) ");
      return scan.next();
    }

  public void displayError(String error) {
    System.err.println(error);
  }

  public void boardSaved() {
    System.out.println("CURRENT BOARD SAVED TO FILE");
  }
    
  public void existing() {
    System.out.println("GOOD BYE!");
  }

    @Override
    public String toString() {
        return "TextUI [toString()=" + super.toString() + "]";
    }
  
  
}
