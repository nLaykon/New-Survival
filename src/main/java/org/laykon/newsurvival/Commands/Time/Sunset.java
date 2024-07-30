package org.laykon.newsurvival.Commands.Time;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class Sunset implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!ensureOp(sender)){
            return false;
        }
        sender.getServer().getWorlds().get(0).setTime(12000);
        return true;
    }
}
