package com.jck.exo.model;

import com.jck.exo.service.DataHandler;

public class User {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private int coins;
    private int gamesPlayed;
    private int gamesWon;
    private int coinsSpent;
    public int getGamesWon() {
        return gamesWon;
    }
    public int getCoinsSpent() {
        return coinsSpent;
    }

    public User(String name) {
        this.name = name;
        this.coins = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.coinsSpent = 0;
    }

    public void addCoins(int c){
        this.coins+=c;
        DataHandler.updateUser(this);
    }

    public void removeCoins(int c){
        // Warning : donnée liée avec coinsSpent
        this.coins-=c;
        this.coinsSpent+=c;
        DataHandler.updateUser(this);
    }

    public int getCoins() {
        return coins;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void incGamesPlayed(){
        this.gamesPlayed++;
        DataHandler.updateUser(this);
    }
    public void incGamesWon(){
        this.gamesWon++;
        DataHandler.updateUser(this);
    }
}
