import java.util.*;


abstract class BlackJackStarter {
//    list that stores all card
    private final ArrayList<Card> allCard = new ArrayList<>();

//    index for tracking how many card is used
    private int curIndex = 0;

    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private Integer playerNum = 0;
    private Integer dealerNum = 0;

    private Integer playerWins;
    private Integer dealerWins;

    public BlackJackStarter(int numOfDeck){
//        create and shuffle the entire deck
        for (int i = 0; i < numOfDeck; i++){
            for (String curSuit: BlackJackMetadata.suits){
                for (Integer curRank: BlackJackMetadata.ranks){
                    allCard.add(new Card(curSuit, curRank));
                }
            }
        }
        Collections.shuffle(allCard);
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
                        dealerWins++;
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
                        playerWins++;
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
                            dealerWins++;
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

    // method the game use to decide whether to hit or stand
    public abstract int hitDecider();

    public void startGame(){
        // continuely playing the game until user said quit
        boolean continueGame = true;
        while (continueGame){
            this.startSingleGame();
            continueGame = continueGameDecider();
        }
        System.out.println("\n\n");
        System.out.println("Player Wins: " + playerWins);
        System.out.println("Dealer Wins: " + dealerWins);

    }

    // method for the game to decide how to loop the game
    public abstract boolean continueGameDecider();


    // getters
    public ArrayList<Card> getAllCard() {
        return allCard;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public Integer getPlayerNum() {
        return playerNum;
    }

    public Integer getDealerNum() {
        return dealerNum;
    }
}
