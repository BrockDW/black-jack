import java.util.ArrayList;

// strategy interface, for strategy to use when executing computer again computer game
interface Strategy {
    // decide whether to hit or stand
    public boolean decide(ArrayList<Card> curHand, int number);
}
