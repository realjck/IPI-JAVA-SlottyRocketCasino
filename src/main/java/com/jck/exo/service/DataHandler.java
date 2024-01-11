package com.jck.exo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jck.exo.model.User;

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
     * (code ChatGPT à voir si pas mieux en utilisant Gson)
     * @return String[][] configuration of machine slots
     */
    public static String[][] getConfigSlots(){
        JsonArray configArray = instance.data.getAsJsonArray("configSlots");
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

    /**
     * Renvoie un User par son nom
     * @param userName nom du joueur
     * @return User joueur recherché
     */
    public static User getUserByName(String userName) {

        // pour debug:
        System.out.println(instance.data.getAsJsonObject("saves")
                .getAsJsonObject(userName).toString());

        return new Gson().fromJson(instance.data
                .getAsJsonObject("saves")
                .getAsJsonObject(userName), User.class);

    }
}
