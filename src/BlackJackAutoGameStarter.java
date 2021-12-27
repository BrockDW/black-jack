class BlackJackAutoGameStarter extends BlackJackStarter {

    private int term;
    private final Strategy strat;

    public BlackJackAutoGameStarter(Strategy curStrat, int term) {
        super();
        this.term = term;
        this.strat = curStrat;
    }

    @Override
    public int hitDecider() {
        boolean decide = this.strat.decide(this.getPlayerHand(), this.getPlayerNum());
        if (decide) {
            System.out.println("Computer Player decided to hit!");
        } else {
            System.out.println("Computer Player decided to stand!");
        }
        return decide ? 0 : 2;
    }

    @Override
    public boolean continueGameDecider() {
        System.out.println("\n\n");
        if (term > 0) {
            System.out.println("continue playing, currently " + this.term + " number of games left");
            this.term--;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        BlackJackAutoGameStarter gs = new BlackJackAutoGameStarter(new SimpleStrategy(14), 10);
        gs.startGame();
    }
}
