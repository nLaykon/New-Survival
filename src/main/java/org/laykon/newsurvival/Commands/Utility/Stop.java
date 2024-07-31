package org.laykon.newsurvival.Commands.Utility;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class Stop implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!ensureOp(commandSender)){
            return false;
        }
        if (Bukkit.isStopping()){
            commandSender.sendMessage("Already shutting down");
            return false;
        }
        for (Player player : Bukkit.getOnlinePlayers()){
            player.kick(Component.text("§cServer is restarting!"));
        }
        Bukkit.shutdown();
        return true;
    }
}
