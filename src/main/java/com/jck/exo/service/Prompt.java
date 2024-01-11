package com.jck.exo.service;

import java.util.Scanner;

import static java.lang.System.out;

public class Prompt {
    private static final String YELLOW = "\u001B[93m";

    /**
     * Renvoie la valeur de la touche pressée au clavier
     * (pour l'instant avec appui sur Return, à voir pour appui simple)
     * @param message message du prompt
     * @return String
     */
    public static String getKey(String message){
        Scanner scanner = new Scanner(System.in);
        out.print(YELLOW + message);
        return String.valueOf(scanner.next().charAt(0));
    }

    /**
     * Renvoie une réponse String entrée au clavier
     * @param message message du prompt
     * @return String
     */
    public static String getString(String message){
        Scanner scanner = new Scanner(System.in);
        out.print(YELLOW + message);
        return scanner.next();
    }
}
