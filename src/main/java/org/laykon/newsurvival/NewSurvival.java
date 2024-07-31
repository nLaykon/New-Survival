package org.laykon.newsurvival;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeAdventure;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeCreative;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeSpectator;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeSurvival;
import org.laykon.newsurvival.Commands.Skills.SkillsGui;
import org.laykon.newsurvival.Commands.Skills.SkillsDatabaseEventHandling;
import org.laykon.newsurvival.Commands.Skills.SkillsManager;
import org.laykon.newsurvival.Commands.Time.*;
import org.laykon.newsurvival.Commands.Utility.*;
import org.laykon.newsurvival.Data.Config;
import org.laykon.newsurvival.Data.DataBase;

public final class NewSurvival extends JavaPlugin {
    private DataBase database;
    private Config config;
    private static NewSurvival instance;

    public static NewSurvival getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        config = new Config(this);

        String url = config.getDatabaseUrl();
        String user = config.getDatabaseUser();
        String password = config.getDatabasePassword();

        database = new DataBase(url, user, password);

        if (database.isConnected()) {
            getLogger().info("Successfully connected to the database!");
        } else {
            getLogger().warning("Failed to connect to the database.");
        }


        cmd("gma", new GamemodeAdventure());
        cmd("gmc", new GamemodeCreative());
        cmd("gms", new GamemodeSurvival());
        cmd("gmsp", new GamemodeSpectator());
        cmd("ping", new Ping());
        cmd("skills", new SkillsGui());
        cmd("repeat", new Repeat());
        cmd("serverexec", new ServerExecute());
        cmd("day", new Day());
        cmd("night", new Night());
        cmd("noon", new Noon());
        cmd("sunrise", new Sunrise());
        cmd("sunset", new Sunset());
        cmd("fly", new Fly());
        cmd("stop", new Stop());

        event(new SkillsGui());
        event(new SkillsDatabaseEventHandling());


    }

    @Override
    public void onDisable() {
        if (database != null && database.isConnected()) {
            database.close();
        }
        HandlerList.unregisterAll();
    }

    public DataBase getDatabase() {
        return database;
    }

    public void cmd(String name, CommandExecutor command) {
        getCommand(name).setExecutor(command);
    }

    public static void executeCmd(String command) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
        System.out.println("Command Executed: " + command);
    }

    public void event(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

}

