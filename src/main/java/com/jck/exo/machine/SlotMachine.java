package com.jck.exo.machine;

import com.jck.exo.model.User;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;
import com.jck.exo.view.SlotMachineView;

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
        view.showMachine(DataHandler.getConfigSlots());
        view.showMachineFooter(user.getCoins(), user.getGamesPlayed());
        String input = "";
        while(!input.equalsIgnoreCase("q")){
            input = Prompt.getKey("Combien de jetons voulez-vous miser ?\n-3,2,1- Q:sauvegarder et quitter ? > ");
        }

    }
}
