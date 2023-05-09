package blackjack.cards;

public class Card {

    private int rank;
    private int colour;

    public Card(int rank, int colour) {
        this.rank = rank;
        this.colour = colour;
    }

    public int getRank() { return rank; }

    public int getColour() { return colour; }

    public int getValue() {
        if(rank > 9) return 10;
        else if(rank == 0) {
            /* TODO IMPLEMENT ACE LOGIC */
            return 1;
        }
        else return rank + 1;
    }

}
