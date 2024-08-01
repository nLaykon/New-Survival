package org.laykon.newsurvival.Utility;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface Commands extends CommandExecutor, Listener {

    default boolean checkOp(CommandSender sender) {
        if (sender.isOp()) {
            return true;
        }
        sender.sendMessage("§cCommand not found.");
        return false;

    }

    default boolean checkPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return true;
        }
        sender.sendMessage("§cThis command can only be run by a player.");
        return false;
    }
}