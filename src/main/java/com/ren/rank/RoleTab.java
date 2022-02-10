package com.ren.rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        //Tab Completion for the first argument
        if(args.length == 1) {
            List<String> names = new ArrayList<>();
            for(Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());

        //Tab Completion for the second argument
        } else if(args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], Arrays.asList(Main.RANKS_TAB), new ArrayList<>());
        }
        return null;
    }
}
