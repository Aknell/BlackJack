package blackjack.dealing;

import blackjack.api.Rank;
import blackjack.api.Suit;
import blackjack.cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();

        // Fills deck with every card which has a suit and rank
        for(Suit suit : Suit.values()) {
            // Start unicode character location from ace symbol
            int image = suit.ace;
            for (Rank rank : Rank.values()) {
                // Adds card with image, then increments unicode character location
                deck.add(new Card(rank, suit, Character.toString(image)));
                image++;
            }
        }
    }

    // Shuffles deck
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // Removes one card from deck and returns the card removed
    public Card drawCard() {
        return deck.remove(0);
    }
}
