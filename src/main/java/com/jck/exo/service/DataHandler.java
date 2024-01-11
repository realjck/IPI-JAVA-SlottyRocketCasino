package com.jck.exo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.util.Objects;

/**
 * DataHandler
 * to handle access to config and saves
 */
public class DataHandler {
    private static DataHandler instance = null;
    private JsonObject data;
    private DataHandler(){}
    public static void getInstance(){
        if (instance == null) {
            instance = new DataHandler();
        }
    }

    /**
     * Load data
     * @param filename (in src/ressources/)
     */
    public static synchronized void load(String filename){
        instance.data = JsonParser.parseReader(
            new InputStreamReader(
                Objects.requireNonNull(
                        DataHandler.class.getClassLoader()
                                .getResourceAsStream(filename)
                )
            )
        ).getAsJsonObject();
    }

    /**
     * Get Config
     * @return String[][] configuration of machine slots
     */
    public static String[][] getConfig(){
        JsonArray configArray = instance.data.getAsJsonArray("config");
        int rows = configArray.size();
        int cols = configArray.get(0).getAsJsonArray().size();

        String[][] config = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            JsonArray rowArray = configArray.get(i).getAsJsonArray();
            for (int j = 0; j < cols; j++) {
                config[i][j] = rowArray.get(j).getAsString();
            }
        }

        return config;
    }

}
