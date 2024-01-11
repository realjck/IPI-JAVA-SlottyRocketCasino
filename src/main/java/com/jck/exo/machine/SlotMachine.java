package com.jck.exo.machine;

public class SlotMachine {
    private ColumnsHandler columnsHandler;
    public SlotMachine() throws SlotMachineException {
        this.columnsHandler = new ColumnsHandler();
    }
}
