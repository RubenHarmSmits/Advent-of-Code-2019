package HelperClasses;

import java.util.ArrayList;

import static java.lang.Math.toIntExact;

public class IntComputer23 {

    long relativeBase=0L;
    ArrayList<Long> numbers;

    public long adres;
    boolean hasAdres=false;

    int outputNr;
    long outputDes;
    long outputX;
    long outputY;
    boolean sendPackage=false;

    public ArrayList<Packet> inputQue = new ArrayList<>();
    int inputNr;

    int index=0;


    public IntComputer23(ArrayList<Long> numbers, long adres){
        this.numbers = numbers;
        this.adres=adres;

    }

    private long getInput() {
        if(!hasAdres){
            hasAdres = true;
            return adres;
        } else {
            if(inputQue.size()==0) return -1L;
            else {
                Packet packet = inputQue.get(0);
                if(inputNr==0) {
                    inputNr++;
                    return packet.x;
                } else {
                    inputNr--;
                    inputQue.remove(0);
                    return packet.y;
                }
            }
        }
    }

    private void output(long output){
        if(outputNr==0){
            outputDes = output;
            outputNr++;
        } else if(outputNr==1){
            outputX = output;
            outputNr++;
        } else {
            outputY = output;
            outputNr=0;
            sendPackage = true;
        }
    }


    public Object run(){
        for(int i=index;i<numbers.size();i++){
            String opCodeS = Long.toString(numbers.get(i));
            int length = opCodeS.length();
            int opCode = opCodeS.charAt(length-1)-'0';
            long indexDestiny=0;
            long secondInt=0;
            long firstInt=0;

            if(opCode<3||opCode==7||opCode==8){

                if(length<3){
                    indexDestiny = numbers.get(i+3);
                    firstInt = numbers.get(toIntExact(numbers.get(i+1)));
                    secondInt = numbers.get(toIntExact(numbers.get(i+2)));
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

            }
            else if(opCode==3){
                long input = getInput();
                if(length==3) {
                    int param1 = opCodeS.charAt(length - 3) - '0';
                    if(param1==2){
                        numbers.set(toIntExact(numbers.get(i+1)+relativeBase),input);
                    } else {
                        System.out.println("yyessir");
                    }
                } else {
                    numbers.set(toIntExact(numbers.get(i+1)),input);
                }
                if(input==-1L) {
                    index=i+2;
                    return 0L;
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
                if(sendPackage){
                    i+=2;
                    index=i;
                    sendPackage=false;
                    return new Packet(outputX,outputY,outputDes);
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
                    log(99);
                    return 0L;
                }
                i++;

            } else {
                log("er gaat iets mis");
            }
        }
        log("end");
        return 0L;
    }


    void log(Object o){
        System.out.println(o);
    }

    void log(int i){
        System.out.println(i);
    }

}
