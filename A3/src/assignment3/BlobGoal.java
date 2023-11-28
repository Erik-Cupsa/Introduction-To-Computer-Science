package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal{

    public BlobGoal(Color c) {
        super(c);
    }

    @Override
    public int score(Block board) {
       int score=0;
       Color[][] flattened = board.flatten();
       boolean[][] visited = new boolean[flattened.length][flattened[0].length];

       for(int i=0; i< flattened.length;i++){
           for(int j=0; j<flattened[0].length; j++){
               if (!visited[i][j] && flattened[i][j].equals(super.targetGoal)) {
                   int blobSize = undiscoveredBlobSize(i, j, flattened, visited);
                   score = Math.max(score, blobSize);
               }
           }
       }
       return score;
    }

    @Override
    public String description() {
        return "Create the largest connected blob of " + GameColors.colorToString(targetGoal)
                + " blocks, anywhere within the block";
    }


    public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
        if (i < 0 || i >= unitCells.length) {
            return 0;
        }
        else if(j < 0 || j >= unitCells[0].length){
            return 0;
        }
        else if (unitCells[i][j] != super.targetGoal) {
            visited[i][j] = false;
            return 0;
        }
        else if (visited[i][j]) {
            return 0;
        }
        else {
            int size = 0;
            int[][] neighbours = new int[4][2];
            int row = 0;
            while(row<4){
                if(row==0){
                   neighbours[0][0] = i-1;
                   neighbours[0][1] = j;
                }
                else if (row==1){
                    neighbours[1][0] = i+1;
                    neighbours[1][1] = j;
                }
                else if(row==2){
                    neighbours[2][0] = i;
                    neighbours[2][1] = j-1;
                }
                else{
                    neighbours[3][0] = i;
                    neighbours[3][1] = j+1;
                }
                row++;
            }

            visited[i][j] = true;

            for (int k = 0; k < neighbours.length; k++) {
                int neighborRow = neighbours[k][0];
                int neighborCol = neighbours[k][1];
                size += undiscoveredBlobSize(neighborRow, neighborCol, unitCells, visited);
            }
            return size + 1;
        }
    }

}
