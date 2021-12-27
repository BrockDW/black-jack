import java.util.ArrayList;

class SimpleStrategy implements Strategy {
    private final int threshhold;

    public SimpleStrategy(int threshHold) {
        this.threshhold = threshHold;
    }

    @Override
    public boolean decide(ArrayList<Card> curHand, int number) {
        return number < threshhold;
//        return false;
    }
}
