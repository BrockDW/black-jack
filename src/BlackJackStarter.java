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

    private Integer playerWins = 0;
    private Integer dealerWins = 0;

    StringBuilder gameLog;
    StringBuilder resultLog;

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

    private int handCounter(ArrayList<Card> currentHand){
        int totalNumber = 0;
        int totalAces = 0;
        for (Card c: currentHand){
            int value = c.getValue();
            if (value != 1){
                totalNumber+= value;
            } else {
                totalAces++;
            }
        }

        for(int i = 0; i < totalAces; i++){
            if (totalNumber + 11 + (totalAces - (i+1)) <= 21){
                totalNumber += 11;
            } else {
                totalNumber += 1;
            }
        }
        return totalNumber;
    }

    private void printBothHand(Integer dealerNum, Integer playerNum){
        System.out.print("Player's Hand: ");
        System.out.format("%50s%15s", playerHand.toString(), "score: "+playerNum);
        System.out.print("\nDealer's Hand: ");
        System.out.format("%50s%15s", dealerHand.toString(), "score: "+ dealerNum);
        System.out.println();
    }


    public void startSingleGame(){
        // initialize each hand and the value of player's hand
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerNum = 0;
        dealerNum = 0;
        gameLog = new StringBuilder();
        resultLog = new StringBuilder();
        boolean dealerFixed = false;
        // game condidtion,
        // 0 = player term
        // 1 = dealer term
        // 2 = player hand fixed
        // 3 = player win
        // 4 = dealer win
        int term = 0;

        while (term<3){
            System.out.println(term + " " + dealerFixed);
            // iterate all cards
            for (;curIndex < allCard.size(); curIndex++){
                // add a card to player hand and check if it exceed 21 or not
                Card curCard = allCard.get(curIndex);
                if (term == 0){
                    playerHand.add(curCard);
                    playerNum = handCounter(playerHand);
                    if (playerNum > 21){
                        resultLog.append("Player's hand exceed 21, Dealer wins!!").append("\n");
                        dealerWins++;
                        term = 4;
                        break;
                    }
                    // switch to dealer term
                    term = 1;
                } else {
                    if (!dealerFixed){
                        // add a card to dealer hand and check if it exceed 21 or not
                        dealerHand.add(curCard);
                        dealerNum = handCounter(dealerHand);

                        if (dealerNum > 21){
                            resultLog.append("Dealer's hand exceed 21, Player wins!!").append("\n");
                            playerWins++;
                            term = 3;
                            break;
                        }
                        dealerFixed = hitDeciderDealer();
                    }

                    if (term == 1){
                        term = hitDecider();
                    } else {
                        // if player hand is fixed, dealer keep gaining card and check if greater than the player's hand
                        if (dealerNum < playerNum){
                            if (dealerFixed){
                                resultLog.append("player win!").append("\n");
                                playerWins++;
                                term = 3;
                                break;
                            } else {
                                gameLog.append("Dealer gained a card. Currently has smaller number than player").append("\n");
                            }
                        } else {
                            gameLog.append("Dealer gained a card. Currently has greater number than player").append("\n");
                            resultLog.append("Dealer wins!!").append("\n");
                            dealerWins++;
                            term = 4;
                            break;
                        }
                    }
                }
            }
            // if card used out, reshuffle and use a new deck
            if (curIndex >= 52){
                gameLog.append("current deck used out, reshuffle the deck and continue playing").append("\n");
                Collections.shuffle(allCard);
                curIndex = 0;
            }
        }
        System.out.println(gameLog.toString());
        printBothHand(dealerNum, playerNum);
        System.out.println(resultLog.toString());
    }

    // method the game use to decide whether to hit or stand
    public abstract int hitDecider();

    // method the game use to decide whether to hit or stand
    public abstract boolean hitDeciderDealer();

    public void startGame(){
        // continuely playing the game until user said quit
        boolean continueGame = true;
        while (continueGame){
            this.startSingleGame();
            continueGame = continueGameDecider();
        }
        System.out.println("\nThanks for playing the game!");
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
