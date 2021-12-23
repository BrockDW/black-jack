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

        while (true){

        }
    }

}
