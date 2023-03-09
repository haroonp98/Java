package connectfour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Class Used to Read and Write the Grid data to File
 * @author
 *
 */
public class FileOperations {

  public static final String BOARD_FILE = "exampleboard.csv";

  /**
   * Method to Write the Grid data to the File provided
   * @param grid
   * @param fileName
   */
  public static void writeToFile(char[][] grid, String fileName) {
    PrintWriter out = null;

    try {
        //Create a PrintWriter to write the data to File
        //If the FileName is not provided, use the default file.
        if(fileName!=null && !fileName.isEmpty()) {
            out = new PrintWriter(new FileWriter(fileName));
        }else {
            out = new PrintWriter(new FileWriter(BOARD_FILE));
        }      
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int row = 0; row < grid.length; row++) {
      String inputText = "";
      for (int col = 0; col < grid[0].length; col++) {
        char value = '0';
        //Convert Blank,X,O to 0,1,2
        if(grid[row][col] == 'X') {
            value = '1';
        }else if(grid[row][col] == 'O') {
            value = '2';
        }
        if (inputText == "") {
          inputText = inputText + value;
        } else {
          inputText = inputText + "," + value;
        }

      }
      //Write the data to File
      out.println(inputText);
      out.flush();
    }

    out.close();
  }

  /**
   * Method to Load the Grid data from the File based on the User Input
   * @param fileName
   * @return
   */
  public static Scanner loadFromFile(String fileName) {
    Scanner scanner = null;
    try {
        // Create Scanner for reading the file
        //If the Input fileName from User is not null, Use that to load the file, else use default file
        if(fileName!=null && !fileName.isEmpty()) {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        }else {
            scanner = new Scanner(new BufferedReader(new FileReader(BOARD_FILE)));
        }
      
    } catch (IOException e) {
      //e.printStackTrace();
    }
    
    return scanner;
  }

    @Override
    public String toString() {
        return "FileOperations [toString()=" + super.toString() + "]";
    }
  
  
}
