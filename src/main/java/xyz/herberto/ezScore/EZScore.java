package xyz.herberto.ezScore;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.herberto.ezScore.listeners.PlayerListener;
import xyz.herberto.ezScore.scoreboard.ScoreboardManager;

public final class EZScore extends JavaPlugin {

    @Getter public static EZScore instance;

    @Override
    public void onEnable() {

        instance = this;

        if(getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            ScoreboardManager.isPAPI = true;
        }

        ScoreboardManager.autoUpdate();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
