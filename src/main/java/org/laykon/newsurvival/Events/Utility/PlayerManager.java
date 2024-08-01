package org.laykon.newsurvival.Events.Utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.laykon.newsurvival.Data.DataBase;
import org.laykon.newsurvival.NewSurvival;
import org.laykon.newsurvival.Utility.Console;
import org.laykon.newsurvival.Utility.Skills.Events;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager implements Events {
    DataBase dataBase = NewSurvival.getInstance().getDatabase();
    public static HashMap<String, UUID> playerUuidTracker = new HashMap<>();
    Connection connection = dataBase.getConnection();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent it) {
        if (playerUuidTracker.containsKey(it.getPlayer().getName())) {
            return;
        }
        playerUuidTracker.put(it.getPlayer().getName(), it.getPlayer().getUniqueId());
        Console.success("Player " + it.getPlayer().getName() + " has been recorded!");
    }

    @EventHandler
    public void onServerShutdown(PluginDisableEvent it) throws SQLException {
        if (it.getPlugin() != NewSurvival.getInstance()) {
            return;
        }
        saveData();

    }

    @EventHandler
    public void onServerBoot(PluginEnableEvent it) throws SQLException {
        if (it.getPlugin() != NewSurvival.getInstance()) {
            return;
        }
        loadData();

    }

    //Functions
    public void loadData() throws SQLException {
        String query = "SELECT * FROM playeruuid";

        try (PreparedStatement selectStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    String playerName = resultSet.getString("PlayerName");
                    UUID uuid = UUID.fromString(resultSet.getString("UUID"));
                    playerUuidTracker.put(playerName, uuid);
                }
            }
        }
        Console.success("Tracked Players loaded!");
    }

    public void saveData() throws SQLException{
        String insertOrUpdateQuery = "INSERT INTO playeruuid (PlayerName, UUID) VALUES (?, ?) ON DUPLICATE KEY UPDATE UUID = VALUES(UUID)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrUpdateQuery)) {
            for (Map.Entry<String, UUID> entry : playerUuidTracker.entrySet()) {
                String playerName = entry.getKey();
                UUID uuid = entry.getValue();

                preparedStatement.setString(1, playerName);
                preparedStatement.setString(2, uuid.toString());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        }

        Console.success("Tracked Players saved!");
    }

    public static UUID getPlayer(String playerName) {
        //Online Players
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(playerName)) {
                return player.getUniqueId();
            }
        }
        //Tracked Players
        for (String player : playerUuidTracker.keySet()) {
            if (player.equals(playerName)) {
                return playerUuidTracker.get(player);
            }
        }
        return null;
    }
}
