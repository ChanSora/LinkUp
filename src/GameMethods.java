import java.util.Collections;
import java.util.Random;

import util.Utils.Pair;

public class GameMethods {
    static Random rand = new Random();
    static void generatePattern(GameCore game, Pair a, Pair b) {
        int pattern;
        int x1 = a.x, y1 = a.y, x2 = b.x, y2 = b.y;
        Pair a_ = new Pair(x1 - 1, y1 - 1);
        Pair b_ = new Pair(x2 + 1, y2 + 1);
        if (x1 == x2) {
            pattern = rand.nextInt(GameCore.pattern_number) + 1;
            for (int i = y1; i <= y2; i++) game.setGrid(x1, i, pattern);
            generatePattern(game, a_, b_);
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (!isCoordinateValid(i, j, game.getRows(), game.getCols())) continue;
                if (game.getGrid(i, j) == 0) game.Points.add(new Pair(i, j));
            }
        }
        if (game.Points.isEmpty()) return;
        Collections.shuffle(game.Points);
        while (game.Points.size() > 1) {
            Pair p1 = game.Points.get(0);
            Pair p2 = game.Points.get(1);
            game.Points.remove(p1);
            game.Points.remove(p2);
            pattern = rand.nextInt(GameCore.pattern_number) + 1;
            game.setGrid(p1.x, p1.y, pattern);
            game.setGrid(p2.x, p2.y, pattern);
        }
        generatePattern(game, a_, b_);
    }
    static boolean isCoordinateValid(int x, int y, int rows, int cols) {
        return (0 <= x && x < rows && 0 <= y && y < cols);
    }
//    static boolean check(int x, int y, int rows, int cols) {
//        return (0 <= x && x < rows && 0 <= y && y < cols);
//    }
}
