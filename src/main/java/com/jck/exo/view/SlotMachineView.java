package com.jck.exo.view;

import java.util.Scanner;

import static java.lang.System.out;

public class SlotMachineView {
    private static final String RED = "\u001B[91m";
    private static final String GREEN = "\u001B[92m";
    private static final String YELLOW = "\u001B[93m";
    private static final String BLUE = "\u001B[94m";
    private static final String PURPLE = "\u001B[95m";
    private static final String CYAN = "\u001B[96m";
    private static final String WHITE = "\u001B[97m";
    private static final String DEFAULT = "\u001B[37m";

    public SlotMachineView() {
    }

    /**
     * SHOW MACHINE
     * @param matrix 3x3 String matrix
     */
    public void showMachine(String[][] matrix){
        printLine("@*******@*/***\\*@*******@", "GBBBBBBBGBPBBBPBGBBBBBBBG");
        printLine("~S L O T T Y R O C K E T~", "_Y_C_G_Y_C_G_Y_C_G_Y_C_G_");
        printLine("@======@/*=====*\\@======@", "GBBBBBBGPBBBBBBBPGBBBBBBG");
        for (int i=0; i<3; i++){
            printLine("#[ "+formatCell(matrix[0][i]) + " ]=[ "
                            + formatCell(matrix[1][i]) + " ]=[ "
                            + formatCell(matrix[2][i]) + " ]#",
                    "PY_WWW_YBY_WWW_YBY_WWW_YP");
        }
        printLine("@*******@*******@*******@", "GBBBBBBBGBBBBBBBGBBBBBBBG");
    }
    private void printLine(String text, String colors){
        int counter = 0;
        StringBuilder lineStr = new StringBuilder();
        for(char c : text.toCharArray()){
            char color = colors.toUpperCase().charAt(counter);
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
    private String formatCell(String cell){
        if (cell.length()==1){
            try{
                Integer.parseInt(cell);
                return "("+cell+")";
            } catch(Exception e){
                return "~"+cell+"~";
            }
        } else {
            return cell;
        }
    }
    public char getCharInput(String message){
        Scanner scanner = new Scanner(System.in);
        out.print(YELLOW + message);
        return scanner.next().charAt(0);
    }
}
