package com.jck.exo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jck.exo.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Objects;

/**
 * DATA HANDLER
 * Gestion des données du jeu
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
     * @param filename String (in src/ressources/)
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
     * Save data
     * @param filename String (in src/ressources/)
     */
    public static synchronized void save(String filename) {

        File file = new File("src/main/resources/" + filename);
        Path path = file.toPath();

        try (FileWriter writer = new FileWriter(path.toFile())) {
            new Gson().toJson(instance.data, writer);
        } catch (IOException e) {
            System.out.println("**ERREUR A L'ÉCRITURE**");
        }
    }

    /**
     * Renvoie la configuration de la machine (3 colonnes)
     * @return String[][]
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
        User user = new Gson().fromJson(instance.data
                .getAsJsonObject("saves")
                .getAsJsonObject(userName), User.class);

        user.setName(userName);

        return user;
    }

    /**
     * Efface un joueur de l'objet data saves
     * @param user User
     */
    public static void removeUser(User user){
        instance.data.getAsJsonObject("saves").remove(user.getName());
    }

    /**
     * Mets à jour (ou crée) User
     * @param user User avec propriétés à jour
     */
    public static void updateUser(User user){
        JsonObject save = new JsonObject();
        save.addProperty("coins", user.getCoins());
        save.addProperty("gamesPlayed", user.getGamesPlayed());
        save.addProperty("gamesWon", user.getGamesWon());
        save.addProperty("coinsSpent", user.getCoinsSpent());
        instance.data.getAsJsonObject("saves")
                .add(user.getName(), save);
    }

    /**
     * Renvoie les gains associés depuis la config gains de data
     * @param symbol String symbole gagné
     * @return int gains
     */
    public static int getGainByString(String symbol){
        return instance.data
                .getAsJsonObject("configGains")
                .getAsJsonPrimitive(symbol)
                .getAsInt();
    }

    /**
     * Incrémente le winCounter correspondant de data
     * @param symbol String
     */
    public static void incWinCounter(String symbol){
        instance.data.getAsJsonObject("winCounter").addProperty(
                symbol,
                instance.data.getAsJsonObject("winCounter")
                        .getAsJsonPrimitive(symbol)
                        .getAsInt() +1
        );
    }

    /**
     * Récupère les statistiques des symboles sous forme d'un Json
     * @return JsonObject {"symbol": count}
     */
    public static JsonObject getWinCounter(){
        return instance.data.getAsJsonObject("winCounter");
    }
}
