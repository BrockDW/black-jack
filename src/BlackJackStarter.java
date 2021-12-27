import java.util.*;

// classes that store the metadata of the game
class BlackJackMetadata {
    public static final int numOfDeck = 1;
    public static final String[] suits = new String[]{"clubs", "diamonds", "hearts", "spades"};
    public static final Integer[] ranks = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
}

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

interface Strategy{
//    public boolean decide(int number);
    public boolean decide(ArrayList<Card> curHand, int number);
}


class SimpleStrategy implements Strategy{
    private int threshhold;

    public SimpleStrategy(int threshHold) {
        this.threshhold = threshHold;
    }

    @Override
    public boolean decide(ArrayList<Card> curHand, int number) {
        return number < threshhold;
//        return false;
    }
}

class BlackJackAutoGameStarter extends BlackJackStarter{

    private int term;

    public BlackJackAutoGameStarter(Strategy curStrat, int term) {
        super(curStrat);
        this.term = term;
    }

    @Override
    public int hitDecider() {
        boolean decide = this.getStrat().decide(this.getPlayerHand(), this.getPlayerNum());
        if (decide){
            System.out.println("Computer Player decided to hit!");
        } else {
            System.out.println("Computer Player decided to stand!");
        }
        return decide ? 0 : 2;
    }

    @Override
    public boolean continueGameDecider() {
        if (term > 0){
            System.out.println("continue playing, currently " + this.term + " number of games left");
            return true;
        } else {
            return false;
        }
    }
}


abstract class BlackJackStarter {
//    list that stores all card
    private final ArrayList<Card> allCard = new ArrayList<>();
//    scanner for accessing user input
    private final Scanner sc = new Scanner(System.in);
//    index for tracking how many card is used
    private int curIndex = 0;

    private Strategy strat;

    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private Integer playerNum = 0;
    private Integer dealerNum = 0;



    public BlackJackStarter(Strategy curStrat){
//        create and shuffle the entire deck
        for (int i = 0; i < BlackJackMetadata.numOfDeck; i++){
            for (String curSuit: BlackJackMetadata.suits){
                for (Integer curRank: BlackJackMetadata.ranks){
                    allCard.add(new Card(curSuit, curRank));
                }
            }
        }
        Collections.shuffle(allCard);
        strat = curStrat;
    }

    public void startSingleGame(){
        // initialize each hand and the value of player's hand
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerNum = 0;
        dealerNum = 0;
        // game condidtion,
        // 0 = player term
        // 1 = dealer term
        // 2 = player hand fixed
        // 3 = player win
        // 4 = dealer win
        int term = 0;

        while (term<3){
            // iterate all cards
            for (;curIndex < allCard.size(); curIndex++){
                // add a card to player hand and check if it exceed 21 or not
                Card curCard = allCard.get(curIndex);
                if (term == 0){
                    playerHand.add(curCard);
                    playerNum += curCard.curRank;
                    if (playerNum > 21){
                        System.out.println("Player's hand exceed 21, Dealer wins!!");
                        term = 4;
                        break;
                    }
                    // switch to dealer term
                    term = 1;
                } else {
                    // add a card to dealer hand and check if it exceed 21 or not
                    dealerHand.add(curCard);
                    dealerNum += curCard.curRank;

                    if (dealerNum > 21){
                        System.out.println("Dealer's hand exceed 21, Player wins!!");
                        term = 3;
                        break;
                    }
                    if (term == 1){
                        System.out.println("Player current hand includes: " + playerHand.toString());
                        term = hitDecider();
                    } else {
                        // if player hand is fixed, dealer keep gaining card and check if greater than the player's hand
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
            // if card used out, reshuffle and use a new deck
            if (curIndex >= 52){
                System.out.println("current deck used out, reshuffle the deck and continue playing");
                Collections.shuffle(allCard);
                curIndex = 0;
            }
        }
    }

    public abstract int hitDecider();
//    {
//        String userResult = "";
//        // ask if user want more card or not
//        System.out.println("Would you like another card?");
//        userResult = sc.nextLine().toUpperCase();
//        // keep asking answers if user input is invalid
//        while (!(userResult.equals("Y") || userResult.equals("N"))){
//            System.out.println("your input is invalid please try again");
//            userResult = sc.nextLine().toUpperCase();
//        }
//        // change term according to user's answer, 2 = player hand fixed
//        return userResult.equals("Y")? 0 : 2;
//    }

    public void startGame(){
        // continuely playing the game until user said quit
        boolean continueGame = true;
        while (continueGame){
            this.startSingleGame();
            continueGame = continueGameDecider();
        }
    }

    public abstract boolean continueGameDecider();
//    {
//        System.out.println("\n\nWould you like to try another game?");
//        String userResult = sc.nextLine().toUpperCase();
//        if (!userResult.equals("Y")){
//            System.out.println("Thank you for playing the game! Have a nice day!");
//            return false;
//        }
//        return true;
//    }

//    public static void main(String[] args) {
//        // start the game
//        BlackJackStarter newGame = new BlackJackStarter();
//        newGame.startGame();
//    }


    public ArrayList<Card> getAllCard() {
        return allCard;
    }

    public Strategy getStrat() {
        return strat;
    }

    public void setStrat(Strategy strat) {
        this.strat = strat;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public Integer getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(Integer playerNum) {
        this.playerNum = playerNum;
    }

    public Integer getDealerNum() {
        return dealerNum;
    }

    public void setDealerNum(Integer dealerNum) {
        this.dealerNum = dealerNum;
    }
}
