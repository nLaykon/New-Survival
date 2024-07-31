package org.laykon.newsurvival.Utility;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.laykon.newsurvival.Commands.Skills.SkillType;

import java.util.List;
import java.util.UUID;

public interface GuiUtils {
    default ItemStack inventoryIdentifier(Material material, String identifier){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(NamespacedKeys.getKey(identifier), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    default ItemStack skillItem(SkillType skillType){
    ItemStack itemStack = new ItemStack(skillType.getItemType());
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.displayName(Component.text(skillType.getItemColor() + skillType.getSkillName()));
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

    default void buildSkillsGui(Inventory inv, List<String> items){
        int i = 0;
        for (String x : items){
            switch (x){
                case "x":
                    inv.setItem(i, blankItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
                    break;
                case "y":
                    inv.setItem(i, blankItem(Material.GRAY_STAINED_GLASS_PANE));
                    break;
                case "a":
                    inv.setItem(i, skillItem(SkillType.BOW));
                    break;
                case "b":
                    inv.setItem(i, skillItem(SkillType.EXPLORATION));
                    break;
                case "c":
                    inv.setItem(i, skillItem(SkillType.TRADING));
                    break;

            }
            i++;
        }
    }


}
