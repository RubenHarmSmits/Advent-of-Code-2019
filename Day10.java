import HelperClasses.Coordinate10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.abs;

public class Day10 extends AoC{
    int size =21;
    int[][] grid = new int[size][size];
    ArrayList<Coordinate10> coordinates = new ArrayList<>();
    int y=0;
    int x=0;


    public static void main(String[] args) throws IOException {
        new Day10().solve();
    }

    void solve() throws IOException {
        parseFileEnter();
        makeGrid();
        calculate();
        log(this.x);
        log(this.y);
        solve2();
        //log("");
    }

    void solve2(){
        makeCoordinates();
        sortCoordinates();
    }

    void sortCoordinates(){
        coordinates.sort((c1,c2)->Double.compare(c1.angle,c2.angle));
        shoot();
    }

    void shoot(){
        int count=0;
        double lastAngle=99.0;
        for(int i=0;i<coordinates.size();i++){
            Coordinate10 c= getFirst(i,coordinates.get(i));
            double angle = c.angle;
            if(lastAngle!=c.angle){
                lastAngle = c.angle;
                count++;
                log(count+"-c:x="+(c.x+this.x)+",y="+(c.y+this.y));
            }

        }

    }

    Coordinate10 getFirst(int i, Coordinate10 first){
        Coordinate10 c= coordinates.get(i);
        Coordinate10 c2 = coordinates.get(i+1);
        if(c.angle!=c2.angle) return first;
        else{
            Coordinate10 closest = getClosest(c,c2);
            return getFirst(i+1,closest);
        }
    }

    Coordinate10 getClosest(Coordinate10 one, Coordinate10 two){
        if(abs(one.x-this.x)<abs(two.x-this.x)) return one;
        return two;
    }

    void makeCoordinates(){
        for(int y=0;y<size;y++){
            for(int x=0;x<size;x++){
                if(grid[y][x]==1&&!(y==this.y&&x==this.x)){
                    Coordinate10 coor = new Coordinate10(y-this.y,x-this.x);
                    coordinates.add(coor);
                }
            }
        }
    }

    int calculateAmount(int y, int x){
        int amount=0;
        for(int yyy=0;yyy<size;yyy++){
            for(int xxx=0;xxx<size;xxx++){
                if(grid[yyy][xxx]==1){
                    if(x!=xxx||y!=yyy){
                        if(canDetect(y,x,yyy,xxx)){
                            amount++;
                        }
                    }
                }
            }
        }
        return amount;
    }

    boolean canDetect(int y, int x, int yyy, int xxx){
        for(int yy=0;yy<size;yy++){
            for(int xx=0;xx<size;xx++){
                if(grid[yy][xx]==1){
                    if(notTheSame(x,xx,xxx,y,yy,yyy)){
                        if(straightBlock(y,yy,yyy,x,xx,xxx)) return false;
                        if(diaBlock(y,yy,yyy,x,xx,xxx)) return false;
                    }
                }
            }
        }
        return true;
    }

    boolean straightBlock(int y,int yy,int yyy,int x,int xx,int xxx){
        if(y==yy&&yy==yyy){
            if(x>xx&&xx>xxx){
                return true;
            }
            if(x<xx&&xx<xxx){
                return true;
            }
        }
        if(x==xx&&xx==xxx){
            if(y>yy&&yy>yyy){
                return true;
            }
            if(y<yy&&yy<yyy){
                return true;
            }
        }
        return false;
    }

    boolean diaBlock(int y,int yy,int yyy,int x,int xx,int xxx) {
        int dy = y - yy;
        int dx = x - xx;
        int dyyy = yy - yyy;
        int dxxx = xx - xxx;

        try{
            if ((double)dy / (double)dx == (double)dyyy / (double)dxxx) {
                if(dy >= 0 && dyyy >= 0 || dy < 0 && dyyy < 0){
                    if(dx >= 0 && dxxx >= 0 || dx < 0 && dxxx < 0){
                        return true;
                    }
                }
            }
        } catch(Exception e){
            return false;
        }

        return false;
    }

    boolean notTheSame(int x,int xx,int xxx,int y,int yy,int yyy){
        if(x==xx&&y==yy)return false;
        if(xx==xxx&&yyy==yy)return false;

        return true;
    }

    void calculate(){
        int highest=0;
        for(int y=0;y<size;y++){
            for(int x=0;x<size;x++){
                if(grid[y][x]==1){
                    int amount = calculateAmount(y,x);
                    if(amount>highest){
                        highest = amount;
                        this.x=x;
                        this.y=y;
                    }
                }
            }
        }
    }

    void makeGrid(){
        for(int y=0;y<input.size();y++){
            String line = input.get(y);
            for(int x=0;x<line.length();x++){
                grid[y][x] = line.charAt(x)=='#'? 1:0;
            }
        }
    }
}
