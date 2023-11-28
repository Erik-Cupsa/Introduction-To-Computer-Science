package assignment2;

public class Region {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Region(int minX, int minY, int maxX, int maxY){
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public boolean contains(Position position){
        int x = position.getX();
        int y = position.getY();
        if(x>=this.minX && x <= this.maxX && y >= this.minY && y <= this.maxY){
            return true;
        }
        return false;
    }
}
