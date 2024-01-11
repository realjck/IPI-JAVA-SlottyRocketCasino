package com.jck.exo.machine;

import com.jck.exo.service.DataHandler;

import java.util.Date;
import java.util.Random;

public class ColumnsHandler {
    private Random random;
    private String[][] slots;

    public ColumnsHandler() throws SlotMachineException {
        this.slots = DataHandler.getConfigSlots();
        this.random = new Random();
    }

    /**
     * This returns a 3x3 Random Matrix ♥ of game
     * @param seed Long
     * @return String[][]
     */
    public String[][] getRandomMatrix(long seed){
        random.setSeed(seed);
        String[][] matrix = new String[3][3];
        for (int col=0; col<3 ; col++){
            int index = random.nextInt(slots[col].length);
            for (int i=0; i<3; i++){
                // modulo use
                matrix[col][i] = slots[col][(i + index) % slots[col].length];
            }
        }
        return matrix;
    }
}
