package org.laykon.newsurvival.Commands.Skills;

import org.laykon.newsurvival.Data.DataBase;
import org.laykon.newsurvival.NewSurvival;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SkillsManager {
    private static final SkillsManager INSTANCE = new SkillsManager();
    private final DataBase dataBase;
    private Map<UUID, Map<SkillType, BigDecimal>> experienceMap;

    private SkillsManager() {
        this.dataBase = NewSurvival.getInstance().getDatabase();
        this.experienceMap = new HashMap<>();
    }

    public static SkillsManager getInstance() {
        return INSTANCE;
    }

    public Map<UUID, Map<SkillType, BigDecimal>> getExperienceMap() {
        return this.experienceMap;
    }

    public void setExperienceMap(Map<UUID, Map<SkillType, BigDecimal>> experienceMap) {
        this.experienceMap = experienceMap;
    }
}

