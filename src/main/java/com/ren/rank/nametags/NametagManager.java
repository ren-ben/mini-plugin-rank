package com.ren.rank.nametags;

import com.ren.rank.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class NametagManager {
    public static void setNametags(Player player) {
        player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());

        for(Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.getOrderSymbol() + rank.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', rank.getDisplay()));
        }
    }

    public static void newTag(Player player, Main main) {
        Rank rank;
        YamlConfiguration readFile = YamlConfiguration.loadConfiguration(main.file);
        switch((String) Objects.requireNonNull(readFile.get(String.valueOf(player.getUniqueId())))) {
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

        for(Player target : Bukkit.getOnlinePlayers()) {
            Objects.requireNonNull(target.getScoreboard().getTeam(rank.getOrderSymbol() + rank.name())).addEntry(player.getName());
        }
    }

    public static void removeTag(Player player) { //remove player from all scoreboards
        for(Player target : Bukkit.getOnlinePlayers()) {
            Objects.requireNonNull(target.getScoreboard().getEntryTeam(player.getName())).removeEntry(player.getName());
        }
    }
}
