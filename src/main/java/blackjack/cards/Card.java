package blackjack.cards;

import blackjack.api.Rank;
import blackjack.api.Suit;

public class Card {

    private Rank rank;
    private Suit suit;

    // Use Unicode characters to make cards
    private String img;
    private final String backImg = "\uD83C\uDCA0";

    public Card(Rank rank, Suit suit, String img) {
        this.rank = rank;
        this.suit = suit;
        this.img = img;
    }

    public Suit getSuit() { return suit; }
    public int getValue() {
        return rank.rankValue;
    }
    public String getImg() { return img; }
    public String getBackImg() { return backImg; }

    public String toString(){
        return ("[" + rank + " of " + suit + "] ("+this.getValue()+") " + img);
    }

}
