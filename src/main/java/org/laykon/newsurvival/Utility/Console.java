package org.laykon.newsurvival.Utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class Console {
    
    private static CommandSender sender = Bukkit.getConsoleSender();
    
    public static void error(String error){
        sender.sendMessage(ChatColor.AQUA + "[NewSurvival] " + ChatColor.RED + error);
    }
    public static void log(String log){
        sender.sendMessage(ChatColor.AQUA + "[NewSurvival] " + ChatColor.GRAY + log);
    }
    public static void warn(String warn){
        sender.sendMessage(ChatColor.AQUA + "[NewSurvival] " + ChatColor.YELLOW + warn);
    }
    public static void success(String success){
        sender.sendMessage(ChatColor.AQUA + "[NewSurvival] " + ChatColor.GREEN + success);
    }
    public static void execute(String command){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }
    
}
