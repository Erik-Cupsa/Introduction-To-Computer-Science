package assignment3;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

import assignment3.GameColors;

public class Block {
    private int xCoord;
    private int yCoord;
    private int size; // height/width of the square
    private int level; // the root (outer most block) is at level 0
    private int maxDepth;
    private Color color;

    private Block[] children; // {UR, UL, LL, LR}

    public static Random gen = new Random();

    public static void main(String[] args) {
//        Block[] level1 = new Block[4];
//        Block a = new Block(12,12,4,2,2, GameColors.BLUE,new Block[0]);
//        level1[3] = a;
//        Block b = new Block(8,12,4,2,2, GameColors.YELLOW,new Block[0]);
//        level1[2] = b;
//        Block c = new Block(8,8,4,2,2, GameColors.RED,new Block[0]);
//        level1[1] = c;
//        Block d = new Block(12,8,4,2,2, GameColors.BLUE,new Block[0]);
//        level1[0] = d;
//        Block e = new Block(8,8,8,1,2, GameColors.GREEN,level1);
//        Block[] level0 = new Block[4];
//        level0[3] = e;
//        Block f = new Block(0,8,8,1,2, GameColors.YELLOW,new Block[0]);
//        level0[2] = f;
//        Block g = new Block(0,0,8,1,2, GameColors.RED,new Block[0]);
//        level0[1] = g;
//        Block h = new Block(8,0,8,1,2, GameColors.GREEN,new Block[0]);
//        level0[0] = h;
//        Block last = new Block(0,0,16, 0, 2,null, level0);
//        last.printBlock();
//        Block blockDepth2 = new Block(0,2);
//        blockDepth2.updateSizeAndPosition(16,0,0);
//        blockDepth2.printBlock();
//        Block blockDepth3 = new Block(0,3);
//        blockDepth3.updateSizeAndPosition(16,0,0);
//        Block b1 = blockDepth3.getSelectedBlock(3, 5, 2);
//        b1.printBlock();
        Block b = new Block(0,2);
        b.updateSizeAndPosition(16,0,0);
        b.printColoredBlock();
    }

    /*
     * These two constructors are here for testing purposes.
     */
    public Block() {}

    public Block(int x, int y, int size, int lvl, int  maxD, Color c, Block[] subBlocks) {
        this.xCoord=x;
        this.yCoord=y;
        this.size=size;
        this.level=lvl;
        this.maxDepth = maxD;
        this.color=c;
        this.children = subBlocks;
    }



    /*
     * Creates a random block given its level and a max depth.
     *
     * xCoord, yCoord, size, and highlighted should not be initialized
     * (i.e. they will all be initialized by default)
     */
    public Block(int lvl, int maxDepth) {
       this.level = lvl;
       this.maxDepth = maxDepth;
       this.xCoord=0;
       this.yCoord=0;
       this.size=0;

       if(lvl < maxDepth) {
           if(gen.nextDouble() < Math.exp(-0.25 * lvl)){
               this.children = new Block[4];
               for (int i = 0; i < this.children.length; i++) {
                   this.children[i] = new Block(lvl + 1, maxDepth);
               }
           }
           else{
               this.children = new Block[0];
               this.color = GameColors.BLOCK_COLORS[gen.nextInt(GameColors.BLOCK_COLORS.length)];
           }
       }
       else if(lvl == maxDepth){
           this.children = new Block[0];
           this.color = GameColors.BLOCK_COLORS[gen.nextInt(GameColors.BLOCK_COLORS.length)];
       }
       else{
           throw new IllegalArgumentException("Level can not be larger than the max depth");
       }
    }


    /*
     * Updates size and position for the block and all of its sub-blocks, while
     * ensuring consistency between the attributes and the relationship of the
     * blocks.
     *
     *  The size is the height and width of the block. (xCoord, yCoord) are the
     *  coordinates of the top left corner of the block.
     */
    public void updateSizeAndPosition (int size, int xCoord, int yCoord) {
        if(size <= 0 || !validSize(size)){
            throw new IllegalArgumentException(("Invalid size"));
        }
        this.size = size;
        this.xCoord = xCoord;
        this.yCoord = yCoord;

        if(this.children.length != 0){
            this.children[0].updateSizeAndPosition(size/2, xCoord + size/2, yCoord);
            this.children[1].updateSizeAndPosition(size/2, xCoord, yCoord);
            this.children[2].updateSizeAndPosition(size/2, xCoord, yCoord + size/2);
            this.children[3].updateSizeAndPosition(size/2, xCoord + size/2, yCoord + size/2) ;
        }
    }

