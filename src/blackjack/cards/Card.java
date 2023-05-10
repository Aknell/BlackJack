package blackjack.cards;

import blackjack.api.Rank;

public class Card {

    private Rank rank;

    public Card(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() { return rank; }

    public int getValue() {
        return rank.rankValue;
    }

    public String toString(){
        return ("[" + rank + "] ("+this.getValue()+")");

    }

}
