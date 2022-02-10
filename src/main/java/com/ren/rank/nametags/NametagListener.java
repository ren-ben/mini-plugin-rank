package com.ren.rank.nametags;

import com.ren.rank.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class NametagListener implements Listener {

    Main main;

    public NametagListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        NametagManager.setNametags(e.getPlayer());
        NametagManager.newTag(e.getPlayer(), main);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        NametagManager.removeTag(e.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Rank rank;
        YamlConfiguration readFile = YamlConfiguration.loadConfiguration(main.file);
        switch((String) Objects.requireNonNull(readFile.get(String.valueOf(e.getPlayer().getUniqueId())))) {
            case "OWNER":
                rank = Rank.OWNER;
                break;
            case "ADMIN":
                rank = Rank.ADMIN;
                break;
            case "HELPER":
                rank = Rank.HELPER;
                break;
            case "BUILDER":
                rank = Rank.BUILDER;
                break;
            default:
                rank = Rank.NEWCOMER;
                break;
        }
        e.setFormat(ChatColor.translateAlternateColorCodes('&', rank.display + "§r" + e.getPlayer().getName() + ": " + message));
    }
}