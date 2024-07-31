package org.laykon.newsurvival.Commands.Skills;

import org.bukkit.entity.Player;
import org.laykon.newsurvival.Data.DataBase;
import org.laykon.newsurvival.NewSurvival;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SkillsManager {
    private final Player player;
    private final DataBase dataBase;
    private final Map<UUID, Map<SkillType, BigDecimal>> experienceMap;

    private SkillsManager(Player player) {
        this.player = player;
        this.dataBase = NewSurvival.getInstance().getDatabase();
        this.experienceMap = new HashMap<>();
    }
    public static SkillsManager get(Player player) {
        return new SkillsManager(player);
    }

    public Player player() {
        return this.player;
    }

    public void init() {

    }
}
