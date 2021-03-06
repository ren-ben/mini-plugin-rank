package com.ren.rank;

import com.ren.rank.nametags.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class RoleCommand implements CommandExecutor {

    Main main;

    public RoleCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            //The command is executed when the number of arguments is 2.
            if (args.length == 2) {
                YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(main.file);
                boolean isValid = false;

                for (Player target : Bukkit.getOnlinePlayers())
                    if (target.getName().equalsIgnoreCase(args[0])) isValid = true;

                //Checks if arguments are correct
                if (isValid) {
                    if (Arrays.asList(Main.RANKS).contains(args[1].toUpperCase())) {
                        //Save the parameters in the .yml File
                        modifyFile.set(String.valueOf(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getUniqueId()), args[1].toUpperCase());
                        try {
                            modifyFile.save(main.file);
                            NametagManager.newTag(Objects.requireNonNull(Bukkit.getPlayer(args[0])), main);
                            player.sendMessage(ChatColor.GREEN + "Successfully added the role!");
                        } catch (IOException ignore) {
                        }
                        // This fires when you either have the wrong Role or the wrong Player
                    } else player.sendMessage(ChatColor.RED + "Couldn't find the role!");
                } else player.sendMessage(ChatColor.RED + "Couldn't find the Player!");
            }

        //Same thing but for the Console
        } else {
            if (args.length == 2) {
                YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(main.file);
                boolean isValid = false;
                for (Player target : Bukkit.getOnlinePlayers())
                    if (target.getName().equalsIgnoreCase(args[0])) isValid = true;
                if (isValid) {
                    if (Arrays.asList(Main.RANKS).contains(args[1].toUpperCase())) {
                        modifyFile.set(String.valueOf(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getUniqueId()), args[1].toUpperCase());
                        try {
                            modifyFile.save(main.file);
                            NametagManager.newTag(Objects.requireNonNull(Bukkit.getPlayer(args[0])), main);
                            System.out.println("Successfully added the role!");
                        } catch (IOException ignore) {
                        }
                    } else System.out.println("Couldn't find the role!");
                } else System.out.println("Couldn't find the Player!");
            }
        }
        return false;
    }
}