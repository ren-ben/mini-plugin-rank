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
    public File file;

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("role")).setExecutor(new RoleCommand(this));
        Objects.requireNonNull(getCommand("role")).setTabCompleter(new RoleTab());
        Bukkit.getPluginManager().registerEvents(new NametagListener(this), this);

        file = new File(getDataFolder(), "roles.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {}
        }
    }
}
