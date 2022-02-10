package com.ren.rank;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RoleCommand implements CommandExecutor {

    Main main;

    public RoleCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 2) {
                YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(main.file);
                boolean isValid = false;
                for (Player target : Bukkit.getOnlinePlayers()) {
                    if(target.getName().equalsIgnoreCase(args[0])) {isValid = true;}
                }

                if(isValid) {
                    if(Arrays.asList(Main.RANKS).contains(args[1].toUpperCase())){
                        modifyFile.set(String.valueOf(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getUniqueId()), args[1]);
                        try {
                            modifyFile.save(main.file);
                            player.sendMessage(ChatColor.GREEN + "Successfully added the role!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Couldn't find the role!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Couldn't find the Player!");
                }
            }
        }
        return false;
    }
}