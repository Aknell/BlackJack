package blackjack.players;

import blackjack.dealing.DealersHand;

public class Dealer extends Player {

    private DealersHand hand;

    public Dealer(DealersHand hand) {
        super(hand);
        this.hand = hand;
    }

    @Override
    public DealersHand getHand() { return hand; }

    // Only claims blackjack when there are 2 or more cards visible
    @Override
    public boolean blackJack() {
        return hand.getRevealed() >= 2 && this.getHand().getTotalValue() == 21;
    }

}
