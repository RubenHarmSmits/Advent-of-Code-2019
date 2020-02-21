import HelperClasses.Reaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 extends AoC {

    public static void main(String args[]) throws IOException {
        new Day14().solve();
    }

    ArrayList<Reaction> reactions = new ArrayList<>();
    public HashMap<Reaction, Integer> reserves = new HashMap<>();
    long ORE = 1000000000000L;

    void solve() throws IOException {
        parseFileEnter();
        makeReactions();
        makeIngredients();
        caluculateFuel();
    }


    private void caluculateFuel() {
        long totalCost=0L;
        int fuelAmount=0;
        Reaction fuel = findReaction("FUEL");
        while(ORE>=totalCost){
            long OREfor1Fuel=(long)fuel.calculateCost(1,reserves);
            totalCost+=OREfor1Fuel;
            fuelAmount++;
        }
        log(fuelAmount-1);

    }

    private void makeIngredients() {
        input.forEach(line->{
            Reaction product = findReaction(line.substring(line.lastIndexOf(' ')+1));calculate();
            String ingredientsS = line.substring(0,line.indexOf('='));
            String[] ingredientsA = ingredientsS.split(",");
            for(String i:ingredientsA){
                i = i.trim();
                int amount = Integer.parseInt(i.substring(0,i.indexOf(" ")));
                String ingredient = i.substring(i.indexOf(' ')+1);
                product.ingredients.put(findReaction(ingredient),amount);
            }
        });
    }

    private void makeReactions() {
        reactions.add(new Reaction("1 ORE"));
        input.forEach((l)->{
            reactions.add(new Reaction(l.substring(l.indexOf('>')+2)));
        });
    }

    Reaction findReaction(String name){
        for(Reaction r:reactions){
            if(r.name.equals(name)) return r;
        }
        return null;
    }
}
