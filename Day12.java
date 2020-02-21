import HelperClasses.Coordinate12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day12 extends AoC{

    ArrayList<Coordinate12> coordinaten = new ArrayList<>();
    ArrayList<ArrayList<Integer>> statesX = new ArrayList<>();
    ArrayList<ArrayList<Integer>> statesY = new ArrayList<>();
    ArrayList<ArrayList<Integer>> statesZ = new ArrayList<>();
    int y;
    int x;
    int z;
    boolean yf=false;
    boolean xf=false;
    boolean zf=false;

    public static void main(String args[]) throws IOException {
        new Day12().solve();
    }

    void solve() throws IOException {
        makeCoordinaten();
        move();
    }

    private void move() {
        long steps=0;
        while(steps<500000){
            addToStates();
            updateVelocity();
            coordinaten.forEach(c->c.move());
            //if(steps%5000==0) log(steps);

//            if(!zf&&checkIfDoubleZ()){
//                log("Z:"+steps);
//                zf=true;
//            }
//            if(!yf&&checkIfDoubleY()){
//                log("Y:"+steps);
//                yf=true;
//            }
//            if(!xf&&checkIfDoubleX()){
//                log("X:"+steps);
//                xf=true;
//            }
            steps++;
        }
        log("done stepping");
        checkIfDoubleX();
        checkIfDoubleY();
        checkIfDoubleZ();
    }

    private boolean checkIfDoubleZ() {
        int count=0;
        for(ArrayList<Integer> state:statesZ){
            for(ArrayList<Integer> stateFlex:statesZ){
                count++;
                if(state.equals(stateFlex)){
                    if(state!=stateFlex){
                        //log(statesZ.lastIndexOf(stateFlex));
                        log(count-1);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean checkIfDoubleX() {
        int count=0;
        for(ArrayList<Integer> state:statesX){
            for(ArrayList<Integer> stateFlex:statesX){
                count++;
                if(state.equals(stateFlex)){
                    if(state!=stateFlex){
                        log(count-1);
                        //log(statesX.lastIndexOf(stateFlex));
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean checkIfDoubleY() {
        int count=0;
        for(ArrayList<Integer> state:statesY){
            for(ArrayList<Integer> stateFlex:statesY){
                count++;
                if(state.equals(stateFlex)){
                    if(state!=stateFlex){
                        //log(statesY.lastIndexOf(stateFlex));
                        log(count-1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void addToStates() {
        ArrayList<Integer> stateX = new ArrayList<>();
        ArrayList<Integer> stateY = new ArrayList<>();
        ArrayList<Integer> stateZ = new ArrayList<>();
        coordinaten.forEach(c->{
            stateX.add(c.x);
            stateY.add(c.y);
            stateZ.add(c.z);
            stateX.add(c.dx);
            stateY.add(c.dy);
            stateZ.add(c.dz);
        });

        statesX.add(stateX);
        statesY.add(stateY);
        statesZ.add(stateZ);
    }

    private void calculateTotalEnergy() {
        int total=0;
        for(Coordinate12 c:coordinaten){
            total+=c.calculateTotalEnergy();
        }
        log(total);
    }


    void updateVelocity(){
        for(Coordinate12 c:coordinaten){
            int gravX = calculateGravX(c);
            int gravY = calculateGravY(c);
            int gravZ = calculateGravZ(c);
            c.dx += gravX;
            c.dy += gravY;
            c.dz += gravZ;
        }
    }

    private int calculateGravX(Coordinate12 c) {
        int grav=0;
        for(Coordinate12 c2:coordinaten){
            if(c.x>c2.x) grav--;
            else if(c.x<c2.x) grav++;
        }
        return grav;
    }
    private int calculateGravY(Coordinate12 c) {
        int grav=0;
        for(Coordinate12 c2:coordinaten){
            if(c.y>c2.y) grav--;
            else if(c.y<c2.y) grav++;
        }
        return grav;
    }
    private int calculateGravZ(Coordinate12 c) {
        int grav=0;
        for(Coordinate12 c2:coordinaten){
            if(c.z>c2.z) grav--;
            else if(c.z<c2.z) grav++;
        }
        return grav;
    }

    private void makeCoordinaten() {
//        coordinaten.add(new Coordinate12(-1,0,2));
//        coordinaten.add(new Coordinate12(2,-10,-7));
//        coordinaten.add(new Coordinate12(4,-8,8));
//        coordinaten.add(new Coordinate12(3,5,-1));
//        coordinaten.add(new Coordinate12(-8,-10,0));
//        coordinaten.add(new Coordinate12(5,5,10));
//        coordinaten.add(new Coordinate12(2,-7,3));
//        coordinaten.add(new Coordinate12(9,-8,-3));
        coordinaten.add(new Coordinate12(-5,6,-11));
        coordinaten.add(new Coordinate12(-8,-4,-2));
        coordinaten.add(new Coordinate12(1,16,4));
        coordinaten.add(new Coordinate12(11,11,-4));
    }


}
