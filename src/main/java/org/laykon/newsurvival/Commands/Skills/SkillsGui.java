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
            "y", "a", "b", "c", "d", "e", "f", "g", "y",
            "x", "h", "i", "j", "o", "l", "m", "n", "x",
            "y", "x", "k", "x", "y", "x", "p", "x", "y",
            "x", "y", "x", "y", "x", "y", "x", "y", "x"
    );


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!ensurePlayer(commandSender)) {
            return false;
        }
        Player player = (Player) commandSender;

        skillsMainMenu = Bukkit.createInventory(null, 45, "§5Skills");

        buildSkillsGui(skillsMainMenu, skillsMainMenuItems, player.getUniqueId());

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
