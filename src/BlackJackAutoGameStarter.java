// this class is for starting a game for computer against computer
class BlackJackAutoGameStarter extends BlackJackStarter {

    private int term;
    private final Strategy strat;

    public BlackJackAutoGameStarter(Strategy curStrat, int term, int numOfDeck) {
        super(numOfDeck);
        this.term = term;
        this.strat = curStrat;
    }

    @Override
    public int hitDecider() {
        gameLog.append("Player current hand includes: ").append(getPlayerHand().toString()).append("\n");
        boolean decide = this.strat.decide(this.getPlayerHand(), this.getPlayerNum());
        if (decide) {
            gameLog.append("Computer Player decided to hit!").append("\n");
//            System.out.println("Computer Player decided to hit!");
        } else {
            gameLog.append("Computer Player decided to stand!").append("\n");
//            System.out.println("Computer Player decided to stand!");
        }
        return decide ? 0 : 2;
    }

    @Override
    public boolean continueGameDecider() {
        System.out.println("\n\n");
        if (term > 0) {
            gameLog.append("continue playing, currently").append(" ").append(this.term).append(" ").append("number of games left").append("\n");
//            System.out.println("continue playing, currently " + this.term + " number of games left");
            this.term--;
            return true;
        } else {
            return false;
        }
    }

//    public static void main(String[] args) {
//        BlackJackAutoGameStarter gs = new BlackJackAutoGameStarter(new SimpleStrategy(14), 10, 1);
//        gs.startGame();
//    }
}
