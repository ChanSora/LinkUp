import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class LinkUpCLI {
    static private final int HARD_PATTERN_NUMBER = 12;
    static private final int EASY_PATTERN_NUMBER = 5;
    static int count = 0, pattern_number = 0;
    static Random rand = new Random();
    public static void main(String[] args) {
        rand.setSeed(System.currentTimeMillis());
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Link Up CLI!");
        System.out.println("Please enter the mode (1.Easy or 2.Hard) would like to play:");
        int type = input.nextInt();
        if (type == 1) pattern_number = EASY_PATTERN_NUMBER;
        else if (type == 2) pattern_number = HARD_PATTERN_NUMBER;
        System.out.println("Please enter the area of the chessboard:(a * b)");
        int rows = input.nextInt();
        int cols = input.nextInt();
        int [][] grid = new int [rows][cols];
        int init_x = rand.nextInt(rows);
        int init_y = rand.nextInt(cols - 1);
        Pair A0 = new Pair(init_x, init_y);
        Pair B0 = new Pair(init_x, init_y + 1);
        ArrayList<Pair> pairs = new ArrayList<>();
        generatePattern(grid, A0, B0, rows, cols, pairs);
        for (int[] row : grid) {
            for (int j : row) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    static void generatePattern(int[][] grid, Pair a, Pair b, int rows, int cols, ArrayList<Pair> pairs) {
        int pattern;
        int x1 = a.x, y1 = a.y, x2 = b.x, y2 = b.y;
        Pair a_ = new Pair(x1 - 1, y1 - 1);
        Pair b_ = new Pair(x2 + 1, y2 + 1);
        if (x1 == x2) {
            pattern = rand.nextInt(pattern_number) + 1;
            for (int i = y1; i <= y2; i++) grid[x1][i] = pattern;
            generatePattern(grid, a_, b_, rows, cols, pairs);
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (!check(i, j, rows, cols)) continue;
                if (grid[i][j] == 0) pairs.add(new Pair(i, j));
            }
        }
        if (pairs.isEmpty()) return;
        Collections.shuffle(pairs);
        while (pairs.size() > 1) {
            Pair p1 = pairs.get(0);
            Pair p2 = pairs.get(1);
            pairs.remove(p1);
            pairs.remove(p2);
            pattern = rand.nextInt(pattern_number) + 1;
            grid[p1.x][p1.y] = pattern;
            grid[p1.x][p2.y] = pattern;
        }
        generatePattern(grid, a_, b_, rows, cols, pairs);
    }
    static boolean check(int x, int y, int rows, int cols) {
        return (0 <= x && x < rows && 0 <= y && y < cols);
    }
    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
