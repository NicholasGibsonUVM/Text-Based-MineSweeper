import java.util.Random;

/** Grid class creates a two dimensional array of squares
*/
public class Grid
{
   private Square[][] grid;
   private int width;
   private int height;
   private int numMines;
   private int numSquaresUncovered;
   
   /**   Status is an enumerated type
   */
   public enum Status
   {
      OK, 
      WIN, 
      MINE
   }
   
   /**   Constructor that fills the grid with numbersquares and minesquares
         @param w the width
         @param h the height
         @param numMines the number of mines
   */
   public Grid(int w, int h, int numMines)
   {
      width = w;
      height = h;
      this.numMines = numMines;
      numSquaresUncovered = 0;
      
      grid = new Square[height][width];
      fillGrid();
   }
   
   /**   fillGrid starts by putting mines in random locations
         then it fills the remaining squares with number squares with
         their coresponding neighbormines
   */
   public void fillGrid()
   {
      Random rand = new Random();
     
      for (int i = 0; i < numMines; i++)
      {
         int mineRow = rand.nextInt(height - 1);
         int mineCol = rand.nextInt(width - 1);
         if (grid[mineRow][mineCol] == null)
         {
            grid[mineRow][mineCol] = new MineSquare();
         }
         else
         {
            i++;
         }
      }
      
      for (int i = 0; i < height; i++)
      {
         for (int j = 0; j < width; j++)
         {
            if (grid[i][j] == null)
            {
               int neighborMines = getNeighbors(i,j);
               grid[i][j] = new NumberSquare(neighborMines, i, j);
            }
         }
      }      
   } 
   
   /**   getNeighbors returns the number of mines
         around a square
         @param r the row number
         @param c the column number
         @return number of neighbors
   */
   public int getNeighbors(int r, int c)
   {
      int total = 0;
      
      //check a 3x3 area around (r, c)
      for (int i = -1; i <= 1; i++)
      {
         for (int j= -1; j <= 1; j++)
         {
            // if (r, c) is on an edge or corner an index out of bounds
            // exception will be thrown catch it and move on
            try
            {
               if (grid[r+i][c+j].isMine())
               {
                  total++;
               }
            }
            
            catch (ArrayIndexOutOfBoundsException e)
            {
               
            }
            
            catch (NullPointerException e)
            {
            
            }
         }
      }
      
      //return the number of mines
      
      return total;
   }
   
   /**   recursiveUncover uncovers every square with
         no neighbor until it reachs either a mine or a square with neighbors
         @param r the row number
         @param c the column number
   */
   public void recursiveUncover(int r, int c)
   {
      for (int i = -1; i <= 1; i++)
      {
         for (int j = -1; j <= 1; j++)
         {
            try //This stops the program from crashing if it goes off the edge of the grid
            {
               if (getNeighbors(r+i, c+j) == 0 && !(grid[r+i][c+j].isMine()) && !(grid[r+i][c+j].isUncovered()))
               {
                  numSquaresUncovered++;
                  grid[r+i][c+j].uncover();
                  recursiveUncover(r+i, c+j);
               }
               else if (getNeighbors(r+i, c+j) > 0 && !(grid[r+i][c+j].isMine()) && !(grid[r+i][c+j].isUncovered()))
               {
                  numSquaresUncovered++;
                  grid[r+i][c+j].uncover();
               }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {}
         }
      }
   }
   
   /**   uncoverSquare uncovers a square and checks whether it is a
         mine or a square with neighbors and then uncovers just that square
         and returns the appropiate status. However, if the square has no
         neighbors it calls the recursive uncover function
         @param r the row number
         @param c the column number
         @return the condition (Win, Lose, Okay)
   */
   public Status uncoverSquare(int r, int c)
   {
      if (grid[r][c].isMine())
      {
         return Status.MINE;
      }
      else if (getNeighbors(r, c) > 0)
      {
         numSquaresUncovered++;
         grid[r][c].uncover();
      }
      else
      {
         grid[r][c].uncover();
         numSquaresUncovered++;
         recursiveUncover(r, c);
      }
      
      if (numSquaresUncovered == (height * width - numMines))
         return Status.WIN;
      else
         return Status.OK;      
   }
   
   /**   exposeMines uncovers all the mines on the board
   */
   public void exposeMines()
   {
      for (int i = 0; i < height; i++)
      {
         for (int j = 0; j < width; j++)
         {
            if (grid[i][j].isMine())
            {
               grid[i][j].uncover();
            }
         }
      }
   }
   
   /**   flagSquare changes the element of a square to F
         @param r the row number
         @param c the column number
   */
   public void flagSquare(int r, int c)
   {
      grid[r][c].flagSquare();
      grid[r][c].setElement("F");
   }
   
   /**   toString returns the board printed in a grid style
   */
   public String toString()
   {
      String displayGrid = "   ";
      for (int i = 0; i < width; i++)
      {
         displayGrid += String.valueOf(i);
         if (i < 10)
         {
            displayGrid += "  ";
         }
         else
         {
            displayGrid += " ";
         }
      }
      
      displayGrid += "\n";
      
      for (int i = 0; i < height; i++)
      {
         displayGrid += String.valueOf(i);
         if (i < 10)
         {
            displayGrid += "  ";
         }
         else
         {
            displayGrid += " ";
         }
         for (int j = 0; j < width; j++)
         {
            displayGrid += grid[i][j].toString();
            displayGrid += " ";
         }
         displayGrid += "\n";
      } 
      return displayGrid;
   }
}