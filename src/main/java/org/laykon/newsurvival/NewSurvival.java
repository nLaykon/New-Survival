package org.laykon.newsurvival;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeAdventure;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeCreative;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeSpectator;
import org.laykon.newsurvival.Commands.Gamemodes.GamemodeSurvival;
import org.laykon.newsurvival.Commands.Skills.SkillsGui;
import org.laykon.newsurvival.Events.Utility.PlayerManager;
import org.laykon.newsurvival.Events.Utility.SkillsDatabaseHandling;
import org.laykon.newsurvival.Commands.Time.*;
import org.laykon.newsurvival.Commands.Utility.*;
import org.laykon.newsurvival.Data.Config;
import org.laykon.newsurvival.Data.DataBase;
import org.laykon.newsurvival.Utility.CheckSkillHashmap;
import org.laykon.newsurvival.Utility.Console;

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
        Console.success("Gotten Config");

        String url = config.getDatabaseUrl();
        String user = config.getDatabaseUser();
        String password = config.getDatabasePassword();

        database = new DataBase(url, user, password);

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
        cmd("dev/skills/test-map", new CheckSkillHashmap());

        Console.success("Registered all commands!");

        event(new SkillsGui());
        event(new SkillsDatabaseHandling());
        event(new PlayerManager());

        Console.success("Registered all events!");

        for (Player player : Bukkit.getOnlinePlayers()){
            Bukkit.getServer().getPluginManager().callEvent(new PlayerJoinEvent(player, Component.text("Reloaded!")));
        }
        Console.success("Initialized skill data!");

        Console.success("New Survival loaded!");
    }

    @Override
    public void onDisable() {
        Console.warn("Shutting down!");
        if (database != null && database.isConnected()) {
            database.close();
        }

        HandlerList.unregisterAll();
        Console.success("Handlers unregistered!");
    }

    public DataBase getDatabase() {
        return database;
    }

    public void cmd(String name, CommandExecutor command) {
        getCommand(name).setExecutor(command);
    }

    public void event(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

}

