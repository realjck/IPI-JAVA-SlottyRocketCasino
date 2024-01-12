package com.jck.exo.machine;

import com.jck.exo.model.User;
import com.jck.exo.service.DataHandler;
import com.jck.exo.service.Prompt;
import com.jck.exo.view.SlotMachineView;

import java.util.Date;
import java.util.Objects;

public class SlotMachine {
    private User user;
    private ColumnsHandler columnsHandler;
    private SlotMachineView view;
    private String[][] matrix;
    private boolean[][] matrixLum;
    public SlotMachine(User user) throws SlotMachineException {
        this.user = user;
        this.columnsHandler = new ColumnsHandler();

        DataHandler.getInstance();
        this.view = new SlotMachineView();

        launchGame();
    }

    private void launchGame(){

        matrix = new String[][]{
                {"---", "---", "---"},
                {"---", "---", "---"},
                {"---", "---", "---"}
        };
        matrixLum = new boolean[3][3];
        String input = "";

        // games
        int lastWon = 0;
        while (!input.equalsIgnoreCase("q")){

            view.showMachine(matrix, matrixLum);
            view.showMachineFooter(user.getCoins(), user.getGamesPlayed());
            if (lastWon > 0){
                view.showWon(lastWon);
            }

            int coinsSpent = 0;
            while(!input.equalsIgnoreCase("q") && (coinsSpent <1 || coinsSpent >3)){
                input = Prompt.getKey("Combien de jetons voulez-vous miser ?\n-3,2,1- Q:sauvegarder et quitter ? > ");
                try {
                    coinsSpent = Integer.parseInt(input);
                } catch (Exception ignored){}
            }
            user.removeCoins(coinsSpent);

            // random matrix
            if (coinsSpent > 0 ){
                // random seed by date :
                long seed = Long.parseLong(String.valueOf(new Date().hashCode()));
                matrix = columnsHandler.getRandomMatrix(seed);
                matrixLum = new boolean[3][3];
                // GET MONEY
                lastWon = 0;
                int won;
                // ligne milieu :
                lastWon+=checkLine(new int[]{0, 1}, new int[]{1, 1}, new int[]{2, 1});
                // lignes haut/bas :
                if (coinsSpent>=2){
                    lastWon+=checkLine(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0});
                    lastWon+=checkLine(new int[]{0, 2}, new int[]{1, 2}, new int[]{2, 2});
                }
                // diagonales :
                if (coinsSpent==3){
                    lastWon+=checkLine(new int[]{0, 0}, new int[]{1, 1}, new int[]{2, 2});
                    lastWon+=checkLine(new int[]{0, 2}, new int[]{1, 1}, new int[]{2, 0});
                }
                if (lastWon>0){
                    user.addCoins(lastWon);
                    user.incGamesWon();
                }
                user.incGamesPlayed();
            }
        }
    }

    /**
     * Vérifie une ligne (3 cases)
     * allume les lumières dans matrixLum
     * et renvoie les gains
     * @param c1 int[] case 1 x,y
     * @param c2 int[] case 2 x,y
     * @param c3 int[] case 3 x,y
     * @return int gains (jetons)
     */
    private int checkLine(int[] c1, int[] c2, int[] c3){
        if (Objects.equals(matrix[c1[0]][c1[1]], matrix[c2[0]][c2[1]])
                && Objects.equals(matrix[c2[0]][c2[1]], matrix[c3[0]][c3[1]])){

            matrixLum[c1[0]][c1[1]] = matrixLum[c2[0]][c2[1]] = matrixLum[c3[0]][c3[1]] = true;
            return DataHandler.getGainByString(matrix[c1[0]][c1[1]]);

        } else {
            return 0;
        }
    }
}
