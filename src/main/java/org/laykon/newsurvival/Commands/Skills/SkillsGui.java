package org.laykon.newsurvival.Commands.Skills;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;
import java.util.Arrays;
import java.util.List;

public class SkillsGui implements Commands {
    public static Inventory skillsMainMenu;
    public static Inventory combatSkills;
    public static Inventory utilitySkills;
    public static Inventory specializedSkills;

    public static List<String> skillsMainMenuItems = Arrays.asList(
            "x", "y", "x", "y", "x", "y", "x", "y", "x",
            "y", "a", "y", "x", "b", "x", "y", "c", "y",
            "x", "y", "x", "y", "x", "y", "x", "y", "x"
    );
    public static List<String> combatSkillsItems = Arrays.asList(
            "x", "y", "x", "y", "x", "y", "x", "y", "x",
            "y", "x", "y", "x", "y", "x", "y", "x", "y",
            "x", "y", "x", "y", "x", "y", "x", "y", "x"
    );
    public static List<String> utilitySkillsItems = Arrays.asList(
            "x", "y", "x", "y", "x", "y", "x", "y", "x",
            "y", "x", "y", "x", "y", "x", "y", "x", "y",
            "x", "y", "x", "y", "x", "y", "x", "y", "x"
    );
    public static List<String> specializedSkillsItems = Arrays.asList(
            "x", "y", "x", "y", "x", "y", "x", "y", "x",
            "y", "x", "y", "x", "y", "x", "y", "x", "y",
            "x", "y", "x", "y", "x", "y", "x", "y", "x"
    );




    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!ensurePlayer(commandSender)) {
            return false;
        }
        Player player = (Player) commandSender;

        skillsMainMenu = Bukkit.createInventory(null, 27, "§aSkills");
        combatSkills = Bukkit.createInventory(null, 27, "§aCombat Skills");
        utilitySkills = Bukkit.createInventory(null, 27, "§aUtility Skills");
        specializedSkills = Bukkit.createInventory(null, 27, "§aSpecialized Skills");

        buildSkillsGui(skillsMainMenu, skillsMainMenuItems);

        player.openInventory(skillsMainMenu);

        return true;
    }



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(skillsMainMenu)) {
            event.setCancelled(true);
        }
    }
}
