package org.laykon.newsurvival.Commands.Gamemodes;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class GamemodeCreative implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(ensurePlayer(commandSender) || ensureOp(commandSender))){
            return false;
        }
        Player player = (Player) commandSender;
        if (player.getGameMode() != GameMode.CREATIVE){
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("§aGamemode set to §6Creative");
            return true;
        }else {
            player.sendMessage("§cCommand not found.");
        }
        return false;
    }
}
