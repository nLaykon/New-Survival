package org.laykon.newsurvival.Utility;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.laykon.newsurvival.Commands.Skills.SkillType;
import org.laykon.newsurvival.Commands.Skills.SkillsManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GuiUtils {
    default ItemStack inventoryIdentifier(Material material, String identifier){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(NamespacedKeys.getKey(identifier), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    default ItemStack skillItem(SkillType skillType, UUID uuid){
        Map<UUID, Map<SkillType, BigDecimal>> experienceMap = SkillsManager.getInstance().getExperienceMap();
        if (experienceMap == null) {
            System.out.println("a");
        }
        if (experienceMap.get(uuid) == null){
            System.out.println("b");
        }

        ItemStack itemStack = new ItemStack(skillType.getItemType());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(skillType.getItemColor() + skillType.getSkillName()));
        itemMeta.lore(List.of(
                Component.text("§b" + experienceMap.get(uuid).get(skillType).toString()),
                Component.text( "§r" +StringUtils.generateProgressBar(Integer.parseInt(experienceMap.get(uuid).get(skillType).toString()), 250)))
        );


        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    default ItemStack blankItem(Material mat){
        ItemStack itemStack = new ItemStack(mat);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(""));
        //itemMeta.lore(List.of(Component.text("")));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    default void buildSkillsGui(Inventory inv, List<String> items, UUID uuid){
        int i = 0;
        for (String x : items){
            switch (x){
                case "x":
                    inv.setItem(i, blankItem(Material.GRAY_STAINED_GLASS_PANE));
                    break;
                case "y":
                    inv.setItem(i, blankItem(Material.MAGENTA_STAINED_GLASS_PANE));
                    break;
                case "a":
                    inv.setItem(i, skillItem(SkillType.SMITHING, uuid));
                    break;
                case "b":
                    inv.setItem(i, skillItem(SkillType.SWORD, uuid));
                    break;
                case "c":
                    inv.setItem(i, skillItem(SkillType.BOW, uuid));
                    break;
                case "d":
                    inv.setItem(i, skillItem(SkillType.DEFENSE, uuid));
                    break;
                case "e":
                    inv.setItem(i, skillItem(SkillType.TAMING, uuid));
                    break;
                case "f":
                    inv.setItem(i, skillItem(SkillType.MINING, uuid));
                    break;
                case "g":
                    inv.setItem(i, skillItem(SkillType.LUMBER, uuid));
                    break;
                case "h":
                    inv.setItem(i, skillItem(SkillType.FISHING, uuid));
                    break;
                case "i":
                    inv.setItem(i, skillItem(SkillType.ALCHEMY, uuid));
                    break;
                case "j":
                    inv.setItem(i, skillItem(SkillType.COOKING, uuid));
                    break;
                case "k":
                    inv.setItem(i, skillItem(SkillType.TRADING, uuid));
                    break;
                case "l":
                    inv.setItem(i, skillItem(SkillType.EXPLORATION, uuid));
                    break;
                case "m":
                    inv.setItem(i, skillItem(SkillType.SURVIVAL, uuid));
                    break;
                case "n":
                    inv.setItem(i, skillItem(SkillType.HUNTING, uuid));
                    break;
                case "o":
                    inv.setItem(i, skillItem(SkillType.STEALTH, uuid));
                    break;
                case "p":
                    inv.setItem(i, skillItem(SkillType.ENCHANTING, uuid));
                    break;

            }
            i++;
        }
    }


}
