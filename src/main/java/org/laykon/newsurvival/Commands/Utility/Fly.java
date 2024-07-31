package org.laykon.newsurvival.Commands.Utility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class Fly implements Commands {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!checkOp(sender) || !checkPlayer(sender)) return false;

        Player player = (Player) sender;
        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.sendMessage("§aDisabled Flight");
        }else {
            player.setAllowFlight(true);
            player.sendMessage("§aEnabled Flight");
        }

        return true;
    }
}
