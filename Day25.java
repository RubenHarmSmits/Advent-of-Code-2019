import HelperClasses.Intcomputer25;

import java.io.IOException;
import java.util.ArrayList;

public class Day25 extends AoC {
    public static void main(String[] args) throws IOException {
        new Day25().solve();
    }

    ArrayList<Long> numbers = new ArrayList<>();

    private void solve() throws IOException {
        makeNumbers();
        new Intcomputer25(numbers).run();

    }

    private void makeNumbers() throws IOException {
        parseFileSplit();
        for(String str:super.input){
            numbers.add(Long.parseLong(str));
        }
        for(int i=0;i<10000;i++){
            numbers.add(0L);
        }
    }
}
