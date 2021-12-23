import java.lang.reflect.Array;
import java.util.*;

public class BlackJackStarter {
    private ArrayList<Card> allCard = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public BlackJackStarter(){
        for (int i = 0; i < BlackJackMetadata.numOfDeck; i++){
            for (String curSuit: BlackJackMetadata.suits){
                for (Integer curRank: BlackJackMetadata.ranks){
                    allCard.add(new Card(curSuit, curRank));
                }
            }
        }
    }

    public void startGame(){
        Collections.shuffle(allCard);
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        Integer playerNum = 0;
        Integer dealerNum = 0;
        int term = 0;

        while (term<3){
            for (Card curCard: allCard){
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
                    }
                    if (term == 1){
                        System.out.println("Your current hand includes: " + playerHand.toString() + ", would you like another card?");
                        userResult = sc.nextLine().toUpperCase();
                        System.out.println(userResult);
//                      System.out.println(!(userResult.equals("Y") || userResult.equals("N")));
                        while (!(userResult.equals("Y") || userResult.equals("N"))){
                            System.out.println("your input is invalid please try again");
                            userResult = sc.nextLine().toUpperCase();
//                        System.out.println(!(userResult.equals("Y") || userResult.equals("N")));
                        }
                        if (userResult.equals("Y")) {
                            term = 0;
                        } else {
                            term = 2;
                        }
                    } else {
                        if (dealerNum < playerNum){
                            System.out.println("Dealer gained a card. Currently has smaller number than player");
                        } else if (dealerNum > playerNum){
                            System.out.println("Dealer gained a card. Currently has greater number than player");
                            System.out.println("Dealer wins!!");
                            term = 4;
                        }

                    }
//
                }
            }
        }
    }

    public static void main(String[] args) {
        BlackJackStarter newGame = new BlackJackStarter();
        newGame.startGame();
    }

}
