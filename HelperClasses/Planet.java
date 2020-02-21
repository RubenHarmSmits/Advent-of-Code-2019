package HelperClasses;

import java.util.ArrayList;

public class Planet {
    public String name;
    public Planet direct;
    ArrayList<Planet> indirect;
    //ArrayList<Planet> indirect;

    public Planet(String name){
        this.name=name;
    }

    public int getOrbits(){
        if(direct==null) return 0;
        int orbits=1;
        orbits+= direct.getOrbits();
        return orbits;
    }

    public ArrayList<Planet> getIndirect(){
        if(direct==null){
            return new ArrayList<Planet>();
        }
        indirect = direct.getIndirect();
        indirect.add(direct);
        return indirect;
    }
}
