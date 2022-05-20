package model;

import java.util.ArrayList;

public class Tas {

    private int numberOfRows;
    private int maxMoves;
    private ArrayList<Coup> coups;

    public Tas(int numberOfRows, int maxMoves) {
        this.numberOfRows = numberOfRows;
        this.maxMoves = maxMoves;
        this.coups = new ArrayList<>();
        this.generateMatches();
    }

    private void generateMatches() {
        for (int i = 1; i <= numberOfRows; i++) {
            coups.add(new Coup(i, maxMoves == 0 ? 2 * i - 1 : maxMoves <= 2 * i - 1 ? maxMoves : 2 * i - 1));
        }
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }


    public int getMaxMoves() {
        return maxMoves;
    }


    public ArrayList<Coup> getCoups() {
        return coups;
    }
    
    public Coup getCoupInRow(int i){
        if(i >= 0 && i<= coups.size()){
            return coups.get(i);
        }
        return null;
    }
    
    
    public void printNumberOfMatchesDeletedInEachRow(){
        for (int i = 1; i <= numberOfRows; i++) {
            System.out.println("Row " + i + ": deleted " + coups.get(i).getNumberOfMatchesDeleted() );
        }
    }
    
    
    

}
