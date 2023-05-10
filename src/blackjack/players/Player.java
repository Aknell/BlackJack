package blackjack.players;

import blackjack.cards.Card;
import blackjack.dealing.Hand;

public class Player {

    private Hand hand;

    public Player() {
        this.hand = new Hand();
    }

    public Hand getHand() { return hand; }
    public void setHand(Hand hand) { this.hand = hand; }

    public boolean hasBusted() {
        return hand.getTotalValue() > 21;
    }

    public boolean blackJack() {
        return hand.getHand().size() == 2 && hand.getTotalValue() == 21;
    }
}
