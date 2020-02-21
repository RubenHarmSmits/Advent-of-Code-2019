import HelperClasses.Coordinate15;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math.*;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.random;
import static java.lang.Math.toIntExact;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.blocked;

public class Day15 extends AoC{

    Scanner inputscn = new Scanner(System.in);
    Random random = new Random();
    ArrayList<Long> numbers = new ArrayList<>();
    long relativeBase=0L;
    long start=0;
    int size=21;
    char[][] grid = new char[41][41];
    int y=21;
    int x=21;
    int direction;
    int count=0;

    int yy=33;
    int xx=5;

    public static void main(String[] args) throws Exception {
        new Day15().solve2();
    }

    void solve2() throws IOException {
        parseFileEnter();
        for (int y = 0; y < input.size(); y++) {
            String regel = input.get(y);
            for(int x=0;x<regel.length();x++){
                grid[y][x]=regel.charAt(x);
            }
        }
        grid[yy][xx]='O';
        printGrid();
        int minutes=0;
        while(!gridIsFull()){
            minutes++;
            spreadOxigen();
            //System.out.println(minutes);
        }
        log(minutes);
    }

    private void spreadOxigen() {
        ArrayList<Coordinate15> doelwitten = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            char[] regel = grid[y];
            for(int x=0;x<regel.length;x++){
                if(grid[y][x]=='O'){
                    if(grid[y+1][x]=='.') doelwitten.add(new Coordinate15(y+1,x));
                    if(grid[y-1][x]=='.') doelwitten.add(new Coordinate15(y-1,x));
                    if(grid[y][x+1]=='.') doelwitten.add(new Coordinate15(y,x+1));
                    if(grid[y][x-1]=='.') doelwitten.add(new Coordinate15(y,x-1));

                }
            }
        }
        doelwitten.forEach((d)->{
            grid[d.y][d.x]='O';
        });
        printGrid();
    }

    void printGrid(){
        log("yy="+yy);
        log("xx="+xx);
        for (int y = 0; y < grid.length; y++) {
            char[] chars = grid[y];
            System.out.println();
            for (int x = 0;x < chars.length; x++) {
                char aChar = chars[x];
                System.out.print(aChar);
            }
        }

    }


    private void makeGrid() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
               grid[y][x]=' ';
            }
        }
    }

    boolean gridIsFull(){
        for (int y = 0; y < grid.length; y++) {
            char[] chars = grid[y];
            for (int x = 0;x < chars.length; x++) {
                if(grid[y][x]=='.') return false;
            }
        }
        return true;
    }

    long getInput(){
        log("");
        System.out.println("waar wil je heen? 1:U 2:D 3:W 4:E");
        count++;
        if(count>5000){
            int directionKey = inputscn.nextInt();
            if(directionKey==0){
                count=0;
                direction=3;
            }
            else if(directionKey==99){
                count=-50000;
                direction=3;
            }
            else if(directionKey==8) direction = 1;
            else if(directionKey==5) direction = 2;
            else if(directionKey==4) direction = 3;
            else direction=4;

        } else {
            direction = random.nextInt(4)+1;
        }
        return direction;
    }

    void output(long output){
        switch((int)output){
            case 0:
                blocked();
                break;
            case 1:
                move();
                break;
            case 2:
                log("Gevonden!!!!");
                found();
                break;
        }
        printGrid();
    }

    void move(){
        grid[y][x]='.';
        switch(direction){
            case 1:
                y=y-1;
                break;
            case 2:
                y=y+1;
                break;
            case 3:
                x=x-1;
                break;
            case 4:
                x=x+1;
                break;
        }
    }

    private void blocked() {
        switch(direction){
            case 1:
                grid[y-1][x]='#';
                break;
            case 2:
                grid[y+1][x]='#';
                break;
            case 3:
                grid[y][x-1]='#';
                break;
            case 4:
                grid[y][x+1]='#';
                break;
        }
    }

    private void found() {
        switch(direction){
            case 1:
                y=y-1;
                break;
            case 2:
                y=y+1;
                break;
            case 3:
                x=x-1;
                break;
            case 4:
                x=x+1;
                break;
        }
        grid[y][x]='E';
        yy=y;
        xx=x;
    }

    void solve() throws Exception {
        makeGrid();
        printGrid();
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
                long inputDir = getInput();
                if(length==3) {
                    int param1 = opCodeS.charAt(length - 3) - '0';
                    if(param1==2){
                        numbers.set(toIntExact(numbers.get(i+1)+relativeBase),inputDir);
                    } else {
                        System.out.println("yyessir");
                    }
                } else {
                    numbers.set(toIntExact(numbers.get(i+1)),inputDir);
                }

                i++;
            } else if(opCode==4){
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';

                    if(param1==1) output(numbers.get(i+1));
                    else if(param1==2 ) {
                        output(numbers.get(toIntExact(numbers.get(i+1)+relativeBase)));
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
                    //log("99");
                    System.exit(0);
                }
                i++;

            } else {
                log("er gaat iets mis");
            }
        }
    }

}