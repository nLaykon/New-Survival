package org.laykon.newsurvival.Commands.Skills;

public enum SkillType {
    SMITHING(2),
    SWORD(3),
    BOW(4),
    DEFENSE(5),
    TAMING(6),
    MINING(7),
    LUMBER(8),
    FISHING(9),
    ALCHEMY(10),
    COOKING(11),
    TRADING(12),
    EXPLORATION(13),
    SURVIVAL(14),
    HUNTING(15),
    STEALTH(16),
    ENCHANTING(17);

    private final int i;
    SkillType(int i) {
        this.i = i;
    }

    static SkillType getSkillFromString(String string) {
        if (string == null || !string.endsWith("Experience")) {
            throw new IllegalArgumentException("Invalid skill string: " + string);
        }
        String skillName = string.substring(0, string.length() - "Experience".length());

        try {
            return SkillType.valueOf(skillName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No matching SkillType for: " + skillName, e);
        }
    }
}


