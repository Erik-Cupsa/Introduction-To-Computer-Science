package assignment2;

import java.util.Iterator;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(Position copy){
        this.x = copy.x;
        this.y = copy.y;
    }

    public void reset(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void reset(Position position){
        this.x = position.x;
        this.y = position.y;
    }

    public static int getDistance(Position pos1, Position pos2){
        int distance;
        distance = Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y - pos2.y);
        return distance;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void moveWest(){
        this.x --;
    }

    public void moveEast(){
        this.x ++;
    }

    public void moveNorth(){
        this.y --;
    }

    public void moveSouth(){
        this.y ++;
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(!(obj instanceof Position)) return false;

        Position toCompare = (Position) obj;
        if(this.x == toCompare.x && this.y == toCompare.y) return true;
        return false;
    }
}
