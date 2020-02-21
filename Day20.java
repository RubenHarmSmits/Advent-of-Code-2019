import HelperClasses.Coordinate;
import HelperClasses.Coordinate15;
import HelperClasses.Direction;
import HelperClasses.Portal;

import java.io.IOException;
import java.util.*;

import static HelperClasses.Direction.*;

public class Day20 extends AoC {
    public static void main(String[] args) throws IOException {
        new Day20().solve();
    }

    ArrayList<Portal> portals = new ArrayList<>();
    ArrayList<Portal> newPortals = new ArrayList<>();
    char[][] grid;

    int YY;
    int XX;

    int level=0;

    private void solve() throws IOException {
        parseFileEnter();
        grid=makeGrid();
        makePortals();
        changePortals();
        makeDestinations();
        changeDestinations();
        calculateShortestDistance();
    }

    private void changeDestinations() {
        for(Portal p:newPortals){
            Portal old = getPortalByNameAndInside(p.name, p.isInside);

            for(Map.Entry<Portal,Integer> entry:old.destinations.entrySet()){
                Portal oldDestination = entry.getKey();
                int oldDistance = entry.getValue();

                Portal destination;

                int distance = oldDistance;
                if(distance==1){
                    if(oldDestination.isInside){
                        destination = getPortalByNameAndLevelAndInside(oldDestination.name,p.level-1, oldDestination.isInside);
                    } else {
                        destination = getPortalByNameAndLevelAndInside(oldDestination.name,p.level+1, oldDestination.isInside);
                    }

                } else {
                    destination = getPortalByNameAndLevelAndInside(oldDestination.name,p.level, oldDestination.isInside);
                }

                if(destination!=null){
                    String destName  = destination.name;

                    p.destinations.put(destination,distance);
                }
            }
        }
        changeDestinations2();
    }

    private void changeDestinations2() {

    }

    private void changePortals() {
        for(Portal p:portals){
            if(p.isInside||p.name.equals("AA")||p.name.equals("ZZ")) newPortals.add(new Portal(p.coordinate,p.name,p.isInside,0));
        }
        for(int i=1;i<100;i++){
            for(Portal p:portals){
                if(!p.name.equals("AA")&&!p.name.equals("ZZ")) newPortals.add(new Portal(p.coordinate,p.name,p.isInside,i));
            }
        }
    }

    Portal getPortalByNameAndLevelAndInside(String name, int level, boolean inside){
        for(Portal p: newPortals){
            if(p.name.equals(name) && p.level ==level&&p.isInside==inside) {
                return p;
            }
        }
        return null;
    }

    private void calculateShortestDistance() {
        Set<Portal> settled = new HashSet<>();
        Set<Portal> unSettled = new HashSet<>();

        Portal start = getPortalByNameAndLevelAndInside("AA",0, false);;
        start.smallestDistance = 0;
        unSettled.add(start);

        while(!unSettled.isEmpty()){
            Portal pre = unSettled.stream().min(Comparator.comparing(Portal::getDistance)).get();
            for(Map.Entry<Portal,Integer> entry:pre.destinations.entrySet()){
                Portal portal = entry.getKey();
                if(!settled.contains(portal)){
                    int newDistance = entry.getValue()+pre.smallestDistance;
                    if(newDistance<portal.smallestDistance) {
                        portal.smallestDistance = newDistance;
                    }
                    unSettled.add(portal);
                }
            }
            settled.add(pre);
            unSettled.remove(pre);
        }

        log(getPortalByNameAndLevelAndInside("ZZ",0,false).smallestDistance);
    }

    Portal getPortalByNameAndInside(String name, boolean isInside){
        for(Portal p :portals){
            if(p.name.equals(name)&&p.isInside==isInside) return p;
        }
        return null;
    }


    private void makeDestinations() {
        for(Portal p:portals){
            lookAround(p.coordinate,p.destinations,checkDirectionStart(p.coordinate),-1);
            p.destinations.remove(p);
            if(p.teleport!=null) p.destinations.put(p.teleport,1);
        }

    }

    private Direction checkDirectionStart(Coordinate15 c) {
        if(Character.isLetter(grid[c.y+1][c.x])) return Up;
        if(Character.isLetter(grid[c.y-1][c.x])) return Down;
        if(Character.isLetter(grid[c.y][c.x+1])) return Left;
        if(Character.isLetter(grid[c.y][c.x-1])) return Right;
        return null;
    }


