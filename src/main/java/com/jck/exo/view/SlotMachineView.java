package com.jck.exo.view;

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
        // rien ici
    }

    /**
     * SHOW MACHINE
     * @param matrix 3x3 String matrix
     */
    public void showMachine(String[][] matrix, boolean[][] matrixLum){
        out.flush();
        out.println();
        printLine("@*******@*/***\\*@*******@", "GBBBBBBBGBPBBBPBGBBBBBBBG");
        printLine("~S L O T T Y R O C K E T~", "_Y_C_G_Y_C_G_Y_C_G_Y_C_G_");
        printLine("@======@/*=====*\\@======@", "GBBBBBBGPBBBBBBBPGBBBBBBG");
        for (int i=0; i<3; i++){
            printLine("#[ "+formatCell(matrix[0][i]) + " ]=[ "
                            + formatCell(matrix[1][i]) + " ]=[ "
                            + formatCell(matrix[2][i]) + " ]#",
                    "PY_" + (matrixLum[0][i] ? "WWW" : "___")
                            + "_YBY_" + (matrixLum[1][i] ? "WWW" : "___")
                            +"_YBY_" + (matrixLum[2][i] ? "WWW" : "___")
                            +"_YP");
        }
        printLine("@*******@\\*****/@*******@", "GBBBBBBBGPBBBBBPGBBBBBBBG");
    }
    private void printLine(String text, String colors){
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

    /**
     * SHOW MACHINE FOOTER
     * @param coins int nombre de jetons
     * @param nbplay int numéro de partie
     */
    public void showMachineFooter(int coins, int nbplay){
        StringBuilder infoLine = new StringBuilder("# C:");
        infoLine.append(coins);
        infoLine.append(" ".repeat(18
                - String.valueOf(coins).length()
                - String.valueOf(nbplay +1).length()));
        infoLine.append("#").append(nbplay +1).append(" #");
        printLine(infoLine.toString(), "P_Y_WWWWWWWWWWWWWWWWWWW_P");
        printLine("@***********************@", "GBBBBBBBBBBBBBBBBBBBBBBBG");
    }

    /**
     * Affiche les gains du dernier tirage
     * @param gains int gains à afficher
     */
    public void showWon(int gains){
        printLine("VOUS AVEZ GAGNE C:"+gains, "CGCGCGCGCGCGCGCGY_W");
    }
}
