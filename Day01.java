import java.io.IOException;

public class Day01 extends AoC {

    public static void main(String args[]) throws IOException {
        new Day01().solve();
    }

    void solve() throws IOException {
        parseFileEnter();
        int total=0;
        for (String s : input) {
            total += totalFuel(Integer.parseInt(s), 0);
        }
        log(total);
    }

    int totalFuel(int i, int total) {
        int fuel = i / 3 - 2;
        if (fuel > 0) {
            total += fuel;
            return totalFuel(fuel, total);
        } else return total;
    }
}