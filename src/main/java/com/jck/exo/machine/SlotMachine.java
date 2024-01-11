package com.jck.exo.machine;

import com.jck.exo.model.User;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;
import com.jck.exo.view.SlotMachineView;

import java.util.Date;
import java.util.Objects;

public class SlotMachine {
    private User user;
    private ColumnsHandler columnsHandler;
    private SlotMachineView view;
    public SlotMachine(User user) throws SlotMachineException {
        this.user = user;
        this.columnsHandler = new ColumnsHandler();

        DataHandler.getInstance();
        this.view = new SlotMachineView();

        launchGame();
    }

    private void launchGame(){

        String[][] matrix = {
                {"---","---","---"},
                {"---","---","---"},
                {"---","---","---"}
        };
        String input = "";

        // games
        int lastWon = 0;
        while (!input.equalsIgnoreCase("q")){

            view.showMachine(matrix);
            view.showMachineFooter(user.getCoins(), user.getGamesPlayed());
            if (lastWon > 0){
                view.showWon(lastWon);
            }

            int coinsSpent = 0;
            while(!input.equalsIgnoreCase("q") && (coinsSpent <1 || coinsSpent >33)){
                input = Prompt.getKey("Combien de jetons voulez-vous miser ?\n-3,2,1- Q:sauvegarder et quitter ? > ");
                try {
                    coinsSpent = Integer.parseInt(input);
                } catch (Exception ignored){}
            }
            user.removeCoins(coinsSpent);

            // random matrix
            if (coinsSpent > 0 ){
                // random seed by date :
                long seed = Long.parseLong(String.valueOf(new Date().hashCode()));
                matrix = columnsHandler.getRandomMatrix(seed);
                // GET MONEY
                lastWon = 0;
                // ligne milieu :
                lastWon+=checkLine(matrix[0][1], matrix[1][1], matrix[2][1]);
                // lignes haut/bas :
                if (coinsSpent>=2){
                    lastWon+=checkLine(matrix[0][0], matrix[1][0], matrix[2][0]);
                    lastWon+=checkLine(matrix[0][2], matrix[1][2], matrix[2][2]);
                }
                // diagonales :
                if (coinsSpent==3){
                    lastWon+=checkLine(matrix[0][0], matrix[1][1], matrix[2][2]);
                    lastWon+=checkLine(matrix[0][2], matrix[1][1], matrix[2][0]);
                }
                if (lastWon>0){
                    user.addCoins(lastWon);
                    user.incGamesWon();
                }
                user.incGamesPlayed();
            }
        }
    }

    /**
     * Vérifie une ligne (3 cases) et renvoie les gains
     * @param c1 case 1
     * @param c2 case 2
     * @param c3 case 3
     * @return int gains (jetons)
     */
    private int checkLine(String c1, String c2, String c3){
        if (Objects.equals(c1, c2) && Objects.equals(c2, c3)){
            return DataHandler.getGainByString(c1);
        } else {
            return 0;
        }
    }
}
