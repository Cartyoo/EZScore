package xyz.herberto.ezScore;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.BukkitMessageFormatter;
import co.aikar.commands.MessageType;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.herberto.ezScore.commands.EZScoreCommand;
import xyz.herberto.ezScore.listeners.PlayerListener;
import xyz.herberto.ezScore.scoreboard.ScoreboardManager;

public final class EZScore extends JavaPlugin {

    @Getter public static EZScore instance;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        if(getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            ScoreboardManager.isPAPI = true;
        }

        ScoreboardManager.autoUpdate();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.enableUnstableAPI("help");


        manager.setFormat(MessageType.HELP, new BukkitMessageFormatter(ChatColor.YELLOW, ChatColor.WHITE, ChatColor.GRAY));
        manager.setFormat(MessageType.SYNTAX, new BukkitMessageFormatter(ChatColor.YELLOW, ChatColor.BLUE, ChatColor.WHITE));

        manager.registerCommand(new EZScoreCommand());

    }

    @Override
    public void onDisable() {

    }
}
