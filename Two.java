import java.io.IOException;
import java.util.ArrayList;

public class Two extends AoC {
    ArrayList<Integer> numbers;

    public static void main(String[] args) throws IOException {
        new Two().solve();
    }

    void solve() throws IOException {
        parseFileSplit();
        for(int one=0;one<100;one++){
            for(int two=0;two<100;two++){
                makeNumbers(one,two);
                for(int i=0;i<numbers.size();i++){
                    if(i%4==0){
                        int opCode = numbers.get(i);
                        int firstInt = numbers.get(numbers.get(i+1));
                        int secondInt = numbers.get(numbers.get(i+2));
                        int indexDestiny = numbers.get(i+3);
                        if(opCode==1){
                            numbers.set(indexDestiny,firstInt+secondInt);
                        } else if(opCode==2){
                            numbers.set(indexDestiny,firstInt*secondInt);
                        } else if(opCode==99){
                            if(numbers.get(0)==19690720) log(100*one + two);
                            break;
                        } else {
                            System.out.println("er gaat iets mis");
                        }
                    }
                }
            }
        }

    }

    void makeNumbers(int one, int two){
        numbers = new ArrayList<>();
        for(int i=0;i<input.size();i++){
            if(i==1){
                numbers.add(one);
            }
            else if(i==2){
                numbers.add(two);
            } else {
                numbers.add(Integer.parseInt(input.get(i)));
            }
        }
    }
}
