import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math.*;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.toIntExact;

public class Day17 extends AoC{

    ArrayList<Long> numbers = new ArrayList<>();
    long start = 10L;
    long relativeBase=0L;

    char[][] grid = new char[41][63];
    int indexx=0;

    List<Long> inputs;

    public static void main(String[] args) throws Exception {
        new Day17().solve();
    }

    long getInput(){
        log("GETINPUTTTT!!!!!!!!!!!!!!!!!!!!!!");
        long input = inputs.get(indexx);
        indexx++;
        return input;
    }

    private void makeInputs() {
        inputs = Arrays.asList(66L,44L,66L,44L,65L,44L,67L,44L,65L,44L,67L,44L,65L,44L,67L,44L,65L,44L,66L,10L,76L, 44L, 49L, 50L, 44L, 82L, 44L, 54L, 44L, 76L, 44L, 56L, 44L, 76L, 44L, 49L, 50L, 10L,82L, 44L, 54L, 44L, 76L, 44L, 49L, 50L, 44L,82L, 44L, 54L, 10L,82L, 44L, 49L, 50L, 44L, 76L, 44L, 49L, 48L, 44L,  76L, 44L, 49L, 48L, 10L,110L,10L);
    }

    void output(long output){
        log(output);
    }


    void printGrid(){
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i];
            System.out.println();
            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                System.out.print(aChar);
            }
        }
    }


    void solve() throws Exception {
        //makeGrid();
        //getRoute();
        makeInputs();
        parseFileSplit();
        for(String str:super.input){
            numbers.add(Long.parseLong(str));

        }
        log("size:");
        for(int i=0;i<10000;i++){
            numbers.add(0L);
        }

        for(int i=0;i<numbers.size();i++){
            //log("i is:"+i);

            String opCodeS = Long.toString(numbers.get(i));
            int length = opCodeS.length();
            int opCode = opCodeS.charAt(length-1)-'0';
            //log("opcode =  "+opCode);
            long indexDestiny=0;
            long secondInt=0;
            long firstInt=0;

            if(opCode<3||opCode==7||opCode==8){

                if(length<3){
                    try{
                        indexDestiny = numbers.get(i+3);
                        firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                        secondInt = numbers.get(toIntExact(numbers.get(i+2)));
                    } catch(Exception e){
                        log("oeps");
                    }


                }
                else if(length==3){
                    int param1=opCodeS.charAt(length-3)-'0';

                    if(param1==1) firstInt=numbers.get(i+1);
                    else if(param1==2) firstInt = numbers.get(toIntExact(numbers.get(i+1)+relativeBase));
                    else firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                    indexDestiny = numbers.get(i+3);

                    secondInt = numbers.get(toIntExact(numbers.get(i+2)));

                }
                else if(length==4){
                    int param1=opCodeS.charAt(length-3)-'0';
                    int param2=opCodeS.charAt(length-4)-'0';

                    if(param1==1){
                        firstInt=numbers.get(i+1);
                    }
                    else if(param1==2) firstInt = numbers.get(toIntExact(numbers.get(i+1)+relativeBase));
                    else {
                        firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                    }

                    if(param2==1) secondInt=numbers.get(i+2);
                    else if(param2==2) secondInt = numbers.get(toIntExact(numbers.get(i+2)+relativeBase));
                    else secondInt = numbers.get(toIntExact(numbers.get(i+2)));

                    indexDestiny = numbers.get(i+3);

                }
                else if(length==5){
                    int param1=opCodeS.charAt(length-3)-'0';
                    int param2=opCodeS.charAt(length-4)-'0';
                    int param3=opCodeS.charAt(length-5)-'0';

                    if(param1==1) firstInt=numbers.get(i+1);
                    else if(param1==2) firstInt = numbers.get(toIntExact(numbers.get(i+1)+relativeBase));
                    else firstInt = numbers.get(toIntExact(numbers.get(i+1)));

                    if(param2==1) secondInt=numbers.get(i+2);
                    else if(param2==2) secondInt = numbers.get(toIntExact(numbers.get(i+2)+relativeBase));
                    else secondInt = numbers.get(toIntExact(numbers.get(i+2)));

                    if(param3==1){
                        System.out.println("ouch");
                        indexDestiny = i+3;
                    }
                    if(param3==2){
                        indexDestiny = numbers.get(i+3)+relativeBase;
                    }
                    else indexDestiny = numbers.get(i+3);
                } else {
                    System.out.println(length + "555");
                }

                if(opCode==1){
                    numbers.set(toIntExact(indexDestiny),firstInt+secondInt);

                }
                if(opCode==2){
                    long ans = (long)firstInt*(long)secondInt;
                    numbers.set(toIntExact(indexDestiny),ans);
                }
                if(opCode==7){
                    if(firstInt<secondInt) numbers.set(toIntExact(indexDestiny),1L) ;
                    else numbers.set(toIntExact(indexDestiny),0L) ;
                }
                if(opCode==8){
                    if(firstInt==secondInt) numbers.set(toIntExact(indexDestiny),1L) ;
                    else numbers.set(toIntExact(indexDestiny),0L) ;
                }
                i=i+3;

            } else if(opCode==3){
                if(length==3) {
                    int param1 = opCodeS.charAt(length - 3) - '0';
                    if(param1==2){
                        numbers.set(toIntExact(numbers.get(i+1)+relativeBase),getInput());
                    } else {
                        System.out.println("yyessir");
                    }
                } else {
                    numbers.set(toIntExact(numbers.get(i+1)),getInput());
                }

                i++;
            } else if(opCode==4){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';

                    if(param1==1) output(numbers.get(i+1));
                    else if(param1==2 ) {
                       output(+numbers.get(toIntExact(numbers.get(i+1)+relativeBase)));
                    }
                    else output(numbers.get(toIntExact(numbers.get(i+1))));
                } else {
                    output(numbers.get(toIntExact(numbers.get(i+1))));
                }

                i++;
            }
            else if(opCode==5){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';
                    if(param1==1) firstInt=numbers.get(i+1);
                    else if(param1==2) firstInt = numbers.get(toIntExact(numbers.get(i+1)+relativeBase));
                    else firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                } else {
                    firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                }
                if(firstInt!=0){
                    if(length>3){
                        int param2=opCodeS.charAt(length-4)-'0';
                        if(param2==1) i=toIntExact(numbers.get(i+2))-1;
                        else if(param2==2) i= toIntExact(numbers.get(toIntExact(numbers.get(i+2)+relativeBase))-1);
                        else i = toIntExact(numbers.get(toIntExact(numbers.get(i+2)))-1);
                    }
                    else i = toIntExact(numbers.get(toIntExact(numbers.get(i+2))))-1;

                } else {
                    i=i+2;
                }
            }
            else if(opCode==6){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';
                    if(param1==1) firstInt=numbers.get(i+1);
                    else if(param1==2) firstInt = numbers.get(toIntExact(numbers.get(i+1)+relativeBase));
                    else firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                } else {
                    firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                }
                if(firstInt==0){
                    if(length>3){
                        int param2=opCodeS.charAt(length-4)-'0';
                        if(param2==1) i=toIntExact(numbers.get(i+2))-1;
                        else if(param2==2) i = toIntExact(numbers.get(toIntExact(numbers.get(i+2)+relativeBase)))-1;
                        else i = toIntExact(numbers.get(toIntExact(numbers.get(i+2))))-1;
                    }
                    else i = toIntExact(numbers.get(toIntExact(numbers.get(i+2))))-1;

                } else {
                    i=i+2;
                }
            }

            else if(opCode==9){
                if(length==1)  {
                    relativeBase+=numbers.get(toIntExact(numbers.get(i+1)));
                }
                else if(opCodeS.charAt(length-2)-'0'!=9){
                    int param1=opCodeS.charAt(length-3)-'0';
                    if(param1==0){
                        relativeBase+=numbers.get(toIntExact(numbers.get(i+1)));
                    } else if(param1==1){
                        relativeBase+=numbers.get(i+1);
                    } else if(param1==2){
                        int index = toIntExact(numbers.get(i+1)+relativeBase);
                        relativeBase+=numbers.get(index);
                    }

                } else{
                    log("99");
                    System.exit(0);
                }
                i++;

            } else {
                log("er gaat iets mis");
            }
        }
    }


}