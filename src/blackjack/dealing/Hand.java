package blackjack.dealing;

import blackjack.cards.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand = new ArrayList<Card>();
    private int value;
    private boolean isDealer;

    public Hand() {
        this(false);
    }
    public Hand(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public void hit(Card c) {
        hand.add(c);
    }

    public ArrayList<Card> getHand() { return hand; }

    public int getTotalValue() {
        int value = 0;
        for(Card c : hand) value += c.getValue();
        return value;
    }

}
