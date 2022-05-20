package model;

public class Coup {
    private int row;
    private int matchesNum;
    private int maxMoves;
    private int numberOfMatchesDeleted;

    public Coup(int row, int maxMoves) {
        this.row = row;
        this.maxMoves = maxMoves;
        this.matchesNum = 2 * this.row - 1;
        this.numberOfMatchesDeleted = 0;
        
    }

    public int getRow() {
        return row;
    }


    public int getMatchesNum() {
        return matchesNum;
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public void setMaxMoves(int maxMoves) {
        this.maxMoves = maxMoves;
    }

    public int getNumberOfMatchesDeleted() {
        return numberOfMatchesDeleted;
    }
    
     
    
    public void removeOneMatch(){
        if(this.matchesNum > 0){
            this.matchesNum--;
            this.numberOfMatchesDeleted++;
        }
        
    }
    
    
}
