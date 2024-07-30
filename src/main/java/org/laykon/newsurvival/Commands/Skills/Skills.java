package org.laykon.newsurvival.Commands.Skills;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;
import org.laykon.newsurvival.Utility.GuiUtils;
import org.laykon.newsurvival.Utility.NamespacedKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Skills implements Commands, Listener, GuiUtils {
    public static Inventory skillsMainMenu;
    public static Inventory combatSkills;
    public static Inventory utilitySkills;
    public static Inventory specializedSkills;


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

        player.openInventory(skillsMainMenu);

        return true;
    }

    //fix this shit when you can be bothered and dont have half a fuckming braincell working on this shit becayse you dony weana sleep becaus this bitch is too cute to leave lonely and you still wanna slep bu you dont wanna leaven her all along okay? got it? good bud :)

    //Next Todo: Actually make this shit work because like i fixed it but like its fucking stupid with how its handled and also it has 0 sort of GUI built and to be quite honest it fucking needs it and like this is really annoyinh me ans i jsut wanna sleep :(

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(skillsMainMenu)) {
            event.setCancelled(true);
        }
    }
}
