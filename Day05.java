import java.io.IOException;
import java.util.ArrayList;

public class Day05 extends AoC{

    ArrayList<Integer> numbers = new ArrayList<>();
    int start = 5;
    int tri= 12234644;

    public static void main(String[] args) throws Exception {
        new Day05().solve();
    }

    void solve() throws Exception {
        parseFileSplit();
        for(String str:super.input){
            numbers.add(Integer.parseInt(str));
        }

        for(int i=0;i<numbers.size();i++){
            log("i is:"+i);

            String opCodeS = Integer.toString(numbers.get(i));
            int length = opCodeS.length();
            int opCode = opCodeS.charAt(length-1)-'0';
            log("opcode =  "+opCode);
            int indexDestiny=0;
            int secondInt=0;
            int firstInt=0;

            if(opCode<3||opCode==7||opCode==8){

                if(length<3){
                    indexDestiny = numbers.get(i+3);
                    secondInt = numbers.get(numbers.get(i+2));
                    firstInt = numbers.get(numbers.get(i+1));
                }
                else if(length==3){
                    int param1=opCodeS.charAt(length-3)-'0';

                    if(param1==1) firstInt=numbers.get(i+1);
                    else firstInt = numbers.get(numbers.get(i+1));
                    indexDestiny = numbers.get(i+3);
                    secondInt = numbers.get(numbers.get(i+2));

                }
                else if(length==4){
                    int param1=opCodeS.charAt(length-3)-'0';
                    int param2=opCodeS.charAt(length-4)-'0';

                    if(param1==1) firstInt=numbers.get(i+1);
                    else firstInt = numbers.get(numbers.get(i+1));

                    if(param2==1) secondInt=numbers.get(i+2);
                    else secondInt = numbers.get(numbers.get(i+2));

                    indexDestiny = numbers.get(i+3);

                }
                else if(length==5){
                    int param1=opCodeS.charAt(length-3)-'0';
                    int param2=opCodeS.charAt(length-4)-'0';
                    int param3=opCodeS.charAt(length-5)-'0';

                    if(param1==1) firstInt=numbers.get(i+1);
                    else firstInt = numbers.get(numbers.get(i+1));

                    if(param2==1) secondInt=numbers.get(i+2);
                    else secondInt = numbers.get(numbers.get(i+2));

                    if(param3==1){
                        System.out.println("ouch");
                        indexDestiny = i+3;
                    }
                    else indexDestiny = numbers.get(i+3);
                } else {
                    System.out.println(length + "555");
                }

                if(opCode==1){
                    numbers.set(indexDestiny,firstInt+secondInt);
                }
                if(opCode==2){
                    numbers.set(indexDestiny,firstInt*secondInt);
                }
                if(opCode==7){
                    if(firstInt<secondInt) numbers.set(indexDestiny,1) ;
                    else numbers.set(indexDestiny,0) ;
                }
                if(opCode==8){
                    if(firstInt==secondInt) numbers.set(indexDestiny,1) ;
                    else numbers.set(indexDestiny,0) ;
                }
                i=i+3;

            } else if(opCode==3){
                numbers.set(numbers.get(i+1),start);
                i++;
            } else if(opCode==4){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';

                    if(param1==1) log("output: "+numbers.get(i+1));
                    else log("output: "+numbers.get(numbers.get(i+1)));
                } else {
                    log("output: "+numbers.get(numbers.get(i+1)));
                }
                //maybe also mode 1 is possible

                i++;
            }
            else if(opCode==5){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';
                    if(param1==1) firstInt=numbers.get(i+1);
                    else firstInt = numbers.get(numbers.get(i+1));
                } else {
                    firstInt = numbers.get(numbers.get(i+1));
                }
                if(firstInt!=0){
                    if(length>3){
                        int param2=opCodeS.charAt(length-4)-'0';
                        if(param2==1) i=numbers.get(i+2)-1;
                        else i = numbers.get(numbers.get(i+2))-1;
                    }
                    else i = numbers.get(numbers.get(i+2))-1;

                } else {
                    i=i+2;
                }
            }
            else if(opCode==6){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';
                    if(param1==1) firstInt=numbers.get(i+1);
                    else firstInt = numbers.get(numbers.get(i+1));
                } else {
                    firstInt = numbers.get(numbers.get(i+1));
                }
                if(firstInt==0){
                    if(length>3){
                        int param2=opCodeS.charAt(length-4)-'0';
                        if(param2==1) i=numbers.get(i+2)-1;
                        else i = numbers.get(numbers.get(i+2))-1;
                    }
                    else i = numbers.get(numbers.get(i+2))-1;

                } else {
                    i=i+2;
                }
            }

            else if(opCode==9){
                log("99");
                System.exit(0);

            } else {
                log("er gaat iets mis");
            }


        }
    }


}