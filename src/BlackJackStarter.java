import java.lang.reflect.Array;
import java.util.*;

public class BlackJackStarter {
    private ArrayList<Card> allCard = new ArrayList<>();

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
//        PriorityQueue<Card> curDeck = new PriorityQueue<>();
//        LinkedList<Card> curDeck
        Collections.shuffle(allCard);

        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        Integer playerNum = 0;
        Integer dealerNum = 0;
        int term = 0;
        Scanner sc = new Scanner(System.in);

        while (true){
            for (Card curCard: allCard){
                if (term == 0){
                    playerHand.add(curCard);
                    term = 1;
                } else {
                    dealerHand.add(curCard);
                    String userResult = "";
                    if (term == 1){
                        System.out.println("Your current hand includes: " + playerHand.toString() + ", would you like another card?");
                        userResult = sc.nextLine();
                    }
                    if (userResult.toUpperCase().equals("Y")){
                        term = 0;
                    } else {
                        term = 2;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BlackJackStarter newGame = new BlackJackStarter();
        newGame.startGame();
    }

}
