package com.jck.exo;

import com.google.gson.JsonObject;
import com.jck.exo.machine.SlotMachine;
import com.jck.exo.machine.SlotMachineException;
import com.jck.exo.model.User;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;
import com.jck.exo.view.SlotMachineView;
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
        TerminalDisplay.colorPrint("(Seul Testy peut accéder aux statistiques)", "______CGCGC_");
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
                SlotMachine slotMachine = new SlotMachine(player);
                slotMachine.launchGame();
                // plays... then quit the program
            } catch (SlotMachineException e) {
                out.println(e.getMessage());
            }
        } else {
            // mode statistique
            try {
                User playerTest = new User(userName);
                SlotMachine slotMachineStat = new SlotMachine(playerTest);
                int nbTest = 0;
                while (nbTest < 1){
                    try {
                        nbTest = Integer.parseInt(Prompt.getString("Combien voulez-vous lancer de tests aujourd'hui ? > "));
                    } catch (Exception ignored){}
                }
                TerminalDisplay.colorPrint("Tests en cours, veuillez patienter...", "P");
                for (int i=0; i<nbTest ; i++){
                    slotMachineStat.launchGame(true);
                }
                TerminalDisplay.colorPrint("Voici vos Résultats :", "G");
                TerminalDisplay.colorPrint("* COINS:"+playerTest.getCoins(), "P_YYYYY_W");
                TerminalDisplay.colorPrint("* GAMES WON:"+playerTest.getGamesWon(), "P_YYYYYYYYY_W");
                TerminalDisplay.colorPrint("* COINS SPENT:"+playerTest.getCoinsSpent(), "P_YYYYYYYYYYY_W");

                String[] symbols = {"7","BAR","R","P","T","C"};
                int total = 0;
                JsonObject winCounter = DataHandler.getWinCounter();
                for (String s : symbols){
                    total += winCounter.getAsJsonPrimitive(s).getAsInt();
                }
                if (total > 0){
                    TerminalDisplay.colorPrint("% des Tirages gagnants (sauvegardés) :", "B");
                    for (String s : symbols){
                        TerminalDisplay.colorPrint(
                                "* " + new SlotMachineView().formatCell(s)+":"
                                        + (Math.round(winCounter.getAsJsonPrimitive(s).getAsDouble() / total * 100) + "%"),
                                "P BBB_W");
                    }
                }

                TerminalDisplay.colorPrint("Au revoir Testy!", "__________CGCGCP");
                DataHandler.removeUser(playerTest);

            } catch (SlotMachineException e){
                out.println(e.getMessage());
            }
        }
        // save
        DataHandler.save("data.json");
        // quit
    }
}
