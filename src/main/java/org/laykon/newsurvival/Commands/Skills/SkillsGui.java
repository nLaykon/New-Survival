package org.laykon.newsurvival.Commands.Skills;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Events.Utility.PlayerManager;
import org.laykon.newsurvival.Events.Utility.SkillsDatabaseHandling;
import org.laykon.newsurvival.Utility.Commands;
import org.laykon.newsurvival.Utility.GuiUtils;
import org.laykon.newsurvival.Utility.Locale;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
        if (!checkPlayer(commandSender)) return false;
        Player sender = (Player) commandSender;
        UUID targetUUID = sender.getUniqueId();
        String guiAddon = "";

        if (strings.length >= 1 && checkOp(sender)){
            if (PlayerManager.getPlayer(strings[0]) != null){
                targetUUID = PlayerManager.getPlayer(strings[0]);
                guiAddon = " | " + strings[0];
            }
            else {
                sender.sendMessage(Component.text(Locale.playerNeverJoined));
                return false;
            }
        }

        skillsMainMenu = Bukkit.createInventory(null, 45, "§5Skills" + guiAddon);

        try {
            GuiUtils.buildSkillsGui(skillsMainMenu, skillsMainMenuItems, targetUUID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sender.openInventory(skillsMainMenu);
        return true;
    }



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(skillsMainMenu)) {
            event.setCancelled(true);
        }
    }
}
