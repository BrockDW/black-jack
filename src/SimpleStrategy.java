import java.util.ArrayList;

// this is a simple strategy, using only the current number in hand
class SimpleStrategy implements Strategy {
    private final int threshhold;

    public SimpleStrategy(int threshHold) {
        this.threshhold = threshHold;
    }

    // if current hand exceed the threshold, return false, meaning stand
    @Override
    public boolean decide(ArrayList<Card> curHand, int number) {
        return number < threshhold;
//        return false;
    }
}
