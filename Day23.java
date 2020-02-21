import HelperClasses.IntComputer;
import HelperClasses.IntComputer21;
import HelperClasses.IntComputer23;
import HelperClasses.Packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day23 extends AoC{
    public static void main(String[] args) throws IOException {
        new Day23().solve();
    }

    Packet lastPacket;
    long lastDeliveredY;
    boolean end;

    private void solve() throws IOException {
        parseFileSplit();
        List<IntComputer23> computers = makeComputers();
        int count=0;
        while(!end){
            for (IntComputer23 computer : computers) {
                if(count>10000){
                    if(lastDeliveredY==lastPacket.y) end=true;
                    computers.get(0).inputQue.add(lastPacket);
                    lastDeliveredY = lastPacket.y;
                }
                Object output = computer.run();
                count++;
                if(output instanceof Packet){
                    Packet outP = (Packet) output;
                    count=0;
                    if((outP).adress==255L) lastPacket = outP;
                    computers.forEach(c->{
                        if(c.adres==outP.adress) c.inputQue.add(outP);
                    });
                }
            }
        }
        log(lastDeliveredY);
    }

    private ArrayList makeNumbers() {
        ArrayList<Long> numbers = new ArrayList<>();
        for(String str:super.input){
            numbers.add(Long.parseLong(str));
        }
        for(int i=0;i<100000;i++){
            numbers.add(0L);
        }
        return numbers;
    }
    private ArrayList<IntComputer23> makeComputers() {
        ArrayList<Long> numbers = makeNumbers();
        ArrayList<IntComputer23> computers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            computers.add(new IntComputer23(new ArrayList(numbers),i));
        }
        return computers;
    }
}

