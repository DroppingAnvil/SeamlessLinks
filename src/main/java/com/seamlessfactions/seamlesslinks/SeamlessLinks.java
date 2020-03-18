package com.seamlessfactions.seamlesslinks;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class SeamlessLinks extends JavaPlugin {
    public static boolean canUsePAPI = false;
    public static HashMap<String, List<String>> msgMap = new HashMap<>();
    private static SeamlessLinks instance;
    public static SeamlessLinks getInstance() {
        return instance;
    }
    public SeamlessLinks() {
        if (instance == null) {instance = this;}
    }
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().log(Level.INFO, "Loading commands...");
        Long start = System.currentTimeMillis();
        try {
            for (String key : getConfig().getConfigurationSection("commands").getKeys(false)) {
                try {
                    msgMap.put(key, getConfig().getStringList("commands." + key));
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "An error occurred while parsing your configuration! Configuration parsing will continue. Error Key: " + key + " Error message: " + e.getMessage());
                    if (getConfig().getBoolean("debug")) e.printStackTrace();
                }
            }
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "An unknown error occurred while parsing your configuration!");
            e.printStackTrace();
        }
        getLogger().log(Level.INFO, "Loaded " + msgMap.size() + " commands in " + (System.currentTimeMillis() - start) + "MS");
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            canUsePAPI = true;
            getLogger().log(Level.INFO, "PlaceholderAPI found and setup");
        } else {
            getLogger().log(Level.INFO, "PlaceholderAPI could not be found! It is not required but will make the plugin more flexible, learn more here https://www.spigotmc.org/resources/placeholderapi.6245/");
        }
        getServer().getPluginManager().registerEvents(new CommandListener(), this);
    }
}
