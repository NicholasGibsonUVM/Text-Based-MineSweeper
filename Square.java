/**   Square is an abstract class
*/
public abstract class Square
{
   private String element;
   private boolean flagged;
   private boolean uncovered;
   
   /**   Constructor
         @param e the squares display
         @param f whether or not the square is flagged
         @param u wether or not the square is uncovered
   */
   public Square(String e, boolean f, boolean u)
   {
      element = e;
      flagged = f;
      uncovered = u;
   }
   
   /**   isFlagged
         @return flagged wheter or not the square is flagged
   */
   public boolean isFlagged()
   {
      return flagged;
   }
   
   /**   isUncovered
         @return uncovered wether or not the square is uncovered
   */
   public boolean isUncovered()
   {
      return uncovered;
   }
   
   /**   flagSquare sets flagged to true
   */
   public void flagSquare()
   {
      flagged = true;
   }
   
   /**   setUncovered sets uncovered to true
   */
   public void setUncovered()
   {
      uncovered = true;
   }
   
   /**   setElement
         @param e the element
   */
   public void setElement(String e)
   {
      element = e;
   }
   
   /**   toString returns the element as a string
   */
   @Override
   public String toString()
   {
      return String.format("%s ", element);
   }
   
   public abstract boolean uncover();
   
   public abstract boolean isMine();
}