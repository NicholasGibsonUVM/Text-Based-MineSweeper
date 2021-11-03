/**   The Minesweeper Class creates a board 
      and gives functions to interact that board
*/
public class Minesweeper
{
   private Grid board;
   private String action;
   private String[] actions;
   private Grid.Status status = Grid.Status.OK;
   
   /**   Constructor creates a new grid 
   */
   public Minesweeper(int w, int h, int numMines)
   {
      board = new Grid(w, h, numMines);
   }
   
   /**   performTurn runs through one turn of minesweeper
         @param action the user entered action string
   */
   public void performTurn(String action)
   {
      if (action.toUpperCase().charAt(0) == 'U')
      {
         actions = action.split("\\s+");
         status = board.uncoverSquare(Integer.parseInt(actions[1]), Integer.parseInt(actions[2])); 
      }
      else if (action.toUpperCase().charAt(0) == 'F')
      {
         actions = action.split("\\s+");
         board.flagSquare(Integer.parseInt(actions[1]), Integer.parseInt(actions[2])); 
      }
      else if (action.toUpperCase().charAt(0) == 'Q')
      {
         System.exit(0); 
      }
      
      if (status.equals(Grid.Status.WIN))
      {
         System.out.println(board);
         System.out.println("You Won!");
      }
      else if (status.equals(Grid.Status.MINE))
      {
         board.exposeMines();
         System.out.println(board);
         System.out.println("You Lost!");
      }
   }
   
   /**   getStatus
         @return the users status
   */
   public Grid.Status getStatus()
   {
      return status;
   }
   
   /**   toString returns the board and a question about the next action
         @return String
   */
   public String toString()
   {
      return String.format("%s\n%s\n%s", board.toString(), "What next?", "Options: (U)ncover r c, (F)lag r c, (Q)uit");  
   }
}