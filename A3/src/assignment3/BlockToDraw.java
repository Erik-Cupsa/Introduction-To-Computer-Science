package assignment3;

import java.awt.Color;
import java.awt.Rectangle;

public class BlockToDraw {
    private Rectangle shape;
    private Color color;
    private int strokeThickness;

    public BlockToDraw(Color c, int xCoord, int yCoord, int size, int stroke) {
        this.shape = new Rectangle(xCoord, yCoord, size, size);
        this.color = c;
        this.strokeThickness = stroke;
    }

    public Color getColor() {
        return this.color;
    }

    public Rectangle getShape() {
        return this.shape;
    }

    public int getStrokeThickness() {
        return this.strokeThickness;
    }

}
