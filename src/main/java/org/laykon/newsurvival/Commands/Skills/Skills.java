package org.laykon.newsurvival.Commands.Skills;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class Skills implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!ensurePlayer(commandSender)){
            return false;
        }

        commandSender.sendMessage("asd");

        return true;
    }
}
