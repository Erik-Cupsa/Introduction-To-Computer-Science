package assignment2;

public class Caterpillar extends MyDoublyLinkedList<Position> {
    private Position position;
    public Caterpillar() {
        this.position = new Position(7,7);
        super.add(this.position);
    }

    public Position getHead() {
        return super.peekFirst();
    }

    public void eat(Position food){
        if(Math.abs(this.position.getX() - food.getX()) + Math.abs(this.position.getY() - food.getY()) == 1){
            super.addFirst(food);
        }
        else{
            throw new IllegalArgumentException("Not orthogonal");
        }
    }

    public void move(Position next){
        if(Math.abs(this.position.getX() - next.getX()) + Math.abs(this.position.getY() - next.getY()) == 1){
            super.addFirst(next);
            super.removeLast();
        }
        else{
            throw new IllegalArgumentException("Not orthogonal");
        }
    }

    public boolean selfCollision(Position self){
        for (Position p : this) {
            if (p.equals(self) && !p.equals(getHead())) {
                return true;
            }
        }
        return false;
    }
}
