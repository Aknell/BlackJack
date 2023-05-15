package blackjack.dealing;

import blackjack.cards.Card;
import java.util.ArrayList;

public class DealersHand extends Hand {

    private int revealed = 1;
    private ArrayList<Card> publicHand = new ArrayList<Card>();

    public DealersHand() { super(); }

    public void reveal() { revealed++; }
    public int getRevealed() { return revealed; }
    public void setRevealed(int revealed) { this.revealed = revealed; }

    public Hand getPublicHand() {
        publicHand.clear();
        for(int i = 0; i < revealed; i++) {
            publicHand.add(super.getHand().get(i));
        }
        return new Hand(publicHand);
    }

    public String toString() {
        publicHand.clear();
        for(int i = 0; i < revealed; i++) {
            publicHand.add(super.getHand().get(i));
        }
        String output = "";
        for(Card c : publicHand) {
            output += c;
            output += "\n";
        }
        return output;
    }
}
