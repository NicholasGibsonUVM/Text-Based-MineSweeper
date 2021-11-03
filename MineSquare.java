/**   A MineSquare is a square the causes the player to lose
      if uncovered  
*/
public class MineSquare extends Square
{
   /**   Constructor creates a square
   */
   public MineSquare()
   {
      super("*", false, false);
   }
   
   /**   uncover sets the element of the minesquare to X
         @return wether or not this was succesful
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
         super.setElement("X");
         return true;
      }

   }
   
   /**   isMine lets you access that a minesquare is a mine
         @return true
   */
   public boolean isMine()
   {
      return true;
   }
}