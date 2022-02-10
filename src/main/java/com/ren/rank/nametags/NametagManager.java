package com.ren.rank.nametags;

import com.ren.rank.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class NametagManager {

    //Initializes everything, should only run on onJoin
    public static void setNametags(Player player) {
        player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());

        for(Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.getOrderSymbol() + rank.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', rank.getDisplay()));
        }
    }

    //Make / Change the Rank Tag
    public static void newTag(Player player, Main main) {
        Rank rank = getRank(player, main);

        for(Player target : Bukkit.getOnlinePlayers())
            Objects.requireNonNull(target.getScoreboard().getTeam(rank.getOrderSymbol() + rank.name())).addEntry(player.getName());
    }

    //remove player from all scoreboards
    public static void removeTag(Player player) {
        for(Player target : Bukkit.getOnlinePlayers())
            Objects.requireNonNull(target.getScoreboard().getEntryTeam(player.getName())).removeEntry(player.getName());
    }

    //Gets the Rank of the player with the color code formatting
    public static Rank getRank(Player player, Main main) {
        YamlConfiguration readFile = YamlConfiguration.loadConfiguration(main.file);
        switch((String) Objects.requireNonNull(readFile.get(String.valueOf(player.getUniqueId())))) {
            case "OWNER":
                return Rank.OWNER;
            case "ADMIN":
                return Rank.ADMIN;
            case "HELPER":
                return Rank.HELPER;
            case "BUILDER":
                return Rank.BUILDER;
            default:
                return Rank.NEWCOMER;
        }
    }
}