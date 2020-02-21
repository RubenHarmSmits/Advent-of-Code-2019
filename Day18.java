import HelperClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static HelperClasses.Direction.*;
import static HelperClasses.Direction.Left;

public class Day18 extends AoC {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        new Day18().solve();
        long endTime = System.nanoTime();

        System.out.println("Time:" +((endTime - startTime))/10000);

    }
    int width;
    int height;

    ArrayList<Key> keys;
    ArrayList<Door> doors;

    private void solve() throws IOException {
        parseFileEnter();
        print();
        char[][] grid = makeGrid();
        keys = makeKeys(grid);
        doors = makeDoors(grid);
        findQuickestWay(copyArray(grid),0);

        log("Quickest way = "+quickest);
    }

    int quickest = Integer.MAX_VALUE;

    private void findQuickestWay(char[][] grid, int steps) {
        if(steps<quickest){
            HashMap<Coordinate,Integer> options = findOptions(copyArray(grid));
            //log(options.size());
            for(Map.Entry<Coordinate,Integer> entry:options.entrySet()){
                int distance=entry.getValue();
                char[][] newGrid = makeNewGrid(copyArray(grid),entry.getKey());
                findQuickestWay(copyArray(newGrid),steps+distance);
            }
            if(gridIsEmpty(grid)){
                if(steps<quickest){
                    quickest = steps;
                }
            }
        }

    }

    private boolean gridIsEmpty(char[][] grid) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(Character.isLowerCase(grid[y][x])) return false;
            }
        }
        return true;
    }


//    private int findQuickestWay(char[][] grid, int steps, int minValue) {
//        HashMap<Coordinate,Integer> options = findOptions(copyArray(grid));
//        int lowestSolution = Integer.MAX_VALUE;
//        for(Map.Entry<Coordinate,Integer> entry:options.entrySet()){
//            int distance=entry.getValue();
//            char[][] newGrid = makeNewGrid(copyArray(grid),entry.getKey());
//            printCharGrid(newGrid);
//            return findQuickestWay(copyArray(newGrid),steps+distance,minValue);
//        }
//        if(steps<minValue) return steps;
//        else return minValue;
//    }

    private char[][] makeNewGrid(char[][] grid, Coordinate destination) {
        Key destinationKey = findKeyByCoor(destination);
        Door door = destinationKey.door;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(grid[y][x]=='@') grid[y][x]='.';
                if(new Coordinate(y,x).equals(destinationKey.coordinate)) grid[y][x]='@';
                if(door!=null&&new Coordinate(y,x).equals(door.coordinate)) grid[y][x]='.';
            }
        }
        return grid;
    }

    private Key findKeyByCoor(Coordinate destination) {
        return keys.stream().filter(key->key.coordinate.equals(destination)).findAny().orElse(null);

    }

    private HashMap<Coordinate, Integer> findOptions(char[][] grid) {
        HashMap<Coordinate, Integer> options = new HashMap<>();
        
        findOption(findMe(grid),options, Nothing,-1, grid);

        
        return options;
    }

    private Coordinate findMe(char[][] grid) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(grid[y][x]=='@') return new Coordinate(y,x);
            }
        }
        return null;
    }

    void findOption(Coordinate coor,  HashMap<Coordinate, Integer> options,Direction direction, int steps, char[][] grid){
        steps++;

        int y = coor.y;
        int x = coor.x;

//        System.out.println(direction);
//        System.out.println("Y"+y);
//        System.out.println("X"+x);

        char up = grid[y-1][x];
        char down = grid[y+1][x];
        char left = grid[y][x-1];
        char right = grid[y][x+1];

        if(isKey(coor, grid)){
            options.put(coor,steps);
        } else {
            switch(direction){
                case Up:
                    if(up=='.'||Character.isLowerCase(up)) findOption(new Coordinate(y-1,x),options,Up,steps, grid);
                    if(left=='.'||Character.isLowerCase(left)) findOption(new Coordinate(y,x-1),options,Left,steps, grid);
                    if(right=='.'||Character.isLowerCase(right)) findOption(new Coordinate(y,x+1),options,Right,steps, grid);
                    break;
                case Down:
                    if(down=='.'||Character.isLowerCase(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid);
                    if(left=='.'||Character.isLowerCase(left)) findOption(new Coordinate(y,x-1),options,Left,steps,grid);
                    if(right=='.'||Character.isLowerCase(right)) findOption(new Coordinate(y,x+1),options,Right,steps,grid);
                    break;
                case Right:
                    if(up=='.'||Character.isLowerCase(up)) findOption(new Coordinate(y-1,x),options,Up,steps,grid);
                    if(down=='.'||Character.isLowerCase(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid);
                    if(right=='.'||Character.isLowerCase(right)) findOption(new Coordinate(y,x+1),options,Right,steps,grid);
                    break;
                case Left:
                    if(up=='.'||Character.isLowerCase(up)) findOption(new Coordinate(y-1,x),options,Up,steps,grid);
                    if(down=='.'||Character.isLowerCase(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid);
                    if(left=='.'||Character.isLowerCase(left)) findOption(new Coordinate(y,x-1),options,Left,steps,grid);
                    break;
                case Nothing:
                    if(up=='.'||Character.isLowerCase(up)) findOption(new Coordinate(y-1,x),options,Up,steps,grid);
                    if(down=='.'||Character.isLowerCase(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid);
                    if(left=='.'||Character.isLowerCase(left)) findOption(new Coordinate(y,x-1),options,Left,steps,grid);
                    if(right=='.'||Character.isLowerCase(right)) findOption(new Coordinate(y,x+1),options,Right,steps,grid);
                    break;
            }
        }
    }

    private boolean isKey(Coordinate coor, char[][] grid) {
        boolean isKey = Character.isLowerCase(grid[coor.y][coor.x]);
        return isKey;
    }

    char[][] copyArray(char[][] arr){
        char[][] copy = Arrays.stream(arr).map(char[]::clone).toArray(char[][]::new);
        return copy;
    }

    private ArrayList<Door> makeDoors(char[][] grid) {
        ArrayList<Door> doors = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(Character.isUpperCase(grid[y][x])){
                    String name = Character.toString(Character.toUpperCase(grid[y][x]));
                    Key key = findKey(name);
                    Door door = new Door(new Coordinate(y,x),findKey(name),name);
                    doors.add(door);
                    if(key!=null) key.door = door;
                }
            }
        }
        return doors;
    }

    private Key findKey(String name) {
        Key key = keys.stream().filter(k->k.name.equals(name)).findAny().orElse(null);
        return key;
    }

    private ArrayList<Key> makeKeys(char[][] grid) {
        ArrayList<Key> keys = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(Character.isLowerCase(grid[y][x])) keys.add(new Key(new Coordinate(y,x),Character.toString(Character.toUpperCase(grid[y][x]))));
            }
        }
        return keys;
    }

    private char[][] makeGrid() {
        height=input.size();
        width=input.get(0).length();
        char[][] grid = new char[height][width];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x]= input.get(y).charAt(x);
            }
        }
        return grid;
    }
}
