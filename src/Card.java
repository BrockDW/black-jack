// card object, used to store information of a card
class Card {
    public String curSuit;
    public Integer curRank;

    public Card(String curSuit, Integer curRank) {
        this.curSuit = curSuit;
        this.curRank = curRank;
    }

    //    to string method that print a card
    @Override
    public String toString() {
        String curRankDispay = "";
        switch (curRank) {
            case 1 -> curRankDispay += "Ace";
            case 11 -> curRankDispay += "J";
            case 12 -> curRankDispay += "Q";
            case 13 -> curRankDispay += "K";
            default -> curRankDispay += curRank;
        }
        return curSuit + " " + curRankDispay;
    }
}
