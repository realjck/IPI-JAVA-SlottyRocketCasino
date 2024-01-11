package com.jck.exo;

import com.jck.exo.machine.SlotMachine;
import com.jck.exo.machine.SlotMachineException;
import com.jck.exo.service.DataHandler;

import java.util.Arrays;

import static java.lang.System.out;

public class Program {
    public static void main(String[] args) {

        // charge config
        DataHandler.getInstance();
        DataHandler.load("data.json");
        out.println(Arrays.deepToString(DataHandler.getConfig()));

        // lance machine
        try {
            new SlotMachine();
        } catch (SlotMachineException e){
            out.println(e.getMessage());
        }

    }
}
