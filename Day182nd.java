import HelperClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static HelperClasses.Direction.*;
import static HelperClasses.Direction.Left;

public class Day182nd extends AoC {
    public static void main(String[] args) throws IOException {

        //System.out.println(Character.isLetter(ñ));
        long startTime = System.nanoTime();
        new Day182nd().solve();
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
        makeAllOptions(grid);
        //z,y,x,q
        findFastestWay(findKey("Ñ"),0,new ArrayList<>());

        log("Quickest way = "+quickest);
    }

    private void findFastestWay(Key currentKey, int steps, ArrayList<Key> keysFound) {
        if(steps<quickest){
            keysFound.add(currentKey);
            if(keysFound.size()==keys.size()){
                if(steps<quickest){
                    quickest = steps;
                }
            }
            ArrayList<Option> filteredOptions = currentKey.getFilteredOptions(keysFound);
            for(Option option:filteredOptions){
                findFastestWay(option.destination,steps+option.distance,new ArrayList(keysFound));
            }
        }

    }

    private void makeAllOptions(char[][] grid) {
        for(Key k:keys){
            k.options = makeOptions(k, grid);
        }
    }

    private ArrayList<Option> makeOptions(Key k,char[][] grid) {
        ArrayList<Option> options = new ArrayList();
        findOption(k.coordinate,options, Nothing,-1, grid, new ArrayList<>(),new ArrayList<>());
        return options;
    }

    int quickest = Integer.MAX_VALUE;

    private void findQuickestWay(char[][] grid, int steps) {
        if(steps<quickest){
            //HashMap<Coordinate,Integer> options = findOptions(copyArray(grid));
            //log(options.size());
//            for(Map.Entry<Coordinate,Integer> entry:options.entrySet()){
//                int distance=entry.getValue();
//                char[][] newGrid = makeNewGrid(copyArray(grid),entry.getKey());
//                findQuickestWay(copyArray(newGrid),steps+distance);
//            }
//            if(gridIsEmpty(grid)){
//                if(steps<quickest){
//                    quickest = steps;
//                }
//            }
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

    private Coordinate findMe(char[][] grid) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(grid[y][x]=='@') return new Coordinate(y,x);
            }
        }
        return null;
    }

    void findOption(Coordinate coor, ArrayList<Option> options, Direction direction, int steps, char[][] grid, ArrayList<Key> requirements, ArrayList<Key> keysInBetween){
        steps++;

        int y = coor.y;
        int x = coor.x;

        char up = grid[y-1][x];
        char down = grid[y+1][x];
        char left = grid[y][x-1];
        char right = grid[y][x+1];
        
        if(isDoor(coor, grid)){
            requirements.add(findKey(findDoorByCoor(coor).name));
        }

        if(isKey(coor, grid)&&steps>0){
            options.add(new Option(findKeyByCoor(coor),steps,requirements,keysInBetween));
            keysInBetween.add(findKeyByCoor(coor));
        }
        switch(direction){
            case Up:
                if(up=='.'||Character.isLetter(up)) findOption(new Coordinate(y-1,x),options,Up,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(left=='.'||Character.isLetter(left)) findOption(new Coordinate(y,x-1),options,Left,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(right=='.'||Character.isLetter(right)) findOption(new Coordinate(y,x+1),options,Right,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                break;
            case Down:
                if(down=='.'||Character.isLetter(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(left=='.'||Character.isLetter(left)) findOption(new Coordinate(y,x-1),options,Left,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(right=='.'||Character.isLetter(right)) findOption(new Coordinate(y,x+1),options,Right,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                break;
            case Right:
                if(up=='.'||Character.isLetter(up)) findOption(new Coordinate(y-1,x),options,Up,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(down=='.'||Character.isLetter(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(right=='.'||Character.isLetter(right)) findOption(new Coordinate(y,x+1),options,Right,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                break;
            case Left:
                if(up=='.'||Character.isLetter(up)) findOption(new Coordinate(y-1,x),options,Up,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(down=='.'||Character.isLetter(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(left=='.'||Character.isLetter(left)) findOption(new Coordinate(y,x-1),options,Left,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                break;
            case Nothing:
                if(up=='.'||Character.isLetter(up)) findOption(new Coordinate(y-1,x),options,Up,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(down=='.'||Character.isLetter(down)) findOption(new Coordinate(y+1,x),options,Down,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(left=='.'||Character.isLetter(left)) findOption(new Coordinate(y,x-1),options,Left,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                if(right=='.'||Character.isLetter(right)) findOption(new Coordinate(y,x+1),options,Right,steps,grid,new ArrayList(requirements),new ArrayList(keysInBetween));
                break;
        }

    }

    Door findDoorByCoor(Coordinate coor){
        Door door = doors.stream().filter(k->k.coordinate.equals(coor)).findAny().orElse(null);
        return door;
    }

    private boolean isDoor(Coordinate coor, char[][] grid) {
        boolean isDoor = Character.isUpperCase(grid[coor.y][coor.x]);
        return isDoor;
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
