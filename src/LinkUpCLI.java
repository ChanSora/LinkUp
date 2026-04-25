import java.util.Scanner;
import java.util.Random;
import static util.Utils.Pair;

public class LinkUpCLI {
    public static void main(String[] args) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Link Up CLI!");
        System.out.println("Please enter the mode (1.Easy or 2.Hard) would like to play:");

        int type = input.nextInt();
        if (type == 1) GameCore.pattern_number = Constants.EASY_PATTERN_NUMBER;
        else if (type == 2) GameCore.pattern_number = Constants.HARD_PATTERN_NUMBER;

        System.out.println("Please enter the area of the chessboard:(a * b)");

        int rows = input.nextInt();
        int cols = input.nextInt();
        int init_x = rand.nextInt(rows);
        int init_y = rand.nextInt(cols - 1);
        Pair A0 = new Pair(init_x, init_y);
        Pair B0 = new Pair(init_x, init_y + 1);

        GameCore game = new GameCore(rows, cols);
        GameMethods.generatePattern(game, A0, B0);
        game.showGrid();
    }
}
