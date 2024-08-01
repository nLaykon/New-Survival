package org.laykon.newsurvival.Events.Utility;

import org.laykon.newsurvival.Utility.Skills.SkillType;
import org.laykon.newsurvival.Data.DataBase;
import org.laykon.newsurvival.NewSurvival;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void incExperience (UUID uuid, SkillType skillType, BigDecimal amount){
        if (!(this.experienceMap.containsKey(uuid))){
            return;
        }
        Map<SkillType, BigDecimal> expMap = this.experienceMap.get(uuid);
        expMap.put(skillType, expMap.get(skillType).add(amount));
    }

    public void decExperience (UUID uuid, SkillType skillType, BigDecimal amount){
        if (!(this.experienceMap.containsKey(uuid))){
            return;
        }
        Map<SkillType, BigDecimal> expMap = this.experienceMap.get(uuid);
        if (expMap.get(skillType).compareTo(amount) < 0){
            return;
        }
        expMap.put(skillType, expMap.get(skillType).subtract(amount));
    }

    public void setExperience (UUID uuid, SkillType skillType, BigDecimal amount){
        if (!(this.experienceMap.containsKey(uuid))){
            return;
        }
        Map<SkillType, BigDecimal> expMap = this.experienceMap.get(uuid);
        expMap.put(skillType, amount);

    }

    public int getLevel(BigDecimal experience) {
        BigDecimal baseXP = new BigDecimal(100);
        int level = 1;
        while (experience.compareTo(baseXP.multiply(new BigDecimal(level))) >= 0) {
            level++;
        }
        return level;
    }

    public BigDecimal getNextLevel(BigDecimal experience) {
        int currentLevel = getLevel(experience);
        BigDecimal baseXP = new BigDecimal(100);
        BigDecimal nextLevelXP = baseXP.multiply(new BigDecimal(currentLevel).multiply(new BigDecimal(Math.PI/2.5)));
        return nextLevelXP.setScale(2, RoundingMode.HALF_UP);
    }



}

