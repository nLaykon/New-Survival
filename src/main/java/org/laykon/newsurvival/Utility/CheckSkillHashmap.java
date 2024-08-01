package org.laykon.newsurvival.Utility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.laykon.newsurvival.Events.Utility.SkillsManager;

public class CheckSkillHashmap implements Commands{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(checkOp(sender))) return false;
        sender.sendMessage(SkillsManager.getInstance().getExperienceMap().toString());
        return true;
    }
}
