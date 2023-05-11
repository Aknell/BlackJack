package blackjack.dealing;

import blackjack.cards.Card;

import java.util.ArrayList;

public class DealersHand extends Hand {

    private int revealed = 1;
    private ArrayList<Card> publicHand = new ArrayList<Card>();

    public DealersHand() { super(); }

    public void reveal() { revealed++; }
    public int getRevealed() { return revealed; }

    @Override
    public ArrayList<Card> getHand() {
        for(int i = 0; i < revealed; i++) {
            publicHand.add(this.getHand().get(i));
        }
        return publicHand;
    }
}