    private void lookAround(Coordinate15 coor, HashMap<Portal, Integer> destinations, Direction direction, int steps) {
        steps++;
        int y = coor.y;
        int x = coor.x;

        char up = grid[y-1][x];
        char down = grid[y+1][x];
        char left = grid[y][x-1];
        char right = grid[y][x+1];

        Portal p = getPortal(coor);
        if(p!=null) {
            if(destinations.get(p)!=null)log("shityes");
            destinations.put(p,steps);
        }

        switch(direction){
            case Up:
                if(up=='.') lookAround(new Coordinate15(y-1,x),destinations,Up,steps);
                if(left=='.') lookAround(new Coordinate15(y,x-1),destinations,Left,steps);
                if(right=='.') lookAround(new Coordinate15(y,x+1),destinations,Right,steps);
                break;
            case Down:
                if(down=='.') lookAround(new Coordinate15(y+1,x),destinations,Down,steps);
                if(left=='.') lookAround(new Coordinate15(y,x-1),destinations,Left,steps);
                if(right=='.') lookAround(new Coordinate15(y,x+1),destinations,Right,steps);
                break;
            case Right:
                if(up=='.') lookAround(new Coordinate15(y-1,x),destinations,Up,steps);
                if(down=='.') lookAround(new Coordinate15(y+1,x),destinations,Down,steps);
                if(right=='.') lookAround(new Coordinate15(y,x+1),destinations,Right,steps);
                break;
            case Left:
                if(up=='.') lookAround(new Coordinate15(y-1,x),destinations,Up,steps);
                if(down=='.') lookAround(new Coordinate15(y+1,x),destinations,Down,steps);
                if(left=='.') lookAround(new Coordinate15(y,x-1),destinations,Left,steps);
                break;
        }

    }

    private Portal getPortal(Coordinate15 c) {
        for(Portal p:portals){
            if((p.coordinate!=null&&p.coordinate.isEqual(c))) return p;
        }
        return null;
    }

    private void makePortals() {
        for (int y = 0; y < YY; y++) {
            for (int x = 0; x < XX; x++) {
                if(Character.isLetter(grid[y][x])) makePortal(y,x);
            }
        }
    }

    private void makePortal(int y, int x) {
        char first='?';
        char second='?';
        Coordinate15 portal=null;
        if(y<grid.length-1&&Character.isLetter(grid[y+1][x])){
            if(y<grid.length-2&&grid[y+2][x]=='.'){
                first = grid[y][x];
                second = grid[y+1][x];
                portal = new Coordinate15(y+2,x);
            }
            else if(grid[y-1][x]=='.') {
                first = grid[y][x];
                second = grid[y+1][x];
                portal = new Coordinate15(y-1,x);
            }
        }
        else if(Character.isLetter(grid[y-1][x])){
            if(y>1&&grid[y-2][x]=='.'){
                first = grid[y-1][x];
                second = grid[y][x];
                portal = new Coordinate15(y-2,x);
            }
            else if(grid[y+1][x]=='.') {
                first = grid[y-1][x];
                second = grid[y][x];
                portal = new Coordinate15(y+1,x);
            }
        }
        else if(x<XX-1&&Character.isLetter(grid[y][x+1])){
            if(x<XX-2&&grid[y][x+2]=='.'){
                first = grid[y][x];
                second = grid[y][x+1];
                portal = new Coordinate15(y,x+2);
            }
            else if(grid[y][x-1]=='.') {
                first = grid[y][x];
                second = grid[y][x+1];
                portal = new Coordinate15(y,x-1);
            }
        }
        else if(Character.isLetter(grid[y][x-1])){
            if(x>1&&grid[y][x-2]=='.'){
                first = grid[y][x-1];
                second = grid[y][x];
                portal = new Coordinate15(y,x-2);
            }
            else if(grid[y][x+1]=='.') {
                first = grid[y][x-1];
                second = grid[y][x];
                portal = new Coordinate15(y,x+1);
            }
        }
        String name = new StringBuilder().append(first).append(second).toString();

        if(portalIsNew(name)){
            portals.add(new Portal(portal,name,isInside(y,x)));
        } else {
            for(Portal p:portals){
                if(p.name.equals(name)){
                    if(!p.coordinate.isEqual(portal)&&p.teleport==null){
                        Portal newPortal = new Portal(portal,name, isInside(y,x));
                        p.teleport = newPortal;
                        newPortal.teleport = p;
                        portals.add(newPortal);
                        break;
                    }
                }
            }
        }

    }

    private boolean isInside(int y, int x) {
        if(y>18&&y<90&&x<100&&x>10) return true;
        return false;
    }


    private boolean portalIsNew(String name) {
        for(Portal p:portals){
            if(p.name.equals(name))return false;
        }
        return true;
    }



    private char[][] makeGrid() {
        YY=input.size();
        XX=input.get(0).length();
        char[][] grid = new char[YY][XX];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x]= input.get(y).charAt(x);
            }
        }
        return grid;
    }

}
