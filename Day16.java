import java.io.IOException;

public class Day16 extends AoC {
    public static void main(String[] args) throws IOException {
        new Day16().solve();
    }

    int[] basePattern = new int[]{0, 1, 0, -1};
    int[] numbers;
    int phases;
    long totalOutput;
    int offset;
    int length;

    void solve() throws IOException {
        parseFileEnter();
        makeNumbers();

        while (phases < 100) {
            for (int i = 0; i < length; i++) {
                numbers[i] = getSum(i + 1);
            }
            phases++;
            log(phases);
        }
        for (int i = 0; i < 8; i++) {
            System.out.print(numbers[i]);
        }
    }

    private int getSum(int n) {
        int sum = 0;
        for (int i = length-1; i > n-2; i--) {
            sum += numbers[i];
        }
        String sumS = Integer.toString(sum);
        return Integer.parseInt(String.valueOf(sumS.charAt(sumS.length() - 1)));
    }

    private void makeNumbers() {
        String inp = input.get(0);
        StringBuilder ss = new StringBuilder(inp);
        for(int i=1;i<10000;i++){
            ss.append(inp);
        }
        String inputS = ss.toString();
        String offsetS = inp.substring(0,7);
        offset = Integer.parseInt(offsetS);
        numbers = new int[inputS.length()-offset];
        for (int i = 0; i < inputS.length()-offset; i++) {
            numbers[i]= inputS.charAt(offset+i)-'0';
        }
        length=numbers.length;
    }
}