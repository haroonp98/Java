package connectfour;

import java.util.Arrays;
import java.util.Scanner;
/**
 * This is the Board class used to represent the complete Board Structure
 * @author
 *
 */
public class Board {
  
  //Variable to represent the Grid along with Accessors and Mutators
  private char[][] grid;

  public char[][] getGrid() {
    return grid;
  }

  public void setGrid(char[][] gridC) {
    this.grid = gridC;
  }

  /**
     * Constructor to initialize the Grid with 6 * 7 Array.
     */
  public Board() {
    //initialize array.
    grid = new char[6][7];

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        grid[row][col] = ' ';
      }
    }
  }


  /**
     * Assign the Column selected to the current Player.
     */
  public void assignPlayer(int column, char player) {
    for (int row = getGrid().length - 1; row >= 0; row--) {
      if (getGrid()[row][column] == ' ') {
        getGrid()[row][column] = player;
        break;
      }
    }
  }

  /**
     * Validate the Move - Check if the Column selected has an empty row.
     */
  public boolean validate(int column) {
    if (column < 0 || column > grid[0].length) {
      return false;
    }

    if (grid[0][column] != ' ') {
      return false;
    }

    return true;
  }

  /**
     * Check if the Player is a winner.
     */
  public boolean isWinner(char player) {
    boolean isWinner = false;

    //check for 4 grids Horizontal.
    isWinner = checkHorizontal(player);

    //check for 4 grids Vertical.
    if (!isWinner) {
      isWinner = checkVertical(player);
    }

    //check for 4 grids diagonal.
    if (!isWinner) {
      isWinner = checkDiagonal(player);
    }

    return isWinner;
  }


  /**
     * Check if 4 Grids Horizontal has same Value.
     */
  private boolean checkHorizontal(char player) {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0;col < grid[0].length - 3;col++) {
        if (grid[row][col] == player 
                        && grid[row][col + 1] == player 
                        && grid[row][col + 2] == player 
                        && grid[row][col + 3 ] == player) {
          return true;
        }
      }           
    }
    return false;
  }

  /**
     * Check if 4 Grids Vertical has same Value.
     */
  private boolean checkVertical(char player) {
    for (int row = 0; row < grid.length - 3; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == player  
                        && grid[row + 1][col] == player 
                        && grid[row + 2][col] == player 
                        && grid[row + 3][col] == player) {
          return true;
        }
      }
    }
    return false;
  }

  /**
     * Check if 4 Grids Diagonal has same Value.
     */
  private boolean checkDiagonal(char player) {
    for (int row = 3; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length - 3; col++) {
        if (grid[row][col] == player 
                        && grid[row - 1][col + 1] == player
                        && grid[row - 2][col + 2] == player 
                        && grid[row - 3][col + 3] == player) {
          return true;
        }
      }
    }

    for (int row = 0; row < grid.length - 3; row++) {
      for (int col = 0; col < grid[0].length - 3; col++) {
        if (grid[row][col] == player 
                        && grid[row + 1][col + 1] == player
                        && grid[row + 2][col + 2] == player 
                        && grid[row + 3][col + 3] == player) {
          return true;
        }
      }
    }
    return false;
  }


  
  
  public int getTurn() {
      int turn = 1;
    //Decide the Player turn based on the number of elements in the Grid
      for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
          if (grid[row][col] != ' ') {
            turn++;
          }
        }
      }
      return turn;
  }
  
  public void setGridFromFileData(Scanner scanner) {
      String currentLine;
      // get the next line in the file
      while (scanner.hasNextLine()) {
        for (int row = 0; row < grid.length; row++) {
          currentLine = scanner.nextLine();
          String[] currentLineArray = currentLine.split(",");
          for (int col = 0; col < grid[0].length; col++) {
              //Convert 0,1,2 to Blank,X and O
              if(currentLineArray[col].charAt(0) == '1') {
                  grid[row][col] = 'X';
              }else if(currentLineArray[col].charAt(0) == '2') {
                  grid[row][col] = 'O';
              }else {
                  grid[row][col] = ' ';
              }
          }
        }

      }
  }


  /**
   * ToString method to represent the Board Object with Grid information 
   */
  public String toString() {
    return "Board [grid=" + Arrays.toString(grid) + "]";
  }

}
