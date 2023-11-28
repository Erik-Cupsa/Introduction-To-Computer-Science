package assignment3;

import java.awt.Color;

public class PerimeterGoal extends Goal{
    public PerimeterGoal(Color c) {
        super(c);
    }

    @Override
    public int score(Block board) {
        int score = 0;
        Color[][] flattened = board.flatten();
        for(int i=0; i<flattened.length; i++){
            for(int j=0; j<flattened[0].length; j++){
                if(flattened[i][j] == super.targetGoal){
                    if(j==0){
                        if(i==0 || i==flattened.length-1){
                            score +=2;
                        }
                        else{
                            score++;
                        }
                    }
                    else if(j==flattened[0].length-1){
                        if(i==0 || i==flattened.length-1){
                            score +=2;
                        }
                        else{
                            score++;
                        }
                    }
                    else if(i==0){
                        if(j==0 || j==flattened[0].length-1){
                            score +=2;
                        }
                        else{
                            score++;
                        }
                    }
                    else if(i== flattened.length-1){
                        if(j==0 || j==flattened[0].length-1){
                            score +=2;
                        }
                        else{
                            score++;
                        }
                    }
                }
            }
        }
        return score;
    }

    @Override
    public String description() {
        return "Place the highest number of " + GameColors.colorToString(targetGoal)
                + " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
    }

}
