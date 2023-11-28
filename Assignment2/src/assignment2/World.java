package assignment2;

public class World {
    private final Caterpillar caterpillar;
    private Position food;
    private Region map;
    private ActionQueue actions;
    private TargetQueue foodpositions;
    private GameState state;

    public World(TargetQueue foodpositions, ActionQueue actions){
        this.foodpositions = foodpositions;
        this.actions = actions;
        this.map = new Region(0, 0, 15, 15);
        this.caterpillar = new Caterpillar();
        if(!this.foodpositions.isEmpty()){
            this.food = this.foodpositions.dequeue();
        }
        this.state = GameState.MOVE;
    }

    public void step(){
        if(actions.isEmpty()){
            this.state = GameState.NO_MORE_ACTION;
            return;
        }
        if(this.state != GameState.EAT && this.state != GameState.MOVE){
            return;
        }

        Position currentHead = this.caterpillar.getHead();
        Direction currentDirection = this.actions.dequeue();
        Position nextHead = new Position(currentHead.getX(), currentHead.getY());

        if (currentDirection == Direction.NORTH) {
            nextHead.moveNorth();
        } else if (currentDirection == Direction.SOUTH) {
            nextHead.moveSouth();
        } else if (currentDirection == Direction.EAST) {
            nextHead.moveEast();
        } else if(currentDirection == Direction.WEST){
            nextHead.moveWest();
        }
        else{
            return;
        }
        if (!this.map.contains(nextHead)) {
            this.state = GameState.WALL_COLLISION;
            return;
        }
        if (this.caterpillar.selfCollision(nextHead)) {
            this.state = GameState.SELF_COLLISION;
            return;
        }
        if (nextHead.equals(this.food)) {
            this.caterpillar.addFirst(nextHead);
            this.food = this.foodpositions.dequeue();
            if (this.foodpositions.isEmpty()) {
                this.state = GameState.DONE;
            } else {
                this.state = GameState.EAT;
            }
        } else {
            this.caterpillar.addFirst(nextHead);
            this.caterpillar.removeLast();
            this.state = GameState.MOVE;
        }
    }

    public GameState getState(){
        return this.state;
    }
    public Caterpillar getCaterpillar(){
        return this.caterpillar;
    }
    public Position getFood(){
        return this.food;
    }
    public boolean isRunning(){
        if(this.state == GameState.MOVE || this.state == GameState.EAT){
            return true;
        }
        return false;
    }
}
