package blackjack.players;

import blackjack.cards.Card;
import blackjack.dealing.Hand;

public class Player {

    private Hand hand;

    public Player(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() { return hand; }

    public boolean hasBusted() {
        return hand.getTotalValue() > 21;
    }

    public boolean blackJack() {
        return hand.getTotalValue() == 21;
    }
}
