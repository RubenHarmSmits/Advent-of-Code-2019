package HelperClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Key extends Item {
    public Coordinate coordinate;
    public Door door;
    public String name;
    public ArrayList<Option> options;

    public Key(Coordinate coordinate, String name) {
        this.coordinate = coordinate;
        this.name = name;
    }

    public ArrayList<Option> getFilteredOptions(ArrayList<Key> keysFound) {
        ArrayList filteredOptions = new ArrayList();
        for(Option o:options){
            if(keysFound.containsAll(o.requirements)&&!keysFound.contains(o.destination)&&noKeysInBetween(o,keysFound)){
                filteredOptions.add(o);
            }
        }
        return filteredOptions;
    }

    private boolean noKeysInBetween(Option o, ArrayList<Key> keysFound) {
        List<Key> filteredList =  o.keysInbetween.stream().filter(key->!keysFound.contains(key)).collect(Collectors.toList());
        return filteredList.size()==1;
    }
}
