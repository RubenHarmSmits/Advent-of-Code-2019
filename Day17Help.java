import HelperClasses.Direction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static HelperClasses.Direction.*;

public class Day17Help extends AoC {
    public static void main(String[] args) throws IOException {
        new Day17Help().solve();
    }

    char[][] grid = new char[43][65];
    Direction direction = Direction.Up;
    int y=17;
    int x=1;
    StringBuilder route = new StringBuilder();

    public void solve() throws IOException {
        makeGrid();
        while(getScaffolds()>0){
            //printGrid();
            if(insight()){
                move();
            } else {
                turn();
            }
        }
        log(route);
        //printGrid();
    }

    private void turn() {
        switch (direction){
            case Up:
                direction = Right;
                if(insight()){
                    route.append('R');
                    break;
                }
                else direction = Left;
                route.append('L');
                break;
            case Down:
                direction = Right;
                if(insight()){
                    route.append('L');
                    break;
                }
                else direction = Left;
                route.append('R');
                break;
            case Right:
                direction = Up;
                if(insight()){
                    route.append('L');
                    break;
                }
                else direction = Down;
                route.append('R');
                break;
            case Left:
                direction = Up;
                if(insight()){
                    route.append('R');
                    break;
                }
                else direction = Down;
                route.append('L');
                break;
        }
    }


    private boolean insight() {
        switch (direction){
            case Up:
                if(grid[y-1][x]=='#') return true;
                break;
            case Down:
                if(grid[y+1][x]=='#') return true;
                break;
            case Left:
                if(grid[y][x-1]=='#') return true;
                break;
            case Right:
                if(grid[y][x+1]=='#') return true;
                break;
        }
        return false;
    }

    void move(){
        if(isCrossOver()) grid[y][x]='#';
        else grid[y][x]='.';
        switch (direction){
            case Up:
                y--;
                break;
            case Down:
                y++;
                break;
            case Left:
                x--;
                break;
            case Right:
                x++;
                break;
        }
        grid[y][x]='O';
        appendRoute();
    }

    private void appendRoute() {
        char last = route.charAt(route.length()-1);
        try{
            int times = Integer.parseInt(String.valueOf(last));
            if(times==9){
                route.setCharAt(route.length()-1,Character.forDigit(1,10));
                route.append(Character.forDigit(0,10));
            } else {
                times++;
                route.setCharAt(route.length()-1,Character.forDigit(times,10));
            }

        } catch(Exception e){
            route.append(Character.forDigit(1,10));
        }

    }

    private boolean isCrossOver() {
        switch (direction){
            case Up:
                if(grid[y][x+1]=='#') return true;
                if(grid[y][x-1]=='#') return true;
                break;
            case Down:
                if(grid[y][x+1]=='#') return true;
                if(grid[y][x-1]=='#') return true;
                break;
            case Left:
                if(grid[y+1][x]=='#') return true;
                if(grid[y-1][x]=='#') return true;
                break;
            case Right:
                if(grid[y+1][x]=='#') return true;
                if(grid[y-1][x]=='#') return true;
                break;
        }
        return false;
    }

    int getScaffolds(){
        int scaffolds=0;
        for (char[] chars : grid) {
            for (char aChar : chars) {
                if(aChar=='#') scaffolds++;
            }
        }
        return scaffolds;
    }

    void makeGrid() throws IOException {
        File fl = new File("grid");
        BufferedReader br = new BufferedReader(new FileReader(fl));
        String line;
        int y=0;
        while((line = br.readLine())!=null){
            for(int i=0;i<line.length();i++){
                grid[y][i]=line.charAt(i);
            }
            y++;
        }
    }

    void printGrid(){
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i];
            System.out.println();
            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                System.out.print(aChar);
            }

        }
        log("direction:"+direction);
    }
}