    private boolean validSize(int size){
        for(int i = this.level; i < this.maxDepth; i++){
            if(size % 2 != 0){
                return false;
            }
        }
        return true;
    }


    /*
     * Returns a List of blocks to be drawn to get a graphical representation of this block.
     *
     * This includes, for each undivided Block:
     * - one BlockToDraw in the color of the block
     * - another one in the FRAME_COLOR and stroke thickness 3
     *
     * Note that a stroke thickness equal to 0 indicates that the block should be filled with its color.
     *
     * The order in which the blocks to draw appear in the list does NOT matter.
     */
    public ArrayList<BlockToDraw> getBlocksToDraw() {
        ArrayList<BlockToDraw> blocks = new ArrayList<BlockToDraw>();
        if(this.children.length == 0){
            BlockToDraw block = new BlockToDraw(this.color, this.xCoord, this.yCoord, this.size, 0);
            BlockToDraw frame = new BlockToDraw(GameColors.FRAME_COLOR, this.xCoord, this.yCoord, this.size, 3);
            blocks.add(block);
            blocks.add(frame);
        }
        else{
            for(int i=0; i < this.children.length; i++){
                ArrayList<BlockToDraw> dividedBlocks = this.children[i].getBlocksToDraw();
                blocks.addAll(dividedBlocks);

            }
        }
        return blocks;
    }

    /*
     * This method is provided and you should NOT modify it.
     */
    public BlockToDraw getHighlightedFrame() {
        return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
    }



    /*
     * Return the Block within this Block that includes the given location
     * and is at the given level. If the level specified is lower than
     * the lowest block at the specified location, then return the block
     * at the location with the closest level value.
     *
     * The location is specified by its (x, y) coordinates. The lvl indicates
     * the level of the desired Block. Note that if a Block includes the location
     * (x, y), and that Block is subdivided, then one of its sub-Blocks will
     * contain the location (x, y) too. This is why we need lvl to identify
     * which Block should be returned.
     *
     * Input validation:
     * - this.level <= lvl <= maxDepth (if not throw exception)
     * - if (x,y) is not within this Block, return null.
     */
    public Block getSelectedBlock(int x, int y, int lvl) {
        if (lvl < this.level || lvl > this.maxDepth){
            throw new IllegalArgumentException("Invalid level");
        }
        else if(x < this.xCoord || x > this.xCoord + this.size || y < this.yCoord || y > this.yCoord + this.size){
            return null;
        }
        else if(this.children.length == 0 || this.level == lvl){
            return this;
        }
        else{
            for(int i = 0; i < this.children.length; i++){
                Block selected = this.children[i].getSelectedBlock(x, y, lvl);
                if(selected != null){
                    return selected;
                }
            }
        }
        return null;
    }




    /*
     * Swaps the child Blocks of this Block.
     * If input is 1, swap vertically. If 0, swap horizontally.
     * If this Block has no children, do nothing. The swap
     * should be propagate, effectively implementing a reflection
     * over the x-axis or over the y-axis.
     *
     */
    public void reflect(int direction) {
        if(this.children == null || this.children.length == 0){
            if(direction != 0 && direction !=1){
                throw new IllegalArgumentException("Invalid direction");
            }
            //else do nothing
        }
        else if(direction == 0){
            Block temp = this.children[0];
            this.children[0] = this.children[3];
            this.children[3] = temp;
            temp = this.children[1];
            this.children[1] = this.children[2];
            this.children[2] = temp;
            this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
        }
        else if(direction == 1){
            Block temp = this.children[0];
            this.children[0] = this.children[1];
            this.children[1] = temp;
            temp = this.children[2];
            this.children[2] = this.children[3];
            this.children[3] = temp;
            this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
        }
        else{
            throw new IllegalArgumentException("Invalid direction");
        }
        for(Block child: children) {
            child.reflect(direction);
        }
    }



