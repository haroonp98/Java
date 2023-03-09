package connectfour;

import java.util.Scanner;

/**
 * Main Class to Start the Game. This class Initializes the Object of other classes
 * @author
 *
 */
public class ConnectFour {

  private static Scanner in = new Scanner(System.in);

  /**
     * .Method to set the Next Player Turn based on current Player
     */
  private static char setNextPlayer(char turn) {
    if (turn == 'X') {
      turn = 'O';
    } else {
      turn = 'X';
    }

    return turn;
  }

  /**
     * Main Method to Set the Turn, Call the Board and Invoke TextUI to display messages.
     */
  public static void main(String[] args) {
    Board board = new Board();
    TextUI textUI = new TextUI();

    int turn = 1;
    char playerTurn = 'O';

    //Need to load the saved board from file.
    String loadResponse = textUI.loadBoardInput(in);
        
    //If any other input other than Y or N - Show invalid choice
    while (!loadResponse.equalsIgnoreCase("Y") 
                && !loadResponse.equalsIgnoreCase("N")) {
      textUI.displayError("Invalid choice - Try Again!");
      loadResponse = textUI.loadBoardInput(in);
    }
    
    //If User chooses to Load the board from File
    if (loadResponse.equalsIgnoreCase("Y")) {
      //Get the File name from the User  
      String fileName = textUI.getFileName(in);
      try {
          //Load the given File
          Scanner scanner = FileOperations.loadFromFile(fileName);
          //Set the Obtained Grid object from File to the Board
          if(scanner != null) {
              board.setGridFromFileData(scanner);
              turn = board.getTurn();
              //Reset the player based on number of turns - If Even then X else O
              if (turn % 2 == 0) {
                playerTurn = 'X';
              }
          }
      }catch(Exception e) {
          //If any error in loading the file, load default empty board
          textUI.displayError("Error loading file - Proceeding with empty board");
      }
      
    }
    //Static Message
    textUI.saveBoardInputMessage();
    
    //Invoke the Static playBoard method to start the Play
    playBoard(playerTurn,turn,board,textUI);
        
  }
  
  /**
   * Private Method to Play the Board
   * @param playerTurn
   * @param turn
   * @param board
   * @param textUI
   */
  private static void playBoard(char playerTurn,int turn,Board board,TextUI textUI) {
    boolean winner = false;
    //play a turn till a winner is found or till 42 turns over
    while (!winner && turn <= 42) {
      boolean validPlay;
      String userInput;
      int column = 0;

      // Set the Player turns
      playerTurn = setNextPlayer(playerTurn);

      do {
        //Display the current board
        textUI.displayBoard(board.getGrid());

        //Get the User Input for the next Play
        userInput = textUI.getUserInput(playerTurn, in);
        
        //Invoke the Method to Save or Exit the Board
        userInput = saveOrExitBoard(userInput, playerTurn,board,textUI);
                
        try {
          column = Integer.valueOf(userInput);
          //validate play
          validPlay = board.validate(column);
        } catch (Exception e) {
          validPlay = false;
        }
        //If the play is not valid, display error message        
        if (!validPlay) {
          textUI.displayError("Invalid Move - Try Again!");
        }

      } while (!validPlay);

      //Assign the column selected to the player
      board.assignPlayer(column,playerTurn);

      //determine if there is a winner
      winner = board.isWinner(playerTurn);
      
      //Increment the Turn
      turn++;
    }
    //Print the Final Result of the Game
    printFinalResults(playerTurn,board,textUI,winner);
  }
    
  /**
   * Method to Save or Exit the Board based on User Input  
   * @param userInput
   * @param playerTurn
   * @param board
   * @param textUI
   * @return
   */
  private static String saveOrExitBoard(String userInput,char playerTurn,
      Board board,TextUI textUI) {
    if (userInput.equalsIgnoreCase("SAVE")) {
      String fileName = textUI.getFileName(in);
      FileOperations.writeToFile(board.getGrid(),fileName);
      textUI.boardSaved();
      userInput = textUI.getUserInput(playerTurn, in);
    }
        
    if (userInput.equalsIgnoreCase("EXIT")) {
      textUI.existing();
      System.exit(0);
    }
        
    return userInput;
  }
  
  /**
   * Method to Print the Final Results
   * @param playerTurn
   * @param board
   * @param textUI
   * @param winner
   */
  private static void printFinalResults(char playerTurn,Board board,TextUI textUI,boolean winner) {
    //Display Final Board Status
    textUI.displayBoard(board.getGrid());

    //Print the Winner
    textUI.printResult(winner, playerTurn);
  }

    @Override
    public String toString() {
        return "ConnectFour [toString()=" + super.toString() + "]";
    }
  
  

}
