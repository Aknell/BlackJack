package blackjack.cards;

import blackjack.api.Rank;
import blackjack.api.Suit;

import java.awt.image.BufferedImage;

public class Card {

    private Rank rank;
    private Suit suit;
    private String img;
    private final String backImg = "\uD83C\uDCA0";

    public Card(Rank rank, Suit suit, String img) {
        this.rank = rank;
        this.suit = suit;
        this.img = img;
    }

    public Rank getRank() { return rank; }
    public int getValue() {
        return rank.rankValue;
    }
    public String getImg() { return img; }

    public String toString(){
        return ("[" + rank + " of " + suit + "] ("+this.getValue()+") " + img);
    }

}
