package blackjack.dealing;

import blackjack.cards.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand = new ArrayList<Card>();

    public Hand() {}

    public void hit(Deck deck) {
        hand.add(deck.drawCard());
    }

    public ArrayList<Card> getHand() { return hand; }

    public int getTotalValue() {
        int value = 0;
        int aceCount = 0;

        for(Card c : hand) {
           value += c.getValue();
           if(c.getValue() == 11) aceCount++;
        }

        // Setting all aces to 1 while there are aces in the hand, and it is worth more than 21
        if (value > 21 && aceCount > 0){
            while(aceCount > 0 && value > 21){
                aceCount --;
                value -= 10;
            }
        }

        return value;
    }

    public String toString() {
        String output = "";
        for(Card c : hand) {
            output += c;
            output += "\n";
        }
        return output;
    }

}
