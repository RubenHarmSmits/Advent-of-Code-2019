import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math.*;

import static java.lang.Math.toIntExact;

public class Day13 extends AoC{

    ArrayList<Long> numbers = new ArrayList<>();
    long relativeBase=0L;

    int[][] grid = new int[24][40];

    int outputCount=0;

    int x;
    int y;
    int count;
    long score;

    public static void main(String[] args) throws Exception {
        new Day13().solve();
    }

    void output(long output){
        outputCount++;
        if(outputCount%3==2) this.y=(int)output;
        if(outputCount%3==1) this.x=(int)output;
        if(outputCount%3==0){
            if(this.x==-1&&this.y==0){
                this.score = output;
                printBoard();
            } else grid[this.y][this.x]=(int)output;
        }
    }

    int getInputPaddle(){
        int positionBall = getPositionBall();
        int positionPaddle = getPositionPaddle();
        if(positionBall<positionPaddle) return -1;
        else if(positionBall>positionPaddle) return 1;
        else return 0;
    }

    private int getPositionPaddle() {
        for(int x=0;x<grid[0].length;x++) {
            if (grid[grid.length - 2][x] == 3) return x;
        }
        return 0;
    }

    private int getPositionBall() {
        for(int y=0;y<grid.length;y++){
            for(int x=0;x<grid[y].length;x++){
                if(grid[y][x]==4) return x;
            }
        }
        return 0;
    }

    void printBoard(){
        for(int y=0;y<grid.length;y++){
            System.out.println();
            for(int x=0;x<grid[y].length;x++){
                System.out.print(grid[y][x]);
            }
        }
        System.out.println();
        log("score = "+score);
    }

    void solve() throws Exception {
        parseFileSplit();
        for(String str:super.input){
            numbers.add(Long.parseLong(str));
        }
        for(int i=0;i<1000;i++){
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
                long inputPaddle = getInputPaddle();
                if(length==3) {
                    int param1 = opCodeS.charAt(length - 3) - '0';
                    if(param1==2){
                        numbers.set(toIntExact(numbers.get(i+1)+relativeBase),inputPaddle);
                    } else {
                        System.out.println("yyessir");
                    }
                } else {
                    numbers.set(toIntExact(numbers.get(i+1)),inputPaddle);
                }
                printBoard();

                i++;
            } else if(opCode==4){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';

                    if(param1==1){
                        output(numbers.get(i+1));
                        //log("output: "+numbers.get(i+1));
                    }
                    else if(param1==2 ) {
                        output(numbers.get(toIntExact(numbers.get(i+1)+relativeBase)));
                        //log("output: "+numbers.get(toIntExact(numbers.get(i+1)+relativeBase)));
                    }
                    else {
                        output(numbers.get(toIntExact(numbers.get(i+1))));
                        //log("output: "+numbers.get(toIntExact(numbers.get(i+1))));
                    }
                } else {
                    output(numbers.get(toIntExact(numbers.get(i+1))));
                   // log("output: "+numbers.get(toIntExact(numbers.get(i+1))));
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
                    System.exit(0);
                }
                i++;

            } else {
                log("er gaat iets mis");
            }
        }
    }
}