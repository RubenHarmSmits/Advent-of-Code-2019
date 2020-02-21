package HelperClasses;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String args[]){
        new Streams().solve();

    }

    private void solve() {
        int[][] grid = new int[][]{{1,2,3,4},{4,3,2,1}};
        System.out.println(grid[0][0]);

        int[][] grid1 = copyArray(grid);
        grid1[0][0]=99;
        System.out.println(grid[0][0]);
        System.out.println(grid1[0][0]);


    }

    int[][] copyArray(int[][] arr){
        int[][] copy = Arrays.stream(arr).map(int[]::clone).toArray(int[][]::new);
        return copy;
    }
}
