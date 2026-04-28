import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import util.Utils.Pair;

public class GameMethods {
    static Random rand = new Random();
    static ArrayList<Integer> bag = null;
    private GameMethods() {
        rand.setSeed(System.currentTimeMillis());
    }
    static private void generatePattern(GameCore game, Pair a, Pair b, ArrayList<Integer> bag) {
        int pattern;
        int x1 = a.x, y1 = a.y, x2 = b.x, y2 = b.y;
        Pair a_ = new Pair(x1 - 1, y1 - 1);
        Pair b_ = new Pair(x2 + 1, y2 + 1);
        if (x1 == x2) {
//            pattern = rand.nextInt(game.pattern_number) + 1;
            pattern = bag.getFirst();
            bag.removeFirst();

            for (int i = y1; i <= y2; i++) game.setGrid(x1, i, pattern);
            generatePattern(game, a_, b_, bag);
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
            pattern = bag.getFirst();
            bag.removeFirst();
            game.setGrid(p1.x, p1.y, pattern);
            game.setGrid(p2.x, p2.y, pattern);
        }
        generatePattern(game, a_, b_, bag);
    }
    static void generatePattern(GameCore game) {
        int area = game.getCols() * game.getRows();
        bag = buildPatternBag(area / 2, game.pattern_number);
        Pair a, b;
        int init_x = rand.nextInt(game.getRows());
        int init_y = rand.nextInt(game.getCols() - 1);
        a = new Pair(init_x, init_y);
        b = new Pair(init_x, init_y + 1);
        generatePattern(game, a, b, bag);
    }
    static boolean isCoordinateValid(int x, int y, int rows, int cols) {
        return (0 <= x && x < rows && 0 <= y && y < cols);
    }
    static private ArrayList<Integer> buildPatternBag(int pairCount, int differentPatternCount) {
        //list：存储的数据为图案编号，然后在后期，例如{1,2,3,4,5}，在后期取出来在棋盘上所表现得效果为
        // 1 1 2 2 3 3 4 4 5 5(忽略换行)，也就是每两个格子放一个图案，图案的编号由list当中的数字决定
        ArrayList<Integer> bag = new ArrayList<>();

        //先确保每一个编号都出现一次
        for (int i = 1; i <= differentPatternCount; i++) {
            bag.add(i);
        }

        //当包里面的图案不足以摆满棋盘的时候就一直加
        while (bag.size() < pairCount) {
            bag.add(rand.nextInt(differentPatternCount) + 1);//考虑到nextint是从0开始，于是加1
        }

        Collections.shuffle(bag);//打乱顺序
        return bag;
    }
//    static boolean check(int x, int y, int rows, int cols) {
//        return (0 <= x && x < rows && 0 <= y && y < cols);
//    }
}
