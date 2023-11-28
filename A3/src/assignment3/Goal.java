package assignment3;

import java.awt.Color;

public abstract class Goal {
    protected Color targetGoal;

    public Goal(Color c) {
        this.targetGoal = c;
    }

    public abstract int score(Block board);

    public abstract String description();
}
