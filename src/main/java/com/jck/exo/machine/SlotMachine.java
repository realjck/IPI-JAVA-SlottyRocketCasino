package com.jck.exo.machine;

import com.jck.exo.service.DataHandler;
import com.jck.exo.view.SlotMachineView;

public class SlotMachine {
    private ColumnsHandler columnsHandler;
    private SlotMachineView view;
    public SlotMachine() throws SlotMachineException {
        this.columnsHandler = new ColumnsHandler();

        DataHandler.getInstance();
        this.view = new SlotMachineView();

        launchGame();
    }

    private void launchGame(){

        view.showMachine(DataHandler.getConfigSlots());
        char input = view.getCharInput("Combien de jetons -3,2,1- Q:quitter ?");
    }
}
