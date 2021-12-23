import java.util.*;

class BlackJackMetadata {
    public static final int numOfDeck = 1;
    public static final String[] suits = new String[]{"clubs", "diamonds", "hearts", "spades"};
    public static final Integer[] ranks = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
//    public static final ArrayList<Card> allCard = new ArrayList<>();
}

class Card {
    public String curSuit;
    public Integer curRank;

    public Card(String curSuit, Integer curRank) {
        this.curSuit = curSuit;
        this.curRank = curRank;
    }

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


public class BlackJackStarter {
    private final ArrayList<Card> allCard = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private int curIndex = 0;

    public BlackJackStarter(){
        for (int i = 0; i < BlackJackMetadata.numOfDeck; i++){
            for (String curSuit: BlackJackMetadata.suits){
                for (Integer curRank: BlackJackMetadata.ranks){
                    allCard.add(new Card(curSuit, curRank));
                }
            }
        }
        Collections.shuffle(allCard);
    }

    public void startSingleGame(){
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        Integer playerNum = 0;
        Integer dealerNum = 0;
        int term = 0;

        while (term<3){
            for (;curIndex < allCard.size(); curIndex++){
                Card curCard = allCard.get(curIndex);
                if (term == 0){
                    playerHand.add(curCard);
                    playerNum += curCard.curRank;
                    if (playerNum > 21){
                        System.out.println("Player's hand exceed 21, Dealer wins!!");
                        term = 4;
                        break;
                    }
                    term = 1;
                } else {
                    dealerHand.add(curCard);
                    dealerNum += curCard.curRank;
                    String userResult = "";
                    if (dealerNum > 21){
                        System.out.println("Dealer's hand exceed 21, Player wins!!");
                        term = 3;
                        break;
                    }
                    if (term == 1){
                        System.out.println("Your current hand includes: " + playerHand.toString() + ", would you like another card?");
                        userResult = sc.nextLine().toUpperCase();
                        while (!(userResult.equals("Y") || userResult.equals("N"))){
                            System.out.println("your input is invalid please try again");
                            userResult = sc.nextLine().toUpperCase();
                        }
                        term = userResult.equals("Y")? 0 : 2;
                    } else {
                        if (dealerNum < playerNum){
                            System.out.println("Dealer gained a card. Currently has smaller number than player");
                        } else if (dealerNum > playerNum){
                            System.out.println("Dealer gained a card. Currently has greater number than player");
                            System.out.println("Dealer wins!!");
                            term = 4;
                            break;
                        }
                    }
                }
            }
            if (curIndex >= 52){
                System.out.println("current deck used out, reshuffle the deck and continue playing");
                Collections.shuffle(allCard);
                curIndex = 0;
            }
        }
    }

    public void startGame(){
        while (true){
            this.startSingleGame();
            System.out.println("\n\nWould you like to try another game?");
            String userResult = sc.nextLine().toUpperCase();
            if (userResult.equals("N")){
                System.out.println("Thank you for playing the game! Have a nice day!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        BlackJackStarter newGame = new BlackJackStarter();
        newGame.startGame();
    }
}
