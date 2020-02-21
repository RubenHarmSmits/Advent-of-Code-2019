import HelperClasses.Layer;

import java.io.IOException;
import java.util.ArrayList;

public class Day08 extends AoC {

    String input;
    int wide = 25;
    int tall = 6;
    ArrayList<Layer> layers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Day08().solve();
    }

    void solve() throws IOException {
        parseFileSplit();
        input = super.input.get(0);

        for(int i=0;i<input.length();i+=wide*tall){
             layers.add(new Layer(input.substring(i,i+wide*tall)));
        }

        String str="";

        for(int i=0;i<tall;i++){
            for(int j=0;j<wide;j++){
                int num = getNum(i,j);
                str=str.concat(""+num);
            }
        }
        Layer l = new Layer(str);
        l.print();
        layers.get(1).print();
    }

    int getNum(int i, int j){
        for(Layer l:layers){
            if(l.numbers[i][j]==0) return 0;
            if(l.numbers[i][j]==1) return 1;
        }
        return 2;
    }
}
