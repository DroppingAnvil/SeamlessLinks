package com.seamlessfactions.seamlesslinks;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.isCancelled() && Config.ignore_cancelled) return;
        String command = e.getMessage().toLowerCase();
        command = command.replaceFirst("/", "");
        if (SeamlessLinks.msgMap.containsKey(command)) {
            e.setCancelled(true);
            for (String s : SeamlessLinks.msgMap.get(command)) {
                String temp = ChatColor.translateAlternateColorCodes('&', s);
                if (SeamlessLinks.canUsePAPI) temp = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(e.getPlayer(), temp);
                e.getPlayer().sendMessage(temp);
            }
        }
    }
}
