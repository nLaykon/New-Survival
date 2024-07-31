package org.laykon.newsurvival.Commands.Utility;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Utility.Commands;

import java.util.Arrays;

public class Repeat implements Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!checkOp(sender)) return false;

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /repeat <times> <command>");
            return false;
        }

        int times;
        try {
            times = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cThe number of times must be an integer.");
            return false;
        }

        if (times <= 0) {
            sender.sendMessage("§cThe number of times must be greater than zero.");
            return false;
        }

        String commandToRun = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (sender instanceof Player) {
            Player player = (Player) sender;
            for (int i = 0; i < times; i++) {
                Bukkit.dispatchCommand(player, commandToRun);
            }
            sender.sendMessage("§aCommand executed " + times + " times.");
        } else {
            // If the sender is console, run the command as the console
            for (int i = 0; i < times; i++) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToRun);
            }
            sender.sendMessage("§aCommand executed " + times + " times as console.");
        }

        return true;
    }
}
