import HelperClasses.Amplifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Day07 extends AoC {
    ArrayList<Integer> numbers;

    int highestOutput=0;

    public static void main(String[] args) throws Exception {
        new Day07().solve();
    }

    void solve() throws Exception {
        parseFileSplit();

        for(int een=5;een<10;een++){
            for(int twee=5;twee<10;twee++){
                if(twee==een) continue;
                for(int drie=5;drie<10;drie++){
                    if(drie==een||drie==twee) continue;
                    for(int vier=5;vier<10;vier++){
                        if(vier==een||vier==twee||vier==drie) continue;
                        for(int vijf=5;vijf<10;vijf++){
                            if(vijf==een||vijf==twee||vijf==drie||vijf==vier) continue;

                            numbers=new ArrayList<>();
                            for(String str:super.input){
                                numbers.add(Integer.parseInt(str));
                            }

                            ArrayList<Integer> numbers1 = new ArrayList(numbers);
                            ArrayList<Integer> numbers2 = new ArrayList(numbers);
                            ArrayList<Integer> numbers3 = new ArrayList(numbers);
                            ArrayList<Integer> numbers4 = new ArrayList(numbers);
                            ArrayList<Integer> numbers5 = new ArrayList(numbers);



                            Amplifier a1= new Amplifier(een, numbers1);
                            int output1 = a1.go(0);
                            Amplifier a2= new Amplifier(twee,numbers2);
                            int output2 = a2.go(output1);
                            Amplifier a3= new Amplifier(drie,numbers3);
                            int output3 = a3.go(output2);
                            Amplifier a4= new Amplifier(vier,numbers4);
                            int output4 = a4.go(output3);
                            Amplifier a5= new Amplifier(vijf,numbers5);
                            int output5 = a5.go(output4);

                            int last5 = output5;

                            while(true){
                                output1 = a1.go(output5);
                                if(output5==55555555){
                                    log(1);
                                    break;
                                }
                                output2 = a2.go(output1);
                                if(output2==55555555){
                                    log(2);
                                    break;
                                }
                                output3 = a3.go(output2);
                                if(output3==55555555){
                                    log(3);
                                    break;
                                }
                                output4 = a4.go(output3);
                                if(output4==55555555){
                                    log(4);
                                    break;
                                }
                                output5 = a5.go(output4);
                                if(output5==55555555){
                                    log(5);
                                    break;
                                }
                                last5=output5;
                            }

                            if(last5>highestOutput){
                                highestOutput=output5;
                            }

                        }
                    }
                }
            }

        }
        log(highestOutput);
    }

    int runTheProgram(int input, int amp, int pointer, ArrayList<Integer> numbers){
        boolean first=true;
        for(int i=pointer;i<numbers.size();i++){
            //log("i is:"+i);

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

                if(first){
                    numbers.set(numbers.get(i+1),amp);
                } else {
                    numbers.set(numbers.get(i+1),input);
                }
                first=false;
                i++;
            } else if(opCode==4){
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
                log("99");
                return 55555555;

            } else {
                log("er gaat iets mis");
            }


        }
        log("er gaat iets mis");
        return 333333333;
    }


}
