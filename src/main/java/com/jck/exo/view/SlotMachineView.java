package com.jck.exo.view;

import static java.lang.System.out;

public class SlotMachineView {
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
        TerminalDisplay.colorPrint("@*******@*/***\\*@*******@", "GBBBBBBBGBPBBBPBGBBBBBBBG");
        TerminalDisplay.colorPrint("~S L O T T Y R O C K E T~", "_Y_C_G_Y_C_G_Y_C_G_Y_C_G_");
        TerminalDisplay.colorPrint("@======@/*=====*\\@======@", "GBBBBBBGPBBBBBBBPGBBBBBBG");
        for (int i=0; i<3; i++){
            TerminalDisplay.colorPrint("#[ "+formatCell(matrix[0][i]) + " ]=[ "
                            + formatCell(matrix[1][i]) + " ]=[ "
                            + formatCell(matrix[2][i]) + " ]#",
                    "PY_" + (matrixLum[0][i] ? "WWW" : "___")
                            + "_YBY_" + (matrixLum[1][i] ? "WWW" : "___")
                            +"_YBY_" + (matrixLum[2][i] ? "WWW" : "___")
                            +"_YP");
        }
        TerminalDisplay.colorPrint("@*******@\\*****/@*******@", "GBBBBBBBGPBBBBBPGBBBBBBBG");
    }

    /**
     * Formate une cellule ( ) pour un chiffre, ~ ~ pour une lettre
     * @param cell String
     * @return String
     */
    public String formatCell(String cell){
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
     * @param nbPlay int numéro de partie
     */
    public void showMachineFooter(int coins, int nbPlay){
        StringBuilder infoLine = new StringBuilder("# C:");
        infoLine.append(coins);
        infoLine.append(" ".repeat(18
                - String.valueOf(coins).length()
                - String.valueOf(nbPlay +1).length()));
        infoLine.append("#").append(nbPlay +1).append(" #");
        TerminalDisplay.colorPrint(infoLine.toString(), "P_Y_WWWWWWWWWWWWWWWWWWW_P");
        TerminalDisplay.colorPrint("@***********************@", "GBBBBBBBBBBBBBBBBBBBBBBBG");
    }

    /**
     * SHOW WON
     * Affiche les gains du dernier tirage
     * @param gains int gains à afficher
     */
    public void showWon(int gains){
        TerminalDisplay.colorPrint("VOUS AVEZ GAGNE C:"+gains, "CGCGCGCGCGCGCGCGY_W");
    }
}
