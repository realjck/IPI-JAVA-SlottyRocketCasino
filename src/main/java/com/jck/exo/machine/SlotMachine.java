package com.jck.exo.machine;

import com.jck.exo.model.User;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;
import com.jck.exo.view.SlotMachineView;

import java.util.Date;

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

        String[][] matrix = DataHandler.getConfigSlots();
        String input = "";

        // games
        while (!input.equalsIgnoreCase("q")){

            view.showMachine(matrix);
            view.showMachineFooter(user.getCoins(), user.getGamesPlayed());


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

                // trying to get a good random seed :
                long seed = Long.parseLong(String.valueOf(new Date().hashCode()));
                matrix = columnsHandler.getRandomMatrix(seed);
            }
        }

    }
}
