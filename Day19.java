import HelperClasses.IntComputer;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math.*;
import java.util.Collections;

import static java.lang.Math.toIntExact;

public class Day19 extends AoC{

    ArrayList<Long> numbers = new ArrayList<>();
    long[][] grid = new long[2000][2000];

    public static void main(String[] args) throws Exception {
        new Day19().solve();
    }

    void solve() throws Exception {
        parseFileSplit();
        makeNumbers();
        makeGrid();
        checkSquare();
    }

    private void checkSquare() {
        for  (int y = 0; y<1900; y++) {
            for (int x = 0; x<1900; x++) {
                if(squareFound(y,x)){
                    log("found!");
                    log(x);
                    log(y);
                    System.exit(0);
                }
            }
        }
    }

    private boolean squareFound(int startY, int startX) {
        for(int y=startY; y<startY+100;y++){
            for(int x=startX; x<startX+100;x++){
                if(grid[y][x]==0L) return false;
            }
        }
        return true;
    }


    private void makeGrid() {
        for (int y = 0; y<2000; y++) {
            for (int x = 0; x<2000; x++) {
                grid[y][x] = new IntComputer(new ArrayList(numbers),x,y).run();
            }
        }
    }


    private void makeNumbers() {
        for(String str:super.input){
            numbers.add(Long.parseLong(str));
        }
        for(int i=0;i<1000;i++){
            numbers.add(0L);
        }
    }

    private void printGrid() {
        for (int y = 0; y < grid.length; y++) {
            long[] chars = grid[y];
            System.out.println();
            for (int x = 0;x < chars.length; x++) {
                long aChar = chars[x];
                System.out.print(aChar);
            }
        }
    }

}