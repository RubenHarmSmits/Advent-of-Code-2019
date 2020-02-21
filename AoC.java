import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AoC {
    ArrayList<String> input = new ArrayList<>();

    ArrayList<String> parseFileEnter() throws IOException {
        File fl = new File("Input");
        BufferedReader br = new BufferedReader(new FileReader(fl));
        String line;
        while((line = br.readLine())!=null){
            input.add(line.trim());
        }

        return input;
    }

    ArrayList<String> parseFileSplit() throws IOException {
        File fl = new File("Input");
        BufferedReader br = new BufferedReader(new FileReader(fl));
        String line = br.readLine();

        String[] getallen = line.split(",");
        for(String s:getallen){
            input.add(s.trim());
        }
        //print();
        return input;
    }

    void print(){
        for(Object o:input){
            System.out.println(o);
        }
    }

    void printCharGrid(char[][] grid){
        for (int y = 0; y < grid.length; y++) {
            char[] chars = grid[y];
            System.out.println();
            for (int x = 0;x < chars.length; x++) {
                char aChar = chars[x];
                System.out.print(aChar);
            }
        }
        System.out.println();
    }


    void calculate(){
        for(int y=0;y<1;y++){
            for(int x=0;x<1;x++){}
        }
    }

    void log(Object o){
        System.out.println(o);
    }

    void log(int i){
        System.out.println(i);
    }

}
