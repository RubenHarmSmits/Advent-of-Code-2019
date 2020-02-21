package HelperClasses;

public class Layer {
    int wide=25;
    int tall=6;
    public int[][] numbers = new int[6][25];

    public Layer(String numbersS){
        for(int i=0;i<tall;i++){
            for(int j=0;j<wide;j++){
                numbers[i][j] = numbersS.charAt(i*wide+j)-'0';
            }
        }
    }

    public void print(){
        for(int [] a:numbers){
            System.out.println();
            for(int b:a){
                if(b==0) System.out.print("░");
                else System.out.print("█");
            }
        }
    }
}
