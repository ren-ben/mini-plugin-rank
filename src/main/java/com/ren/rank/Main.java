package com.ren.rank;

import com.ren.rank.nametags.NametagListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static final String[] RANKS = {"OWNER", "ADMIN", "HELPER", "BUILDER", "NEWCOMER"};
    public static final String[] RANKS_TAB = {"Owner", "Admin", "Helper", "Builder", "Newcomer"};
    public File file;

    //Sets everything up 🔥
    @Override
    public void onEnable() {

        //✔️ Set up 'role' command and tab completion
        Objects.requireNonNull(getCommand("role")).setExecutor(new RoleCommand(this));
        Objects.requireNonNull(getCommand("role")).setTabCompleter(new RoleTab());

        //✔️ Set up the Name-tag Listener
        Bukkit.getPluginManager().registerEvents(new NametagListener(this), this);

        //✔️ Set up the roles.yml File
        file = new File(getDataFolder(), "roles.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {}
        }
    }
}
