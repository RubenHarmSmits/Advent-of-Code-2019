import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import static java.lang.Math.pow;
import static oracle.jrockit.jfr.events.Bits.longValue;

public class Day22 extends AoC {

    private BigInteger num(long n) {
        return new BigInteger(Long.toString(n));
    }

    static long modInverse(long a, long m) {
        BigInteger aB = new BigInteger(String.valueOf(a));
        BigInteger mB = new BigInteger(String.valueOf(m));
        BigInteger inverse = aB.modInverse(mB);
        return longValue(inverse);
    }

    public static void main(String[] args) throws IOException {
        new Day22().solve();
    }

    long n = 101741582076661L;
    long d = 119315717514047L;
    long index = 2020L;
    long[] indexes = new long[3];

    private void solve() throws IOException {
        parseFileEnter();
        Collections.reverse(input);
        makeIndexes();

        BigInteger xB = num(indexes[0]);
        BigInteger yB = num(indexes[1]);
        BigInteger zB = num(indexes[2]);
        BigInteger nB = num(n);
        BigInteger dB = num(d);

        BigInteger aB = (yB.subtract(zB)).multiply((xB.subtract(yB)).modInverse(dB)).mod(dB);
        BigInteger bB = yB.subtract(aB.multiply(xB)).remainder(dB);

        BigInteger answerPart2 = aB.modPow(nB, dB).subtract(num(1L)).multiply(aB.subtract(num(1)).modInverse(dB).multiply(bB)).add(aB.modPow(nB, dB).multiply(xB)).mod(dB);
        log(answerPart2);
    }
    private void makeIndexes() {
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = index;
            input.forEach((line) -> {
                checkIndex(line);
            });
        }
    }
    private void checkIndex(String line) {
        if (line.indexOf("deal into new stack") >= 0) dealIntoNewStack();
        else if (line.indexOf("deal with increment") >= 0)  dealWithIncrement(Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1)));
        else cutNCards(Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1)));
    }
    void dealIntoNewStack() {
        index = d - index - 1;
    }
    void cutNCards(long param) {
        index = (index + param + d) % d;

    }
    void dealWithIncrement(long param) {
        BigInteger inverseB = new BigInteger(String.valueOf(modInverse(param, d)));
        index = longValue(inverseB.multiply(num(index)).mod(num(d)));
    }
}