    /*
     * Rotate this Block and all its descendants.
     * If the input is 1, rotate clockwise. If 0, rotate
     * counterclockwise. If this Block has no children, do nothing.
     */
    public void rotate(int direction) {
        if(this.children == null || this.children.length == 0){
            if(direction != 0 && direction !=1){
                throw new IllegalArgumentException("Invalid direction");
            }
            //else do nothing
        }
        else if(direction == 0){
            Block temp = this.children[0];
            this.children[0] = this.children[3];
            this.children[3] = this.children[2];
            this.children[2] = this.children[1];
            this.children[1] = temp;
            this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
        }
        else if(direction == 1){
            Block temp = this.children[0];
            this.children[0] = this.children[1];
            this.children[1] = this.children[2];
            this.children[2] = this.children[3];
            this.children[3] = temp;
            this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
        }
        else{
            throw new IllegalArgumentException("Invalid direction");
        }
        for(Block child: children){
            child.rotate(direction);
        }
    }



    /*
     * Smash this Block.
     *
     * If this Block can be smashed,
     * randomly generate four new children Blocks for it.
     * (If it already had children Blocks, discard them.)
     * Ensure that the invariants of the Blocks remain satisfied.
     *
     * A Block can be smashed iff it is not the top-level Block
     * and it is not already at the level of the maximum depth.
     *
     * Return True if this Block was smashed and False otherwise.
     *
     */
    public boolean smash() {
        boolean smashed;
        if(this.level == 0 || this.level == this.maxDepth){
            smashed =  false;
        }
        else{
            this.children = new Block[4];
            for(int i=0; i<4; i++){
                this.children[i] = new Block(this.level+1, this.maxDepth);
            }
            this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
            smashed = true;
        }
        return smashed;
    }


    /*
     * Return a two-dimensional array representing this Block as rows and columns of unit cells.
     *
     * Return and array arr where, arr[i] represents the unit cells in row i,
     * arr[i][j] is the color of unit cell in row i and column j.
     *
     * arr[0][0] is the color of the unit cell in the upper left corner of this Block.
     */
    public Color[][] flatten() {
        int length = (int) Math.pow(2, this.maxDepth);
        int size = (int) Math.pow(2, this.level);
        int unit = length / size;
        Color[][] result = new Color[unit][unit];
        if (this.children.length == 0){
            for(int i=0; i<unit; i++){
                for(int j=0; j<unit; j++){
                    result[i][j] = this.color;
                }
            }
        }
        else{
            unit/=2;
            int numChild = 0;

            while(numChild<4){
                Color[][] childFlattened = children[numChild].flatten();

                for(int i=0; i<unit; i++){
                    for(int j=0; j<unit; j++){
                        if(numChild==0){
                            result[i][j+unit] = childFlattened[i][j];
                        }

                        else if(numChild==1){
                            result[i][j] = childFlattened[i][j];
                        }

                        else if(numChild==2){
                            result[i+unit][j] = childFlattened[i][j];
                        }

                        else{
                            result[i+unit][j+unit] = childFlattened[i][j];
                        }
                    }
                }
               numChild++;
            }
        }
        return result;
    }



    // These two get methods have been provided. Do NOT modify them.
    public int getMaxDepth() {
        return this.maxDepth;
    }

    public int getLevel() {
        return this.level;
    }


    /*
     * The next 5 methods are needed to get a text representation of a block.
     * You can use them for debugging. You can modify these methods if you wish.
     */
    public String toString() {
        return String.format("pos=(%d,%d), size=%d, level=%d"
                , this.xCoord, this.yCoord, this.size, this.level);
    }

    public void printBlock() {
        this.printBlockIndented(0);
    }

    private void printBlockIndented(int indentation) {
        String indent = "";
        for (int i=0; i<indentation; i++) {
            indent += "\t";
        }

        if (this.children.length == 0) {
            // it's a leaf. Print the color!
            String colorInfo = GameColors.colorToString(this.color) + ", ";
            System.out.println(indent + colorInfo + this);
        } else {
            System.out.println(indent + this);
            for (Block b : this.children)
                b.printBlockIndented(indentation + 1);
        }
    }

    private static void coloredPrint(String message, Color color) {
        System.out.print(GameColors.colorToANSIColor(color));
        System.out.print(message);
        System.out.print(GameColors.colorToANSIColor(Color.WHITE));
    }

    public void printColoredBlock(){
        Color[][] colorArray = this.flatten();
        for (Color[] colors : colorArray) {
            for (Color value : colors) {
                String colorName = GameColors.colorToString(value).toUpperCase();
                if(colorName.length() == 0){
                    colorName = "\u2588";
                }else{
                    colorName = colorName.substring(0, 1);
                }
                coloredPrint(colorName, value);
            }
            System.out.println();
        }
    }

}
