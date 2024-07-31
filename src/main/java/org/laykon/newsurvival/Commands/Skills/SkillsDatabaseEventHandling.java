package org.laykon.newsurvival.Commands.Skills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.laykon.newsurvival.Data.DataBase;
import org.laykon.newsurvival.NewSurvival;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class SkillsDatabaseEventHandling implements Listener {
    DataBase dataBase = NewSurvival.getInstance().getDatabase();
    public Map<UUID, Map<SkillType, BigDecimal>> experienceMap = SkillsManager.getInstance().getExperienceMap();
    Connection connection = dataBase.getConnection();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        if (!dataBase.isConnected()) {
            System.out.println("Database Disconnected!!!!!!!");
            return;
        }

        Player player = event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        String selectQuery = "SELECT * FROM PlayerSkills WHERE UUID = ?";
        String insertQuery = "INSERT INTO PlayerSkills (UUID) VALUES (?)";

        Map<SkillType, BigDecimal> newExpMap = new HashMap<>();


        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, uuidString);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 2; i <= columnCount; i++) { // Skip the UUID column
                        String columnName = resultSet.getMetaData().getColumnName(i);
                        BigDecimal value = resultSet.getBigDecimal(i);
                        SkillType skillType = SkillType.getSkillFromString(columnName);
                        if (skillType != null) {
                            newExpMap.put(skillType, value);
                        }
                    }
                    experienceMap.put(UUID.fromString(uuidString), newExpMap);
                    SkillsManager.getInstance().setExperienceMap(experienceMap);
                } else {
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                        insertStatement.setString(1, uuidString);
                        insertStatement.executeUpdate();

                        for (SkillType skillType : SkillType.values()) {
                            newExpMap.put(skillType, BigDecimal.ZERO);
                        }
                        experienceMap.put(UUID.fromString(uuidString), newExpMap);
                        SkillsManager.getInstance().setExperienceMap(experienceMap);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) throws SQLException {
        if (!dataBase.isConnected()) {
            return;
        }

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Map<SkillType, BigDecimal> skillMap = experienceMap.get(playerUUID);
        if (skillMap == null) return;

        String insertQuery = "INSERT INTO PlayerSkills (UUID, SmithingExperience, SwordExperience, BowExperience, DefenseExperience, TamingExperience, MiningExperience, LumberExperience, FishingExperience, AlchemyExperience, CookingExperience, TradingExperience, ExplorationExperience, SurvivalExperience, HuntingExperience, StealthExperience, EnchantingExperience) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE SmithingExperience=VALUES(SmithingExperience), SwordExperience=VALUES(SwordExperience), BowExperience=VALUES(BowExperience), DefenseExperience=VALUES(DefenseExperience), TamingExperience=VALUES(TamingExperience), MiningExperience=VALUES(MiningExperience), LumberExperience=VALUES(LumberExperience), FishingExperience=VALUES(FishingExperience), AlchemyExperience=VALUES(AlchemyExperience), CookingExperience=VALUES(CookingExperience), TradingExperience=VALUES(TradingExperience), ExplorationExperience=VALUES(ExplorationExperience), SurvivalExperience=VALUES(SurvivalExperience), HuntingExperience=VALUES(HuntingExperience), StealthExperience=VALUES(StealthExperience), EnchantingExperience=VALUES(EnchantingExperience)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setString(1, playerUUID.toString());
            int i = 2;
            for (SkillType skillType : SkillType.values()) {
                BigDecimal value = skillMap.getOrDefault(skillType, BigDecimal.ZERO);
                insertStatement.setBigDecimal(i, value);
                i++;
            }
            insertStatement.executeUpdate();
            System.out.println("Player skill data saved for " + playerUUID);
        }
    }
}
