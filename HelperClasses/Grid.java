package HelperClasses;

public class Grid {
    public int depth;
    public char[][] grid;
    boolean[][] changes = new boolean[5][5];

    public Grid upGrid;
    public Grid downGrid;

    public Grid(int depth, char[][] grid) {
        this.depth = depth;
        this.grid = grid;
    }

    public void makeChanges(){
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                changes[y][x] = bug(y, x);
            }
        }
    }

    public void appplyChanges() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (changes[y][x]) grid[y][x] = '#';
                else grid[y][x] = '.';
            }
        }
        grid[2][2]='?';
    }

    private boolean bug(int y, int x) {
        int adjacent = calculateAdjacent(y, x);
        if (grid[y][x] == '#') {
            if (adjacent == 1) return true;
        } else {
            if (adjacent == 1 || adjacent == 2) return true;
        }
        return false;

    }

    private int calculateAdjacent(int y, int x) {
        if(upGrid==(null)) upGrid = new Grid(99,makeEmptyGrid());
        if(downGrid==(null)) downGrid = new Grid(99,makeEmptyGrid());
        int adjacent = 0;

        int tr=0;
        int br=0;
        int lr=0;
        int rr=0;

        for (int yd = 0; yd < 5; yd++) {
            for (int xd = 0; xd < 5; xd++) {
                if(yd==0&&downGrid.grid[yd][xd]=='#')tr++;
                if(yd==4&&downGrid.grid[yd][xd]=='#')br++;
                if(xd==0&&downGrid.grid[yd][xd]=='#')lr++;
                if(xd==4&&downGrid.grid[yd][xd]=='#')rr++;
            }
        }

        if(y==0){
            if (x == 0) {
                if(grid[y+1][x]=='#') adjacent++;
                if(grid[y][x+1]=='#') adjacent++;
                if(upGrid.grid[1][2]=='#') adjacent++;
                if(upGrid.grid[2][1]=='#') adjacent++;
            } else if(x==4){
                if(grid[y+1][x]=='#') adjacent++;
                if(grid[y][x-1]=='#') adjacent++;
                if(upGrid.grid[1][2]=='#') adjacent++;
                if(upGrid.grid[2][3]=='#') adjacent++;
            } else {
                if(grid[y+1][x]=='#') adjacent++;
                if(grid[y][x+1]=='#') adjacent++;
                if(grid[y][x-1]=='#') adjacent++;
                if(upGrid.grid[1][2]=='#') adjacent++;
            }
        }

        if(y==1){
            if (x == 0) {
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (upGrid.grid[2][1] == '#') adjacent++;
            } else if(x==2){
                if (grid[y - 1][x] == '#') adjacent++;
                adjacent+=tr;
                if (grid[y][x + 1] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;

            } else if(x==4){
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (upGrid.grid[2][3] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            } else {
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            }
        }

        if(y==2){
            if (x == 0) {
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (upGrid.grid[2][1] == '#') adjacent++;
            } else if(x==1){
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                adjacent+= lr;
                if (grid[y][x-1] == '#') adjacent++;
            } else if(x==3){
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                adjacent+= rr;
            } else if(x==4){
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (upGrid.grid[2][3] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            }

        }

        if(y==3){
            if (x == 0) {
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (upGrid.grid[2][1] == '#') adjacent++;
            } else if(x==2){
                adjacent+=br;
                if (grid[y + 1][x] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            } else if(x==4){
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (upGrid.grid[2][3] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            } else {
                if (grid[y - 1][x] == '#') adjacent++;
                if (grid[y + 1][x] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            }
        }

        if(y==4){
            if (x == 0) {
                if (grid[y - 1][x] == '#') adjacent++;
                if (upGrid.grid[3][2] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (upGrid.grid[2][1] == '#') adjacent++;
            } else if(x==1){
                if (grid[y - 1][x] == '#') adjacent++;
                if (upGrid.grid[3][2] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            } else if(x==2){
                if (grid[y - 1][x] == '#') adjacent++;
                if (upGrid.grid[3][2] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            } else if(x==3){
                if (grid[y - 1][x] == '#') adjacent++;
                if (upGrid.grid[3][2] == '#') adjacent++;
                if (grid[y][x + 1] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            } else if(x==4){
                if (grid[y - 1][x] == '#') adjacent++;
                if (upGrid.grid[2][3] == '#') adjacent++;
                if (upGrid.grid[3][2] == '#') adjacent++;
                if (grid[y][x - 1] == '#') adjacent++;
            }
        }
        return adjacent;
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

    public void printGrid() {
        for (int y = 0; y < grid.length; y++) {
            char[] chars = grid[y];
            System.out.println();
            for (int x = 0; x < chars.length; x++) {
                char aChar = chars[x];
                System.out.print(aChar);
            }
        }
        System.out.println(depth);
        System.out.println();
    }

    public int getAmountOfBugs() {
        int bugs=0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if(grid[y][x]=='#') bugs++;
            }
        }
        return bugs;
    }
}

//if (y > 0 && grid[y - 1][x] == '#') adjacent++;
//        if (y < 4 && grid[y + 1][x] == '#') adjacent++;
//        if (x < 4 && grid[y][x + 1] == '#') adjacent++;
//        if (x > 0 && grid[y][x - 1] == '#') adjacent++;