import java.util.Scanner;

// this class is setting up a game for player against player
class BlackJackPtpStarter extends BlackJackStarter {
    //    scanner for accessing user input
    private final Scanner sc = new Scanner(System.in);

    public BlackJackPtpStarter(int numOfDeck) {
        super(numOfDeck);
    }

    @Override
    public int hitDecider() {
        System.out.println("Player current hand includes: " + getPlayerHand().toString());
        String userResult = "";
        // ask if user want more card or not
        System.out.println("Would you like another card?");
        userResult = sc.nextLine().toUpperCase();
        // keep asking answers if user input is invalid
        while (!(userResult.equals("Y") || userResult.equals("N"))) {
            System.out.println("your input is invalid please try again");
            userResult = sc.nextLine().toUpperCase();
        }
        // change term according to user's answer, 2 = player hand fixed
        return userResult.equals("Y") ? 0 : 2;
    }

    @Override
    public boolean hitDeciderDealer() {
        return false;
    }

    @Override
    public boolean continueGameDecider() {
        System.out.println("\n\nWould you like to try another game?");
        String userResult = sc.nextLine().toUpperCase();
        if (!userResult.equals("Y")) {
//            System.out.println("Thank you for playing the game! Have a nice day!");
            return false;
        }
        return true;
    }
}
