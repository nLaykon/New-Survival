package org.laykon.newsurvival.Commands.Utility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class Ping implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!ensureOp(commandSender)){
            return false;
        }
        commandSender.sendMessage("Pong!");
        return true;
    }
}
