package blackjack.api;

public enum Suit {
    SPADES("Spades", 0x1F0A1, 0x1F0AE),
    HEARTS("Hearts", 0x1F0B1, 0x1F0BE),
    DIAMONDS("Diamonds", 0x1F0C1, 0x1FCAE),
    CLUBS("Clubs", 0x1F0D1, 0x1F0DE);

    String suitName;
    public int ace;
    int king;
    Suit(String suitName, int ace, int king) {
        this.ace = ace;
        this.king = king;
        this.suitName = suitName;
    }

    public String toString() { return suitName; }

}
