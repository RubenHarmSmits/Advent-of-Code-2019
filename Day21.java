import HelperClasses.IntComputer21;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math.*;

import static java.lang.Math.toIntExact;

public class Day21 extends AoC{

    ArrayList<Long> numbers = new ArrayList<>();
    ArrayList<Long> inputs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new Day21().solve();
    }

    private void solve() throws IOException {
        makeNumbers();
        makeInputs();
        tryProgram();
    }

    private void tryProgram() {
        new IntComputer21(new ArrayList(numbers),inputs).run();
    }

    private void makeInputs() {
        addRule("OR A J");
        addRule("AND B J");
        addRule("AND C J");
        addRule("NOT J J");
        addRule("AND D J");
        addRule("OR E T");
        addRule("OR H T");
        addRule("AND T J");
        addRule("RUN");
    }

    private void addRule(String rule) {
        log(rule);
        for (int i = 0; i < rule.length(); i++) {
            inputs.add((long)rule.charAt(i));
        }
        inputs.add((long)10);
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