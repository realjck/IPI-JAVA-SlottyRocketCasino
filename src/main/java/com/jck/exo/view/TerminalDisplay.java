package com.jck.exo.view;

import static java.lang.System.out;

/**
 * TERMINAL DISPLAY
 * Classe utilitaire pour l'affichage en CLI
 */
public class TerminalDisplay {
    private TerminalDisplay(){}
    private static final String RED = "\u001B[91m";
    private static final String GREEN = "\u001B[92m";
    private static final String YELLOW = "\u001B[93m";
    private static final String BLUE = "\u001B[94m";
    private static final String PURPLE = "\u001B[95m";
    private static final String CYAN = "\u001B[96m";
    private static final String WHITE = "\u001B[97m";
    private static final String DEFAULT = "\u001B[37m";

    /**
     * Affiche une ligne de texte en couleur
     * -Red, Green, Yellow, Blue, Purple, Cyan, White
     * -Default(grey) : `_`
     * @param text String texte à afficher
     * @param colors String couleur de chaque caractère (length =< text.length)
     */
    public static void colorPrint(String text, String colors){
        int counter = 0;
        StringBuilder lineStr = new StringBuilder();
        char color = 'W';
        for(char c : text.toCharArray()){
            try {
                color = colors.toUpperCase().charAt(counter);
            } catch (Exception ignored){}

            switch (color){
                case 'R' : lineStr.append(RED); break;
                case 'G' : lineStr.append(GREEN); break;
                case 'Y' : lineStr.append(YELLOW); break;
                case 'B' : lineStr.append(BLUE); break;
                case 'P' : lineStr.append(PURPLE); break;
                case 'C' : lineStr.append(CYAN); break;
                case 'W' : lineStr.append(WHITE); break;
                default : lineStr.append(DEFAULT); break;
            }
            lineStr.append(c);
            counter++;
        }
        out.println(lineStr);
    }

}
