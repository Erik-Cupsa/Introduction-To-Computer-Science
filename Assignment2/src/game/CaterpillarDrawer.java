package game;

import javax.swing.*;

import assignment2.Caterpillar;
import assignment2.Position;

import java.awt.*;

public class CaterpillarDrawer extends JLabel {

    private static final long serialVersionUID = 1L;

    Point p;

    private Caterpillar caterpillar ;
    private Position food ;

    private int width ;
    private int height ;
    public static int xoff = 130, yoff = 20;

    public void setCaterpillar(Caterpillar caterpillar) {
        this.caterpillar = caterpillar ;
    }

    public void setFood(Position food ) {
        this.food = food ;
    }

    public void setSize(int width, int height) {
        this.width = width ;
        this.height= height ;
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);

        // Draw Background
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, width, height);

        // Draw food
        g.setColor(new Color(206, 18, 18));
        p = positionToCoordinates( food );
        g.fillRect(p.x,p.y, 32,32);

        // Draw caterpillar
        g.setColor(new Color(18, 93, 152));
        for ( Position c : caterpillar ) {
            p = positionToCoordinates(c ) ;
            g.fillRect(p.x, p.y, 32, 32);
        }

        // Draw Grid
        g.setColor(Color.GRAY);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                g.drawRect(i * 32 + xoff, j * 32 + yoff, 32, 32);
            }
        }

        // Draw Border
        g.setColor(Color.GRAY);
        g.drawRect(xoff, yoff, 512, 512);

        repaint();
    }

    public Point positionToCoordinates(Position c) {
        Point p = new Point(0, 0);
        p.x = c.getX() * 32 + xoff ;
        p.y = c.getY() * 32 + yoff ;

        return p;
    }
}