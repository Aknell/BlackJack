package blackjack.api;

public enum Suit {
    SPADES("Spades", 0x1F0A1),
    HEARTS("Hearts", 0x1F0B1),
    DIAMONDS("Diamonds", 0x1F0C1),
    CLUBS("Clubs", 0x1F0D1);

    String suitName;
    public int ace;
    Suit(String suitName, int ace) {
        this.ace = ace;
        this.suitName = suitName;
    }

    public String toString() { return suitName; }

}
