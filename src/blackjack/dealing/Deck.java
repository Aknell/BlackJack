package blackjack.dealing;

import blackjack.cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
        for(int i = 0; i < 13; i++) {
            for(int j = 0; j < 2; j++) {
                deck.add(new Card(i, j));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        return deck.remove(0);
    }

}
