import java.io.IOException;

public class One extends AoC {

    int total=0;

    public static void main(String args[]) throws IOException {
        new One().solve();
    }

    void solve() throws IOException {
        parseFileEnter();
        for(String s:input){
            int i = Integer.parseInt(s);
            total+=totalVal(i);
        }
        System.out.println(total);

    }

    int totalVal(int i){
        int tot = 0;
        int calc =i;
        while(true){
            int x = calc(calc);
            if(x<=0) break;
            else {
                calc = x;
            }
            tot+=x;
        }
        return tot;
    }

    int calc(int i){
        return i/3-2;
    }
}
