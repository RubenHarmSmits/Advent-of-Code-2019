package HelperClasses;

import java.util.ArrayList;
import java.util.HashMap;

public class Reaction {
    public String name;
    public int amount;
    public HashMap<Reaction, Integer> ingredients = new HashMap<>();

    public Reaction(String line) {
        this.name = line.substring(line.lastIndexOf(' ') + 1);
        this.amount = Integer.parseInt(line.substring(0, line.indexOf(' ')));
    }

    public int calculateCost(int qd, HashMap<Reaction, Integer> reserves) {
        int cost = 0;
        int qr = getFromReserves(reserves);
        int productions = (qd - qr + this.amount - 1) / this.amount;
        int newReserve = productions * this.amount + qr - qd;
        reserves.put(this, newReserve);

        for (int p = 0; p < productions; p++) {
            for (Reaction r : ingredients.keySet()) {
                int amount = ingredients.get(r);
                if (r.name.equals("ORE")) cost += amount;
                else cost += r.calculateCost(amount, reserves);
            }
        }
        return cost;
    }

    private int getFromReserves(HashMap<Reaction, Integer> reserves) {
        int reserve = reserves.getOrDefault(this, 0);
        reserves.put(this, 0);
        return reserve;
    }
}
