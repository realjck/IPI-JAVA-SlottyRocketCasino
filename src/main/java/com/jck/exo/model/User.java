package com.jck.exo.model;

public class User {
    private String name;
    private int coins;
    private int gamesPlayed;
    private int gamesWon;
    private int coinsSpent;

    public User(String name) {
        this.name = name;
        this.coins = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.coinsSpent = 0;
    }

    public void addCoins(int c){
        this.coins+=c;
    }

    public void removeCoins(int c){
        // Warning : donnée liée avec coinsSpent
        this.coins-=c;
        this.coinsSpent+=c;
    }

    public int getCoins() {
        return coins;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void incGamesPlayed(){
        this.gamesPlayed++;
    }
    public void incGamesWon(){
        this.gamesWon++;
    }
}
