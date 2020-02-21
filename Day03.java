import HelperClasses.*;
import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Math.abs;

public class Day03 extends AoC {

    ArrayList<Coordinate> coordinatesFirst = new ArrayList<>();
    ArrayList<Coordinate> coordinatesSecond = new ArrayList<>();

    ArrayList<Coordinate> intersections = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Day03().solve();
    }

    void solve() throws IOException {
        parseFileEnter();
        makeCoordinates();
        System.out.println(checkIntersections());
    }

    void makeCoordinates(){
        ArrayList<String> inputFirst = new ArrayList<>();
        ArrayList<String> inputSecond = new ArrayList<>();
        String[] getallen = input.get(0).split(",");
        for(String s:getallen){
            inputFirst.add(s.trim());
        }
        getallen = input.get(1).split(",");
        for(String s:getallen){
            inputSecond.add(s.trim());
        }
        coordinatesFirst = getCoordinates(inputFirst);
        coordinatesSecond = getCoordinates(inputSecond);

    }

    ArrayList<Coordinate> getCoordinates(ArrayList<String> input){
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int totalSteps =0;
        int x=0;
        int y=0;
        for(String s:input){
            char direction = s.charAt(0);
            int steps = Integer.parseInt(s.substring(1));
            for(int i=0;i<steps;i++){
                if(direction=='R') x++;
                if(direction=='L') x--;
                if(direction=='U') y++;
                if(direction=='D') y--;
                totalSteps++;
                coordinates.add(new Coordinate(x,y,totalSteps));
            }
        }
        return coordinates;
    }

    int checkIntersections(){
        int closestsDistance=10000010;
        for(Coordinate c:coordinatesFirst){
            for(Coordinate c2:coordinatesSecond){
                if(c.equals(c2)){
                    int distance = c.totalSteps + c2.totalSteps;
                    if(distance<closestsDistance) closestsDistance = distance;
                }
            }
        }
        return closestsDistance;
    }
}
