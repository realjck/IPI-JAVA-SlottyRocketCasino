package com.jck.exo;

import com.jck.exo.machine.SlotMachine;
import com.jck.exo.machine.SlotMachineException;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;

import java.util.Arrays;

import static java.lang.System.out;

public class Program {
    public static void main(String[] args) {

        // charge config
        DataHandler.getInstance();
        DataHandler.load("data.json");

        // invite utilisateur
        String userName = Prompt.getString("BONJOUR ET BIENVENUE AU CASINO, VEUILLEZ PRÉSENTER VOTRE IDENTITÉ > ");


        // lance machine
        try {
            new SlotMachine();
        } catch (SlotMachineException e){
            out.println(e.getMessage());
        }

    }
}
