import HelperClasses.Planet;

import java.io.IOException;
import java.util.ArrayList;

public class Day06 extends AoC{
    public static void main(String args[]) throws IOException {
        new Day06().solve();
    }

    ArrayList<Planet> planets = new ArrayList<>();

    void solve() throws IOException {
        parseFileEnter();
        makePlanets();
        makeOrbits();
        //calculateOrbits();
        calculateDistence();
    }

    void calculateDistence(){
        Planet you=null;
        Planet san=null;
        for(Planet p:planets){
            if(p.name.equals("YOU")){
                you = p;
            }
            if(p.name.equals("SAN")){
                san = p;
            }
        }
        ArrayList<Planet> youlist= you.getIndirect();
        ArrayList<Planet> sanlist=san.getIndirect();

        int youstep=0;
        int sanstep=0;

        for(int i=youlist.size()-1;i>0;i--){
            youstep++;
            sanstep=0;
            for(int j=sanlist.size()-1;j>0;j--){
                sanstep++;
                if(youlist.get(i)==sanlist.get(j)){
                    log(youstep+sanstep-2);
                    System.exit(0);
                }
            }
        }


    }

    void calculateOrbits(){
        int total=0;
        for(Planet p:planets){
            total+= p.getOrbits();
        }
        log(total);
    }

    void makeOrbits(){
        for(String s:input){
            String middle = s.substring(0,s.indexOf(')'));
            String orbitter = s.substring(s.indexOf(')')+1);
            Planet middleP = getPlanet(middle);
            Planet orbitterP = getPlanet(orbitter);
            orbitterP.direct = middleP;
        }
    }

    void makePlanets(){
        for(String s:input){
            String middle = s.substring(0,s.indexOf(')'));
            String orbitter = s.substring(s.indexOf(')')+1);
            if(planetIsNew(middle)) planets.add(new Planet(middle));
            if(planetIsNew(orbitter)) planets.add(new Planet(orbitter));
        }
    }

    boolean planetIsNew(String name){
        for(Planet p:planets){
            if(p.name.equals(name)) return false;
        }
        return true;
    }

    Planet getPlanet(String name){
        for(Planet p:planets){
            if(p.name.equals(name)) return p;
        }
        return null;
    }
}
