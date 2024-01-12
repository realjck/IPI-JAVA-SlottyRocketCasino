package com.jck.exo;

import com.jck.exo.machine.SlotMachine;
import com.jck.exo.machine.SlotMachineException;
import com.jck.exo.model.User;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;
import com.jck.exo.view.TerminalDisplay;

import static java.lang.System.out;

public class Program {
    public static void main(String[] args) {

        // charge config
        DataHandler.getInstance();
        DataHandler.load("data.json");

        // invite utilisateur
        User player;
        TerminalDisplay.colorPrint("¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸",
                "GPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPBGPB");
        TerminalDisplay.colorPrint("BONJOUR ET BIENVENUE AU CASINO, VEUILLEZ PRÉSENTER VOTRE IDENTITÉ.", "Y");
        TerminalDisplay.colorPrint("(Seul Testy peut accéder aux statistiques)", "WWWWWWCGCGCW");
        String userName = Prompt.getString(" > ").toLowerCase();

        if (!userName.equals("testy")) {

            try {
                player = DataHandler.getUserByName(userName);
            } catch (Exception e) {
                player = new User(userName);
                TerminalDisplay.colorPrint("Veuillez accepter ce cadeau de bienvenue de 100 jetons.", "Y");
                player.addCoins(100);
            }

            // lance machine
            try {
                new SlotMachine(player);
                DataHandler.save("data.json");
            } catch (SlotMachineException e) {
                out.println(e.getMessage());
            }
        } else {
            // mode statistiques

        }
    }
}
