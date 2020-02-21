import HelperClasses.Grid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day24 extends AoC {
    public static void main(String[] args) throws IOException {
        new Day24().solve();
    }
    ArrayList<Grid> grids = new ArrayList<>();
    final int MINUTES = 200;

    private void solve() throws IOException {
        parseFileEnter();
        Grid startGrid = new Grid(0,makeGrid());
        grids.add(startGrid);

        Grid gridD = startGrid;
        Grid gridU = startGrid;

        for(int i=1;i<=MINUTES+1;i++){
            gridD.downGrid = new Grid(-i,makeEmptyGrid());
            gridD.downGrid.upGrid = gridD;
            gridD = gridD.downGrid;

            gridU.upGrid = new Grid(i,makeEmptyGrid());
            gridU.upGrid.downGrid = gridU;
            gridU = gridU.upGrid;

            grids.add(gridU);
            grids.add(gridD);
        }

        for(int i=0;i<MINUTES;i++){
            for (Grid grid : grids) {
                grid.makeChanges();
            }
            for (Grid grid : grids) {
                grid.appplyChanges();
            }

        }

        int total=0;

        for (Grid grid : grids) {
            grid.printGrid();
            total+=grid.getAmountOfBugs();
        }

        log(total);

    }
    private char[][] makeEmptyGrid() {
        char[][] emptyGrid = new char[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                emptyGrid[y][x] = '.';
            }
        }
        emptyGrid[2][2]='?';
        return emptyGrid;
    }

    private char[][] makeGrid() {
        char[][] grid = new char[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                grid[y][x] = input.get(y).charAt(x);
            }
        }
        return grid;
    }
}
