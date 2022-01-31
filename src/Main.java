import java.util.Scanner;

public class Main {

    public void dataCollection(){

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to black jack game!");
        System.out.println("Please enter the number of deck you want to play: ");
        int deckNum = 1;
        int threshold = 13;
        while (true){
            deckNum = sc.nextInt();
            if (deckNum <= 0){
                System.out.println("please enter the number greater than 0");
            } else {
                break;
            }
        }

        while (true){
            System.out.println("\n\nPlease select which mode you want to play: ");
            System.out.println("\t1. Player vs Computer. \n\t2. Computer vs Computer");
            int decision = sc.nextInt();
            BlackJackStarter bjs;
            if (decision != 1 && decision != 2){
                System.out.println("your input is invalid, please try again");
            } else if (decision == 1){
                new BlackJackPtpStarter(deckNum).startGame();
            } else {
                System.out.println("For computer vs computer, we are using simple strategy. " +
                        "\nplease specify the threshold for computer to stand (computer user will stand if current hand exceed this number): ");
                while (true){
                    threshold = sc.nextInt();
                    if (threshold > 21 || threshold <= 0){
                        System.out.println("input invalid, threshold must be greater than 0 and cannot be greater than 21");
                    } else {
                        break;
                    }
                }

                System.out.println("Please enter the number of games you want computers to play");
                int numberOfGames;
                while (true) {
                    numberOfGames= sc.nextInt();
                    if (numberOfGames <= 0){
                        System.out.println("invalid input, number of games for computer vs computer must be greater than 0");
                    } else {
                        break;
                    }
                }
                new BlackJackAutoGameStarter(
                        new SimpleStrategy(threshold),
                        new SimpleStrategy(21),
                        numberOfGames - 1,
                        deckNum).startGame();
                break;
            }
        }

    }
}
