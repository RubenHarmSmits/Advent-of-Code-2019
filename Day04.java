import java.io.IOException;

public class Day04 {

    int begin =264360;
    int end = 746325;
    int guessed = 107754;
    int possibilities=0;

    public static void main(String[] args) throws IOException {
        new Day04().solve();
    }

    void solve() throws IOException {
        for(int i=begin;i<end;i++){
            if(isPossible(i)) possibilities++;
        }
        System.out.println(possibilities);
    }

    boolean isPossible(int number){
        String str = String.valueOf(number);
        boolean hasEqual=false;
        boolean isAllIncreasing = true;
        for(int i=0;i<str.length()-1;i++){
            int cijfer1 = Integer.parseInt(Character.toString(str.charAt(i)));
            int cijfer2 = Integer.parseInt(Character.toString(str.charAt(i+1)));


            if(cijfer1==cijfer2){
                boolean begin=true;
                boolean end=true;
                if(i<str.length()-2){
                    int cijfer3 = Integer.parseInt(Character.toString(str.charAt(i+2)));
                    if(cijfer3==cijfer2){
                        end = false;
                    }
                }

                if(i>0){
                    int cijfer0 = Integer.parseInt(Character.toString(str.charAt(i-1)));
                    if(cijfer0==cijfer2){
                        begin = false;
                    }
                }
                if(begin&&end){
                    hasEqual=true;
                }
            }
            if(cijfer2<cijfer1) isAllIncreasing = false;
        }

        if(hasEqual&&isAllIncreasing) return true;

        return false;

    }
}
