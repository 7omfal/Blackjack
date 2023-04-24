import java.util.concurrent.ThreadLocalRandom;
public class Deck
{
   private String[] deck;                                               // The total number of cards that are available for drawing
   private final String[] SUIT = {"h", "d", "c", "s"};                  // h - hearts, d - diamonds, c - clubs, s - spades
   private final String[] VALUE = {"a", "2", "3", "4", "5", "6", "7",   // a - ace, t - ten, j - jack, q - queen, k - king
                                   "8", "9", "t", "j", "q", "k"};
   private int[] values;                                                // The integer value of each card in the entire deck
   
   /**
    * Establishes deck(s)
    * @param numberOfDecks Indicates the number of standard 52 card decks to be used
    */
   public Deck(int numberOfDecks)
   {
      deck = new String[52 * numberOfDecks];
      values = new int[52 * numberOfDecks];
      
      for (int i = 0; i < numberOfDecks; i++)
      {
         // First element of each card string represents a value (1 - 10)
         for (int j = 0; j < 13; j++)
         {
            // Second element is the suit (for future display purposes)
            for (int k = 0; k < 4; k++)
            {
               /*
                 ((j * 4) + k) is each index in a standard 52 card deck, 
                 (52 * i) considers the case in which multiple decks are requested
               */ 
               deck[(j * 4) + k + (52 * (i))] = VALUE[j] + SUIT[k];
            }
         }  
      }
   }

   /**
    * Shuffles the deck(s) using the Fisher-Yates algorithm
    */
   public void shuffle()
   {
      int randomIndex;     // The random index to be generated
      String randomCard;   // Stores the card at the aforementioned random index
 
      for(int i = 0; i < deck.length; i++)
      {
          // Generate randomIndex and assign card variables
          randomIndex = ThreadLocalRandom.current().nextInt(0, deck.length);
          randomCard = deck[randomIndex];
 
          // Switch
          deck[randomIndex] = deck[i];
          deck[i] = randomCard;
      }    
   }
   
   /**
    * Draws a requested amount of cards and moves each 
    * card in the deck down one position
    * @param quantity Represents how many cards that one wishes to draw
    * @return A string array that contain the 'drawn' cards
    */ 
   public String[] draw(int quantity)
   {
      String[] cards = new String[quantity];
      
      // Repeat entire process for the quantity specified
      for (int i = 0; i < quantity; i++)
      {
         // The next card to be drawn will always move down to the position at 0
         cards[i] = deck[0];
         
         // Move all cards in the deck down one position (last cards are set to an empty string)
         for (int j = 0; j < deck.length; j++)
         {
            try 
            {
               deck[j] = deck[j + 1];
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
               deck[j] = "";
            }
         }   
      }
      /* 
         Set integer values (this might look better in its own method,
         but it's also nice that you only have to call one method in 
         the main program to complete the entire task of drawing a card)
      */
      for (int i = 0; i < deck.length; i++)
      {
         // Don't do anything if the card in question is non-existent or an empty string
         if (!(deck[i] == null || deck[i].equals("")))
         {
            // Evaluate first character in each string
            if (deck[i].substring(0, 1).equals("a"))
            {
               values[i] = 11;
            }
            else if (deck[i].substring(0, 1).equals("j") || deck[i].substring(0, 1).equals("q") || 
                     deck[i].substring(0, 1).equals("k") || deck[i].substring(0, 1).equals("t"))
            {
               values[i] = 10;
            }
            else
            {
               values[i] = Integer.parseInt(deck[i].substring(0, 1));
            }
         }
      }
      // Return the string array from line 72
      return cards;      
   }
      
   /**
    * Shows card values in the deck
    * @param start Index of element in deck to start checking value from
    * @param stop Last element of deck to check value for
    * @return An array filled with integer values that represent the cards left in the deck
    */
   public int[] getValue(int start, int stop)
   {
      int[] worth = new int[stop - start + 1];
      
      for (int i = start; i <= stop; i++)
      {
         worth[i] = values[i];
      }
      return worth;
   }
   
   /**
    * Shows the value of one card
    * @param index The index of the card you wish to determine the value of
    * @return The value of the card at the supplied index
    */
   public int getValue(int index)
   {
      return values[index];
   }
   
   /**
    * @return The entire array of integer values
    */
   public int[] getValue()
   {
      return values;
   }
      
}