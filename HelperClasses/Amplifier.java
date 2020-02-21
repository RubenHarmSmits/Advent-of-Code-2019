package HelperClasses;

import java.util.ArrayList;

public class Amplifier {

    public int phaseSetting;
    public ArrayList<Integer> numbers;
    public int pointer=0;
    boolean first=true;

    public Amplifier(int phaseSetting, ArrayList<Integer> numbers){
        this.phaseSetting = phaseSetting;
        this.numbers = numbers;
    }

    public int go(int input){
        for(int i=pointer;i<numbers.size();i++){
            //System.out.println("i is begin:"+i);

            String opCodeS = Integer.toString(numbers.get(i));
            int length = opCodeS.length();
            int opCode = opCodeS.charAt(length-1)-'0';
            //log("opcode =  "+opCode);
            int indexDestiny=0;
            int secondInt=0;
            int firstInt=0;
            //log(opCode);

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

                if(length==3) {
                    System.out.println("yyessir");
                    int param1 = opCodeS.charAt(length - 3) - '0';
                }
                if(first){
                    numbers.set(numbers.get(i+1),phaseSetting);
                } else {
                    numbers.set(numbers.get(i+1),input);
                }
                first=false;
                i++;
            } else if(opCode==4){
                //.out.println("i is end:"+i);
                pointer=i+2;
                if(length>2){
                    int param1=opCodeS.charAt(length-3)-'0';

                    if(param1==1) return numbers.get(i+1);
                    else return(numbers.get(numbers.get(i+1)));
                } else {
                    return(numbers.get(numbers.get(i+1)));
                }

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
                System.out.println("99!");
                return 55555555;

            } else {
                System.out.println("klopt niet");
            }


        }
        System.out.println("klopt niet");
        return 333333333;
    }




}
