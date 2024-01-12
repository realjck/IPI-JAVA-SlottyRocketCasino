package com.jck.exo.machine;

import com.jck.exo.service.DataHandler;
import java.util.Random;

public class ColumnsHandler {
    private Random random;
    private String[][] slots;

    public ColumnsHandler() throws SlotMachineException {
        try {
            this.slots = DataHandler.getConfigSlots();
        } catch (Exception e){
            throw new SlotMachineException("Erreur à l'initialisation des données");
        }
        this.random = new Random();
    }

    /**
     * This returns a 3x3 Random Matrix
     * @param seed Long
     * @return String[][]
     */
    public String[][] getRandomMatrix(long seed){
        random.setSeed(seed);
        String[][] matrix = new String[3][3];
        for (int col=0; col<3 ; col++){
            int index = random.nextInt(slots[col].length);
            for (int i=0; i<3; i++){
                matrix[col][i] = slots[col][(i + index) % slots[col].length];
            }
        }
        return matrix;
    }
}
