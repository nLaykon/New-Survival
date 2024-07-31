package org.laykon.newsurvival.Commands.Gamemodes;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

public class GamemodeSpectator implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(checkOp(commandSender) || !checkPlayer(commandSender))) return false;

        Player player = (Player) commandSender;
        if (player.getGameMode() != GameMode.SPECTATOR){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§aGamemode set to §6Spectator");
            return true;
        }else {
            player.sendMessage("§cCommand not found.");
        }
        return false;
    }
}
