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
        for(Suit suit : Suit.values()) {
            int image = suit.ace;
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit, Character.toString(image)));
                image++;
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        return deck.remove(0);
    }

    public String toString() {
        String output = "";
        for(Card c : deck) {
            output += c;
            output += "\n";
        }
        return output;
    }

}
