package blackjack.main;

import blackjack.api.Rank;
import blackjack.dealing.Deck;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        System.out.println("\n");
        deck.shuffle();
        System.out.println(deck);
    }

}
