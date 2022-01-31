// this class is for starting a game for computer against computer
class BlackJackAutoGameStarter extends BlackJackStarter {

    private int term;
    private final Strategy strat;
    private Strategy dealerStrat;

    public BlackJackAutoGameStarter(Strategy curStrat, Strategy dealerStrat, int term, int numOfDeck) {
        super(numOfDeck);
        this.term = term;
        this.strat = curStrat;
        dealerStrat = dealerStrat;
    }

    @Override
    public int hitDecider() {
        gameLog.append("Player current hand includes: ").append(getPlayerHand().toString()).append("\n");
        boolean decide = this.strat.decide(this.getPlayerHand(), this.getPlayerNum());
        if (decide) {
            gameLog.append("Computer Player decided to hit!").append("\n");
        } else {
            gameLog.append("Computer Player decided to stand!").append("\n");
        }
        return decide ? 0 : 2;
    }

    @Override
    public boolean hitDeciderDealer() {
        return !this.strat.decide(getDealerHand(), getDealerNum());
    }

    @Override
    public boolean continueGameDecider() {
        System.out.println("\n\n");
        if (term > 0) {
            System.out.println("it works here "+term);
            this.term--;
            return true;
        } else {
            return false;
        }
    }
}
