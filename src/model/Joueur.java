package model;

public class Joueur {
    private String name;
    private int winningRounds; // The number of winning round

    public Joueur() {
    }

    public Joueur(String name, int winningRounds) {
        this.name = name;
        this.winningRounds = winningRounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinningRounds() {
        return winningRounds;
    }

    public void setWinningRounds(int winningRounds) {
        this.winningRounds = winningRounds;
    }
    
    
    
}
