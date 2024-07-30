package org.laykon.newsurvival.Commands.Utility;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class ServerExecute implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!ensureOp(sender)){
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /serverexec <command>");
            return false;
        }

        String commandToRun = String.join(" ", args);

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToRun);

        return true;
    }
}
