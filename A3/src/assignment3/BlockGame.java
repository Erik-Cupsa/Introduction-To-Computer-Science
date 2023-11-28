package assignment3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class BlockGame extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener{
    private JFrame frame;

    ArrayList<BlockToDraw> blocks;
    Block board;

    // interactivity
    Block selected = null;
    int xClickPos;
    int yClickPos;

    // labels
    JLabel turnLabel;
    JLabel turnCountLabel;

    JLabel p1ScoreLabel;
    JLabel p2ScoreLabel;

    // game state
    int playerCount;
    int p1Score = 0;
    int p2Score = 0;
    int turn = 0; // 0 -> player1, 1 -> player 2
    Goal p1goal;
    Goal p2goal;
    int maxTurns;
    int plyCount;

    private static final int dim = 800; // maximum board pixel width
    private static Random gen = new Random(2);

    public static void main(String[] args) {
        new BlockGame();
    }

    public BlockGame() {
        // setup jpanel
        this.setBackground(Color.black);

        // setup frame
        frame = new JFrame ("A Game of Blocks");
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);

        // add listeners
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);

        // setup UI
        JPanel side = new JPanel();
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));

        Font font = new Font("Ariel", Font.PLAIN, 20);

        JButton smashButton = new JButton("Smash \uD83D\uDD28");
        JButton rotateCWButton = new JButton("Rotate Clockwise \u21BB");
        JButton rotateCCWButton = new JButton("Rotate CounterClockwise \u21BA");
        JButton reflectHButton = new JButton("Reflect Horizontal \u2968");
        JButton reflectVButton = new JButton("Reflect Vertical \u296E");

        smashButton.setFont(font);
        rotateCWButton.setFont(font);
        rotateCCWButton.setFont(font);
        reflectHButton.setFont(font);
        reflectVButton.setFont(font);

        smashButton.setFocusable(false);
        rotateCWButton.setFocusable(false);
        rotateCCWButton.setFocusable(false);
        reflectHButton.setFocusable(false);
        reflectVButton.setFocusable(false);

        smashButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rotateCWButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rotateCCWButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reflectHButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reflectVButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        side.add(smashButton);
        side.add(rotateCWButton);
        side.add(rotateCCWButton);
        side.add(reflectHButton);
        side.add(reflectVButton);

        smashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    selected.smash();
                    makeMove();
                }
            }
        });

        rotateCWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    selected.rotate(1);
                    makeMove();
                }
            }
        });

        rotateCCWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    selected.rotate(0);
                    makeMove();
                }
            }
        });

        reflectHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    selected.reflect(0);
                    makeMove();
                }
            }
        });

        reflectVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    selected.reflect(1);
                    makeMove();
                }
            }
        });

        // pushes rest of UI elements to bottom of screen
        side.add(Box.createVerticalGlue());

        // text lables
        turnLabel = new JLabel("It is Player 1's turn");
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnLabel.setFont(font);

        turnCountLabel = new JLabel("Turns left: ?");
        turnCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnCountLabel.setFont(font);

        p1ScoreLabel = new JLabel("Player 1's score: 0");
        p1ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1ScoreLabel.setFont(font);

        p2ScoreLabel = new JLabel("Player 2's score: 0");
        p2ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p2ScoreLabel.setFont(font);

        side.add(turnLabel);
        side.add(turnCountLabel);
        side.add(p1ScoreLabel);
        side.add(p2ScoreLabel);


        frame.add(side, BorderLayout.EAST);

        int maxDepth = Integer.parseInt(JOptionPane.showInputDialog("Choose the maximum number of subdivisions allowed", 6));


        // init and run game
        int size = initGame(maxDepth);
        this.setPreferredSize(new Dimension(size, size));

        // finish setting up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(false);
        frame.pack();
        frame.setVisible(true);

        // ask user for game config
        String[] options = new String[]{"One", "Two"};
        String[] goalSelect = new String[]{"Blob", "Perimeter"};

        playerCount = 1 + JOptionPane.showOptionDialog(null, "How many players?", "Playercount", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        double p1GoalType = gen.nextDouble();
        int p1TargetColor = gen.nextInt(GameColors.BLOCK_COLORS.length);

        if (p1GoalType < 0.5) {
            this.p1goal = new BlobGoal(GameColors.BLOCK_COLORS[p1TargetColor]);
        } else {
            this.p1goal = new PerimeterGoal(GameColors.BLOCK_COLORS[p1TargetColor]);
        }

        if (playerCount == 1) {
            JOptionPane.showOptionDialog(null, this.p1goal.description() , "Player 1 - Goal", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK"}, "OK");
            p1ScoreLabel.setForeground(GameColors.BLOCK_COLORS[p1TargetColor]);
            p2ScoreLabel.setVisible(false);
        } else {
            double p2GoalType = gen.nextDouble();
            int p2TargetColor = gen.nextInt(GameColors.BLOCK_COLORS.length);

            while (p1TargetColor == p2TargetColor)
                p2TargetColor = gen.nextInt(GameColors.BLOCK_COLORS.length);

            if (p2GoalType < 0.5) {
                this.p2goal = new BlobGoal(GameColors.BLOCK_COLORS[p2TargetColor]);
            } else {
                this.p2goal = new PerimeterGoal(GameColors.BLOCK_COLORS[p2TargetColor]);
            }

            JOptionPane.showOptionDialog(null, this.p1goal.description() , "Player 1 - Goal", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK"}, "OK");
            JOptionPane.showOptionDialog(null, this.p2goal.description() , "Player 2 - Goal", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK"}, "OK");
            p1ScoreLabel.setForeground(GameColors.BLOCK_COLORS[p1TargetColor]);
            p2ScoreLabel.setForeground(GameColors.BLOCK_COLORS[p2TargetColor]);
        }

        maxTurns = Integer.parseInt(JOptionPane.showInputDialog("How many turns do you want to play? (per player)", 5));
        turnCountLabel.setText("Turns left: " + maxTurns);

        // init scores
        updateScores();

        // run game
        this.run();
    }

    private void updateScores() {
        this.p1Score = p1goal.score(board);
        if (this.playerCount > 1) {
            this.p2Score = p2goal.score(board);
        }
        p1ScoreLabel.setText("Player 1's " + (p1goal instanceof BlobGoal ? "area" : "perimeter ") + " score: " + this.p1Score);
        p2ScoreLabel.setText("Player 2's " + (p2goal instanceof BlobGoal ? "area" : "perimeter ") + " score: " + this.p2Score);
    }

    private void makeMove() {
        if (playerCount > 1) {turn = (++turn)%2;}

        plyCount++;
        int turnsLeft = (maxTurns - plyCount/playerCount);
        turnCountLabel.setText("Turns left: " + turnsLeft);

        turnLabel.setText("It is Player " + (turn+1) + "'s turn");
        blocks = board.getBlocksToDraw();
        updateScores();

        if (turnsLeft == 0) { // == 0 is better than <= 0 because it lets them play on after the game "ends" if they want
            if (playerCount == 1) {
                JOptionPane.showMessageDialog(null, "Your final score is " + p1Score);
            } else {
                if (p1Score == p2Score) {
                    JOptionPane.showMessageDialog(null, "It's a tie!");
                } else {
                    JOptionPane.showMessageDialog(null, "Player " + (p1Score > p2Score ? "1" : "2") + " wins!");
                }
            }

        }
    }

    private int initGame(int maxDepth) {
        this.board = new Block(0, maxDepth);
        int minSize = this.dim / (int)Math.pow(2, maxDepth);
        int size = (int)Math.pow(2, maxDepth) * minSize;
        this.board.updateSizeAndPosition(size, 0, 0);
        this.blocks = this.board.getBlocksToDraw();
        return size;
    }

    // game loop
    public void run() {
        while (true) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (BlockToDraw bd : this.blocks) {
            g2d.setColor(bd.getColor());
            int strokeThickness = bd.getStrokeThickness();
            if (strokeThickness == 0) {
                g2d.fill(bd.getShape());
            } else {
                g2d.setStroke(new BasicStroke(strokeThickness));
                g2d.draw(bd.getShape());
            }
        }

        if (selected != null) {
            g2d.setColor(GameColors.HIGHLIGHT_COLOR);
            g2d.draw(selected.getHighlightedFrame().getShape());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        xClickPos = e.getX();
        yClickPos = e.getY();
        selected = board.getSelectedBlock(e.getX(), e.getY(), board.getMaxDepth());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (selected == null) {return;}

        int keyCode = e.getKeyCode();
        int curLevel = selected.getLevel();

        if (keyCode == KeyEvent.VK_UP) {
            selected = board.getSelectedBlock(xClickPos, yClickPos, Math.max(curLevel-1, 0));
        } else if (keyCode == KeyEvent.VK_DOWN) {
            selected = board.getSelectedBlock(xClickPos, yClickPos, Math.min(curLevel+1, board.getMaxDepth()));
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}