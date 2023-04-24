import javax.swing.*;

public class Main
{
   public static void main(String[] args)
   {
      Deck deck = new Deck(1);            // New deck object (argument is for number of decks)
      Player user = new Player(11);       // User
      Player dealer = new Player(11);     // Deep Thought V4.2
      String turn;                        // Indicates the status of a turn(initialized to h so that the loop is entered and 
      String turnD = "";                  // Status of dealer's turn
      
      // Initiate dynamo Sybil Cut shuffle w/ arcing spring flair
      deck.shuffle();
      
      // User takes two cards
      user.hit(deck.draw(2));
      
      // Deep Thought discovers the meaning of life... and then draws two cards
      dealer.hit(deck.draw(2));
      
      do
      {
         // Display the user's hand and the dealer's first card (the position at 0 in a hand is always empty)
         turn = JOptionPane.showInputDialog(
                                            "Your hand:\n" +
                                            user.getHand() + 
                                            "\nDealer card:\n" +
                                            dealer.getHand(1) +
                                            "\n\nHit(h) or Stand(s)?\n");
         // Draw 1 card if 'hit' is chosen
         if (turn.equalsIgnoreCase("h"))
         {
            user.hit(deck.draw(1));
         
         }
         // Stroke user's ego and exit turn with 'blackjack' status
         if (user.sumHand() == 21)
         {
            JOptionPane.showMessageDialog(null, "Your hand:\n" +
                                          user.getHand() + 
                                          "\nDealer card:\n" +
                                          dealer.getHand(1) +
                                          "\n\nPlayer Blackjack!\n");
            turn = "blackjack";
         }
         
         // User loses if above 21... turn is exited with 'bust' status
         else if (user.sumHand() > 21)
         {
            JOptionPane.showMessageDialog(null, "Your hand:\n" +
                                          user.getHand() + 
                                          "\nDealer card:\n" +
                                          dealer.getHand(1) +
                                          "\n\nPlayer Bust!\n");
            turn = "bust";
         }
      
      // Evaluate turn status for another chance to draw
      } while (turn.equalsIgnoreCase("h"));
      
      /* It might be useful to add the AI methods to the Player class.
       * The 'turn' variable could also be utilized to determine the
       * winner of each round. If not, perhaps various comparisons via the
       * sumHand method would be of use... The following while loop is just a dummy AI so 
       * a complete game can be tested and viewed; truncate this loop if you plan to
       * test AI
       */
      
      while (dealer.basicStrategy(user.getValue(1), dealer.sumHand()))
      {
         dealer.hit(deck.draw(1));
         if (dealer.sumHand() == 21)
         {
            JOptionPane.showMessageDialog(null, "Your hand:\n" +
                                          user.getHand() + 
                                          "\nDealer hand:\n" +
                                          dealer.getHand() +
                                          "\n\nDealer Blackjack!\n");
            turnD = "blackjack";
         }
         
         else if (dealer.sumHand() > 21)
         {
            JOptionPane.showMessageDialog(null, "Your hand:\n" +
                                          user.getHand() + 
                                          "\nDealer hand:\n" +
                                          dealer.getHand() +
                                          "\n\nDealer Bust!\n");
            turnD = "bust";
         }   
      }
      
      if (turn.equals(turnD) || dealer.sumHand() == user.sumHand())
      {
         JOptionPane.showMessageDialog(null, "Tie!");
      }
      else if (dealer.sumHand() > user.sumHand())
      {
         JOptionPane.showMessageDialog(null, "You Win!");
      }
      else
      {
         JOptionPane.showMessageDialog(null, "You Lose!");
      }
      
      
      System.exit(0);
   }
}