import java.util.Scanner;

/**   Minesweeper
      @author Nicholas Gibson
      I would like to be considered for extra credit
      #3-Allow the user to select a level when they start
      #4-Allow the user to restart the game
      #6-Use recursion and implement the true game of Minesweeper
*/
public class Driver
{
   public static void main(String[] args)
   {
      int rows = 0;
      int cols = 0;
      int numMines = 0;
      boolean loop = true;
      boolean playing = true;
   
      Scanner data = new Scanner(System.in);
      
      //Encase the game in a while loop to allow replayability
      while (playing == true)
      {
         String difficulty = null;
         
         System.out.println("Difficulty\n1. (E)asy\n2. (M)edium\n3. (H)ard");
         
         //Allow the user to select a difficulty with input varification
         while (loop == true)
         {
            difficulty = data.nextLine();
            
            if (difficulty.toUpperCase().equals("E"))
            {
               rows = 8;
               cols = 8;
               numMines = 8;
               loop = false;
            }
            else if (difficulty.toUpperCase().equals("M"))
            {
               rows = 10;
               cols = 12;
               numMines = 10;
               loop = false;
            }
            else if (difficulty.toUpperCase().equals("H"))
            {
               rows = 16;
               cols = 20;
               numMines = 50;
               loop = false;
            }
            else
            {
               System.out.println("Please enter E, M, or H");
               loop = true;
            }
         }
         
         //Instantiate the minesweeper game
         Minesweeper board = new Minesweeper(cols, rows, numMines);
         
         //Perform turns until the user wins or loses
         while(board.getStatus().equals(Grid.Status.OK))
         {
            System.out.println(board);
            String action = data.nextLine();
            board.performTurn(action);
         }
         
         //Ask whether or not the user wants to start again
         boolean loopTwo = true;
         while (loopTwo == true)
         {
            System.out.println("Would you like to play again? (Y/N) ");
            String replay = data.nextLine();
            
            if (replay.toUpperCase().equals("Y"))
            {
               playing = true;
               loopTwo = false;
            }
            else if (replay.toUpperCase().equals("N"))
            {
               playing = false;
               loopTwo = false;
            }
            else
            {
               System.out.println("Please enter Y or N");
               loopTwo = true;
            }
         }
      }
   }
}