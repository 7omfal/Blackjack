public class Player
{
   private String[] hand;     // The cards in a hand
   private int[] values;      // The integer value of each card in a hand
   
   /**
    * Constructor for creating a hand object with a specified maximum number of cards per hand
    */
   public Player(int maxCards)
   {
      hand = new String[maxCards];
      values = new int[maxCards];
   }
   
   /**
    * Draws a number of cards from the deck and moves each card up by the number drawn
    * @param cards A string array that represents cards being drawn
    */
   public void hit(String[] cards)
   {
      // Repeat for each card in the array 
      for (int i = 0; i < cards.length; i++)
      {
         // The index 0 is always empty and ready to receive a card
         hand[0] = cards[i];
         
         /*
           Move each card in the hand up one position (0 is set to an empty string (""))
           This is essentially the reverse of the action that is done on the deck to remove cards
         */
         for (int j = hand.length - 1; j >= 0; j--)
         {
            try
            {
               hand[j] = hand[j - 1];
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
               hand[j] = "";
            }
         }
      }
      // Set integer values (See Deck class for note in similar code)
      for (int i = 0; i < hand.length; i++)
      {
         // Do nothing if the position is empty or null
         if (!(hand[i] == null || hand[i].equals("")))
         {
            // Ace initialized to eleven
            if (hand[i].substring(0, 1).equals("a"))
            {
               values[i] = 11;
            }
            // All royals are ten (as well as ten itself)
            else if (hand[i].substring(0, 1).equals("j") || hand[i].substring(0, 1).equals("q") ||
                     hand[i].substring(0, 1).equals("k") || hand[i].substring(0, 1).equals("t"))
            {
               values[i] = 10;
            }
            else
            {
               values[i] = Integer.parseInt(hand[i].substring(0, 1));
            }
         }
      }
   }
   
   /**
    * Sums up the user's current hand
    * @return The sum of the hand after the consideration of so-called 'hard aces'
    */
   public int sumHand()
   {
      int sum = 0;
      boolean isHardAce = false;  // Evaluates if a hard ace is in the hand
      int aceIndex = 0;           // Necessary for changing the ace's value inside this class
      
      // Gradually add each value of the hand into the sum
      for (int i = 0; i < hand.length; i++)
      {
         sum += values[i];
         if (values[i] == 11)
         {
            isHardAce = true;
            aceIndex = i;
         }
      }
      if (sum > 21 && isHardAce)
      {
         // Switch the ace's value from 11 to 1 and treat the sum as if the ace had been 1 all along
         sum -= 10;
         values[aceIndex] = 1;
      }
      return sum;
   }
   
   /**
    * @return All of the integer values present in a hand
    */
   public int[] getValue()
   {
      return values;
   }
   
   /**
    * @param index Index of value to be returned
    * @return One of the integer values present in a hand
    */
   public int getValue(int index)
   {
      return values[index];
   }

   /**
    * Converts all values in the hand to a readable output string (TEMPORARY)
    * @return The cards in a hand
    */
   public String getHand()
   {
      String output = "";
      
      for (int i = 0; i < hand.length; i++)
      {
         // No null or emptiness
         if (!(hand[i] == null || hand[i].equals("")))
         {
            output += hand[i] + "\n";
         }
      }
      return output;
   }
   /**
    * Shows the value of one card
    * @param index The index of the card you wish to determine the value of
    * @return The value of the card at the supplied index
    */
   public String getHand(int index)
   {
      return hand[index];
   }
   
   /**
    * A basic blackjack strategy that simulates how an 
    * average player would play the game
    * @param dealerCard The card of the dealer that is face up
    * @param userSum The sum of the players hand
    * @return Gives true if the player should hit and false if they should stand
    */
   public boolean basicStrategy(int dealerCard, int userSum)
   {
      if (userSum == 12)
      {
         if (dealerCard >= 4 && dealerCard <= 6)
         {
            return false;
         }
      }
      else if (userSum >= 13 && userSum <= 16)
      {
         if (dealerCard >= 2 && dealerCard <= 6)
         {
            return false;
         }  
      }
      else if (userSum >= 17)
      {
         return false;
      }
      return true;
      }
         
   }