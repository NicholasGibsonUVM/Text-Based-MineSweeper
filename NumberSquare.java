/**   NumberSquare is a square that can have nieghboring mines
*/
public class NumberSquare extends Square
{
   private int neighborMines;
   private int myRow;
   private int myCol;
   
   /**   Constructor 
         @param neighborMines the number of mines in a 3x3 area around the square
         @param myRow the row number
         @param myCol the col number
   */
   public NumberSquare(int neighborMines, int myRow, int myCol)
   {
      super("*", false, false);
      this.neighborMines = neighborMines;
      this.myRow = myRow;
      this.myCol = myCol;
   }
   
   /**   uncover uncovers the square and sets the elements to either
         the neighboring mines or - if there are no neighbors
         @return wether the function was succesful
   */
   public boolean uncover()
   {
      if (super.isFlagged())
      {
         return false;
      }
      else
      {
         super.setUncovered();
         if (neighborMines == 0)
         {
            super.setElement("-");
         }
         else
         {
            super.setElement(String.valueOf(neighborMines));
         }
         return true;
      }
   }
   
   /**   getNeighborMines
         @return neighborMines the nuber of mines surrounding the Square
   */
   public int getNeighborMines()
   {
      return neighborMines;
   }
   
   /** isMines always returns false for a numberSqaure
   */
   public boolean isMine()
   {
      return false;
   }
}
