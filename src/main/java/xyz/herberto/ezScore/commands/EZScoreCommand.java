package xyz.herberto.ezScore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import xyz.herberto.ezScore.EZScore;
import xyz.herberto.ezScore.scoreboard.ScoreboardManager;
import xyz.herberto.ezScore.utils.CC;

@CommandAlias("ezscore")
public class EZScoreCommand extends BaseCommand {

    @HelpCommand
    @Default
    public void help(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("ezscore.command.reload")
    public void reload(CommandSender sender) {
        EZScore.getInstance().reloadConfig();
        ScoreboardManager.cancelTasks();
        ScoreboardManager.autoUpdate();
        sender.sendMessage(CC.translate("&aYou have reloaded the EZScore config!"));

    }

}
