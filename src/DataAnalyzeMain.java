import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



public class DataAnalyzeMain {
    private static final String CSV_NAME = "./black_jack_data.csv";

    public static void dataCollection() throws FileNotFoundException {
        List<int[]> dataList = new ArrayList<>();
        List<String> dataListString = new ArrayList<>();
        for (int i = 10; i < 21; i++){
            for (int j = 10; j < 21; j++){
                BlackJackStarter newGame = new BlackJackAutoGameStarter(
                        new SimpleStrategy(i),
                        new SimpleStrategy(j),
                        10000 - 1,
                        2);
                newGame.startGame();
                dataListString.add(""+i+","+j+","+newGame.getPlayerWins()+","+newGame.getDealerWins()+"\n");
                dataList.add(new int[]{i, j, newGame.getPlayerWins(), newGame.getDealerWins()});
            }
        }
//        for (int[] list: dataList){
//            for (int i: list){
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }
        File csvDataFil = new File(CSV_NAME);
        try (PrintWriter pw = new PrintWriter(csvDataFil)){
            for (String s: dataListString){
                pw.write(s);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        dataCollection();
    }
}
