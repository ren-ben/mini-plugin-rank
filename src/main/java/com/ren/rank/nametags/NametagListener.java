package com.ren.rank.nametags;

import com.ren.rank.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.util.Objects;

public class NametagListener implements Listener {

    Main main;

    //Constructor
    public NametagListener(Main main) {
        this.main = main;
    }

    //Set-up for the Player Ranks when joining
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        NametagManager.setNametags(e.getPlayer());
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(main.file);
        if(!modifyFile.contains(String.valueOf(e.getPlayer().getUniqueId()))) {
            modifyFile.set(String.valueOf(e.getPlayer().getUniqueId()), "NEWCOMER");
            try {
                modifyFile.save(main.file);
                NametagManager.newTag(e.getPlayer(), main);
                return;
            } catch (IOException ignore) {return;}
        }
        NametagManager.newTag(e.getPlayer(), main);
    }

    //Remove the Player Tag on Quit
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        NametagManager.removeTag(e.getPlayer());
    }

    //Player Prefix in Chat
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Rank rank = NametagManager.getRank(e.getPlayer(), main);
        e.setFormat(ChatColor.translateAlternateColorCodes('&', rank.display + "§r§l" + e.getPlayer().getName() + ": §r" + message));
    }
}