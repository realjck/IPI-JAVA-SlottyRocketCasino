package com.jck.exo;

import com.jck.exo.machine.SlotMachine;
import com.jck.exo.machine.SlotMachineException;
import com.jck.exo.model.User;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;

import static java.lang.System.out;

public class Program {
    public static void main(String[] args) {

        // charge config
        DataHandler.getInstance();
        DataHandler.load("data.json");

        // invite utilisateur
        String userName = Prompt.getString("BONJOUR ET BIENVENUE AU CASINO, VEUILLEZ PRÉSENTER VOTRE IDENTITÉ > ");
        User player;
        try {
            player = DataHandler.getUserByName(userName);
        } catch (Exception e){
            player = new User(userName);
            out.println("Veuillez accepter ce cadeau de bienvenue de 100 jetons.");
            player.addCoins(100);
        }

        // lance machine
        try {
            new SlotMachine(player);
        } catch (SlotMachineException e){
            out.println(e.getMessage());
        }

    }
}
