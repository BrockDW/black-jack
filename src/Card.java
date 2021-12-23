public class Card {
    public String curSuit;
    public Integer curRank;

    public Card(String curSuit, Integer curRank) {
        this.curSuit = curSuit;
        this.curRank = curRank;
    }

    @Override
    public String toString() {
        String curRankDispay = "";
        switch (curRank){
            case 1:
                curRankDispay += "Ace";
                break;
            case 11:
                curRankDispay += "J";
                break;
            case 12:
                curRankDispay += "Q";
                break;
            case 13:
                curRankDispay += "K";
                break;
            default:
                curRankDispay += curRank;
                break;
        }
        return curSuit + " " + curRankDispay;
    }
}
