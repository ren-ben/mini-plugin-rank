package com.ren.rank;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PermissonsManager implements Listener {

    public static final String[] ADMIN_BLOCKS = {"/summon", "/kill", "/ban", "/ban-ip", "/role"};

    public static final String[] HELPER_BLOCKS = {"/summon", "/kill", "/ban", "/gamemode", "/kick", "/gamerule", "/bossbar", "/changesettings", "/clearspawnpoint",
            "/give", "/stop", "/op", "/clear", "/deop", "/clone", "/role"};

    public static final String[] BUILDER_BLOCKS = {"/summon", "/kill", "/ban", "/gamemode", "/kick", "/gamerule", "/bossbar", "/changesettings",
            "/clearspawnpoint", "/kick", "/xp", "/weather", "/time", "/stop", "/setworldspawn", "/scoreboard", "/op", "/clear", "/deop", "/clone", "/role"};

    public static final String[] NEWCOMER_ALLOWS = {"/msg", "/?", "/help", "/say", "/tell", "/w"};

    Main main;

    public PermissonsManager(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        YamlConfiguration readFile = YamlConfiguration.loadConfiguration(main.file);
        Object o = Objects.requireNonNull(readFile.get(String.valueOf(e.getPlayer().getUniqueId())));
        if ("ADMIN".equals(o)) {
            checkCommand(ADMIN_BLOCKS, e);
        } else if ("HELPER".equals(o)) {
            checkCommand(HELPER_BLOCKS, e);
        } else if ("BUILDER".equals(o)) {
            checkCommand(BUILDER_BLOCKS, e);
        }

        else if ("NEWCOMER".equals(o)) {
            boolean isBanned = true;
            for(String command : NEWCOMER_ALLOWS ) {
                if(e.getMessage().contains(command)) {
                    isBanned = false;
                }
            }
            if(isBanned) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "You can't use that command!");
            }
        }
    }

    public void checkCommand(String[] args, PlayerCommandPreprocessEvent e) {
        for(String command : args) {
            if(e.getMessage().contains(command)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "You can't use that command!");
            }
        }
    }
}