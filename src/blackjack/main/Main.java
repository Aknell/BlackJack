package blackjack.main;

import blackjack.api.Rank;
import blackjack.dealing.DealersHand;
import blackjack.dealing.Deck;
import blackjack.dealing.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println(deck);
        Dealer dealer = new Dealer(new DealersHand());
        Player player = new Player(new Hand());
        boolean won;

        for(int i = 0; i < 2; i++) {
            dealer.getHand().hit(deck);
            player.getHand().hit(deck);
        }

        while(!player.blackJack()) {
            /* TODO MAKE GAME LOGIC */
            if(dealer.blackJack()) won = true;
        }
    }

}
