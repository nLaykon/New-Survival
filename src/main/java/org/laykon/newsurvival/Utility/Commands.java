package org.laykon.newsurvival.Utility;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface Commands extends CommandExecutor, Listener, GuiUtils {

    default boolean ensureOp(CommandSender sender) {
        if (!sender.isOp()) {
            sender.sendMessage("§cCommand not found.");
            return false;
        }
        return true;
    }

    default boolean ensurePlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be run by a player.");
            return false;
        }
        return true;
    }
}