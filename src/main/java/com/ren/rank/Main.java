package com.ren.rank;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static final String[] RANKS = {"OWNER", "ADMIN", "HELPER", "BUILDER", "NEWCOMER"};
    File file;

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("role")).setExecutor(new RoleCommand(this));
        Objects.requireNonNull(getCommand("role")).setTabCompleter(new RoleTab());

        file = new File(getDataFolder(), "roles.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {}
        }
    }
}
