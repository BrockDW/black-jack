import java.util.ArrayList;

interface Strategy {
    //    public boolean decide(int number);
    public boolean decide(ArrayList<Card> curHand, int number);
}
