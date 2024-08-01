package org.laykon.newsurvival.Utility;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.laykon.newsurvival.Data.DataBase;
import org.laykon.newsurvival.Events.Utility.SkillsDatabaseHandling;
import org.laykon.newsurvival.NewSurvival;
import org.laykon.newsurvival.Utility.Skills.SkillType;
import org.laykon.newsurvival.Events.Utility.SkillsManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GuiUtils {

    static DataBase dataBase = NewSurvival.getInstance().getDatabase();
    static Connection connection = dataBase.getConnection();

    public ItemStack inventoryIdentifier(Material material, String identifier){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(NamespacedKeys.getKey(identifier), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static Map<SkillType, BigDecimal> skillMap = new HashMap<>();

    public static ItemStack skillItem(SkillType skillType, UUID uuid) throws SQLException {
        Map<SkillType, BigDecimal> skillMap = SkillsManager.getInstance().getExperienceMap().get(uuid);
        if (skillMap == null) {
            Map<SkillType, BigDecimal> newExpMap = new HashMap<>();
            String selectQuery = "SELECT * FROM playerskills WHERE UUID = ?";

            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, uuid.toString());

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int columnCount = resultSet.getMetaData().getColumnCount();
                        for (int i = 2; i <= columnCount; i++) {
                            String columnName = resultSet.getMetaData().getColumnName(i);
                            BigDecimal value = resultSet.getBigDecimal(i);

                            if (skillType != null) {
                                newExpMap.put(skillType, value);
                            }
                        }
                    }
                }
            }
            skillMap = newExpMap;
        }


        ItemStack itemStack = new ItemStack(skillType.getItemType());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(skillType.getItemColor() + skillType.getSkillName()));
        itemMeta.lore(List.of(
                Component.text("§b" + skillMap.get(skillType).toString() + " / " + SkillsManager.getInstance().getNextLevel(skillMap.get(skillType))),
                Component.text( "§r" +StringUtils.generateProgressBar(skillMap.get(skillType), SkillsManager.getInstance().getNextLevel(skillMap.get(skillType)))))
        );


        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack blankItem(Material mat){
        ItemStack itemStack = new ItemStack(mat);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(""));
        //itemMeta.lore(List.of(Component.text("")));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static void buildSkillsGui(Inventory inv, List<String> items, UUID uuid) throws SQLException {
        int i = 0;
        for (String x : items){
            switch (x){
                case "x":
                    inv.setItem(i, GuiUtils.blankItem(Material.GRAY_STAINED_GLASS_PANE));
                    break;
                case "y":
                    inv.setItem(i, GuiUtils.blankItem(Material.MAGENTA_STAINED_GLASS_PANE));
                    break;
                case "a":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.SMITHING, uuid));
                    break;
                case "b":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.SWORD, uuid));
                    break;
                case "c":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.BOW, uuid));
                    break;
                case "d":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.DEFENSE, uuid));
                    break;
                case "e":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.TAMING, uuid));
                    break;
                case "f":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.MINING, uuid));
                    break;
                case "g":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.LUMBER, uuid));
                    break;
                case "h":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.FISHING, uuid));
                    break;
                case "i":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.ALCHEMY, uuid));
                    break;
                case "j":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.COOKING, uuid));
                    break;
                case "k":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.TRADING, uuid));
                    break;
                case "l":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.EXPLORATION, uuid));
                    break;
                case "m":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.SURVIVAL, uuid));
                    break;
                case "n":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.HUNTING, uuid));
                    break;
                case "o":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.STEALTH, uuid));
                    break;
                case "p":
                    inv.setItem(i, GuiUtils.skillItem(SkillType.ENCHANTING, uuid));
                    break;

            }
            i++;
        }
    }


}
