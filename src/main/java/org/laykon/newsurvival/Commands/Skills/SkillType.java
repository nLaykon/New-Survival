package org.laykon.newsurvival.Commands.Skills;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum SkillType {
    SMITHING(2, "Smithing", ChatColor.GRAY, Material.MACE),
    SWORD(3, "Swordsmanship", ChatColor.DARK_RED, Material.IRON_SWORD),
    BOW(4, "Archery", ChatColor.GOLD, Material.BOW),
    DEFENSE(5, "Defense", ChatColor.DARK_GRAY, Material.IRON_CHESTPLATE),
    TAMING(6, "Taming", ChatColor.DARK_GREEN, Material.WHEAT),
    MINING(7, "Mining", ChatColor.GRAY, Material.IRON_PICKAXE),
    LUMBER(8, "Lumber", ChatColor.GREEN, Material.IRON_AXE),
    FISHING(9, "Fishing", ChatColor.AQUA, Material.FISHING_ROD),
    ALCHEMY(10, "Alchemy", ChatColor.DARK_PURPLE, Material.POTION),
    COOKING(11, "Smelting", ChatColor.DARK_GRAY, Material.FURNACE),
    TRADING(12, "Trading", ChatColor.GREEN, Material.EMERALD),
    EXPLORATION(13, "Exploration", ChatColor.DARK_AQUA, Material.FILLED_MAP),
    SURVIVAL(14, "Survival", ChatColor.GREEN, Material.GOLDEN_APPLE),
    HUNTING(15, "Hunting", ChatColor.DARK_GREEN, Material.WOODEN_SWORD),
    STEALTH(16, "Stealth", ChatColor.DARK_GRAY, Material.LEATHER_BOOTS),
    ENCHANTING(17, "Enchanting", ChatColor.LIGHT_PURPLE, Material.ENCHANTED_BOOK);

    private final int i;
    private final String skillName;
    private final ChatColor itemColor;
    private final Material itemType;
    SkillType(int i, String skillName, ChatColor itemColor, Material itemType) {
        this.i = i;
        this.skillName = skillName;
        this.itemColor = itemColor;
        this.itemType = itemType;
    }

    public String getSkillName() {
        return skillName;
    }

    public ChatColor getItemColor() {
        return itemColor;
    }

    public Material getItemType() {
        return itemType;
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


