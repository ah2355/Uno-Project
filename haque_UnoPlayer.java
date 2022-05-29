
package uno;

import java.awt.Color;
import java.util.List;

public class haque_UnoPlayer implements UnoPlayer {

    /**
     * play - This method is called when it's your turn and you need to
     * choose what card to play.
     *
     * The hand parameter tells you what's in your hand. You can call
     * getColor(), getRank(), and getNumber() on each of the cards it
     * contains to see what it is. The color will be the color of the card,
     * or "Color.NONE" if the card is a wild card. The rank will be
     * "Rank.NUMBER" for all numbered cards, and another value (e.g.,
     * "Rank.SKIP," "Rank.REVERSE," etc.) for special cards. The value of
     * a card's "number" only has meaning if it is a number card. 
     * (Otherwise, it will be -1.)
     *
     * The upCard parameter works the same way, and tells you what the 
     * up card (in the middle of the table) is.
     *
     * The calledColor parameter only has meaning if the up card is a wild,
     * and tells you what color the player who played that wild card called.
     *
     * Finally, the state parameter is a GameState object on which you can 
     * invoke methods if you choose to access certain detailed information
     * about the game (like who is currently ahead, what colors each player
     * has recently called, etc.)
     *
     * You must return a value from this method indicating which card you
     * wish to play. If you return a number 0 or greater, that means you
     * want to play the card at that index. If you return -1, that means
     * that you cannot play any of your cards (none of them are legal plays)
     * in which case you will be forced to draw a card (this will happen
     * automatically for you.)
     */
    public int play(List<Card> hand, Card upCard, Color calledColor,
        GameState state) {
      int calledNum = upCard.getNumber();
      Color calledCol = upCard.getColor();
      Rank calledRank = upCard.getRank();
      int [] opponents = state.getNumCardsInHandsOfUpcomingPlayers();
      
      /*This if statement will check if the 
       next opponent is close to winning and if 
       they are close to winning a wild card will be
       played from players hand card which will 
       at least make sure the  next opponent doesn't win
       */
      
      if(opponents[0]==2 || opponents[0]==3)
      {
        for(int a=0;a<hand.size();a++)
          {
            if(hand.get(a).getRank().equals(Rank.WILD_D4))
               {
                 return a;
               }
            else if(hand.get(a).getRank().equals(Rank.DRAW_TWO))
            {
              return a;
            }
            else if(hand.get(a).getRank().equals(Rank.SKIP))
            {
              return a;
            }
            else if(hand.get(a).getRank().equals(Rank.REVERSE))
            {
              return a;
            }
            else   if(hand.get(a).getRank().equals(Rank.WILD))
            {
            return a;
            }
          }
      }

      
     for(int i=0; i<hand.size();i++)
       {
         if(calledNum==hand.get(i).getNumber() && calledNum!= -1)
         {
           return i;
         }
         else if(calledCol.equals(hand.get(i).getColor()))
         {
           return i;
         }
         else if(calledRank.equals(Rank.WILD) || calledRank.equals(Rank.WILD_D4))
         {
           for(int j =0; j<hand.size();j++)
             {
               if(calledColor.equals(hand.get(j).getColor()))
                  {
                 return j;
                  }
             }
         }
         
       }
     
      for(int i=0; i<hand.size();i++)
        {
          if(hand.get(i).getColor().equals(Color.NONE))
          {
            return i;
          }
          else if(calledRank!=Rank.NUMBER && calledRank.equals(hand.get(i).getRank()))
          {
            return i;
          }
        }
        // THIS IS WHERE YOUR AMAZING CODE GOES
        return -1;
    }


    /**
     * callColor - This method will be called when you have just played a
     * wild card, and is your way of specifying which color you want to 
     * change it to.
     *
     * You must return a valid Color value from this method. You must not
     * return the value Color.NONE under any circumstances.
     */
    public Color callColor(List<Card> hand) 
  {
    GameState state = new GameState();
      int index =0;
      int y = 0;
    Color[] colopponents = state.getMostRecentColorCalledByUpcomingPlayers();
      //first, checks if the opponent next to player
    // at index 0 called a wild card.
      if(colopponents[0]!=null && !colopponents[0].equals(Color.NONE))
      {
       for(int a =0; a <hand.size();a++)
        {
          int l =0;
          //this for loop checks the most card the player have and and it doesn't count the next call color the opponent next to player
          for(int j=a+1;j<hand.size();j++)
            {
              if(hand.get(a).getColor().equals(hand.get(j).getColor()))
              {
                if(!hand.get(a).getColor().equals(Color.NONE) && !hand.get(a).getColor().equals(colopponents[0]))
                {
                  l++;
                }
              }
            }
          if(l>y)
          {
            y=l;
            index = a;
          }
        } 
        if(!hand.get(index).getColor().equals(Color.NONE))
        {
         return hand.get(index).getColor(); 
        }
      }
    //this for loop looks for the color that the
    // player has most and return the index of the 
    // most called color in the card
      for(int i=0; i<hand.size();i++)
        {
          int x = 0;
          
          for(int j=i+1;j<hand.size();j++)
            {
              if(hand.get(i).getColor().equals(hand.get(j).getColor()))
              {
                if(!hand.get(i).getColor().equals(Color.NONE))
                {
                  x++;
                }
              }
            }
          if(x>y)
          {
            y=x;
            index = i;
          }
          
        }
        //THIS IS WHERE YOUR AMAZING CODE GOES
        if(!hand.get(index).getColor().equals(Color.NONE))
        {
         return hand.get(index).getColor(); 
        }
       //Sometimes the palyer might not have
      // a color card to play so in that case player has
      // to call a color which is not same as the 
      // opponent's next card
      if(colopponents[0]!=null)
      {
           if(!colopponents[0].equals(Color.RED))
          {
            return Color.RED;
          }
        else if(!colopponents[0].equals(Color.BLUE))
          {
          return  Color.BLUE;
          }
        else if(!colopponents[0].equals(Color.YELLOW))
        {
          return Color.YELLOW;
        }
        else if(!colopponents[0].equals(Color.GREEN))
        {
         return Color.GREEN;
        } 
      }
      int g = (int) (Math.random() * 4);

      if(g==1)
      {
        return Color.RED;
      }
      else if(g==2)
      {
        return Color.BLUE;
      }
      else if(g==3)
      {
        return Color.GREEN;
      }
       return Color.YELLOW;
      
}
  
}