package xyz.herberto.ezScore.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import xyz.herberto.ezScore.EZScore;
import xyz.herberto.ezScore.utils.CC;

import java.util.*;

public class ScoreboardManager {
    public static final Map<UUID, FastBoard> boards = new HashMap<>();
    public static boolean isPAPI = false;

    public static void autoUpdate() {

        EZScore.getInstance().getServer().getScheduler().runTaskTimer(EZScore.getInstance(), () -> {
            for(FastBoard board : boards.values()) {
                update(board);
            }
        }, 0, EZScore.getInstance().getConfig().getInt("update-interval", 20));

    }

    public static void cancelTasks() {
        EZScore.getInstance().getServer().getScheduler().cancelTasks(EZScore.getInstance());
    }

    public static void create(Player player) {
        FastBoard board = new FastBoard(player);
        update(board);
        boards.put(player.getUniqueId(), board);
    }

    public static void delete(Player player) {
        FastBoard board = boards.remove(player.getUniqueId());

        if(board != null) {
            board.delete();
        }
    }

    public static void update(FastBoard board) {

        List<String> lines = new ArrayList<>();


        if(isPAPI) {
            board.updateTitle(CC.translate(PlaceholderAPI.setPlaceholders(board.getPlayer(), EZScore.getInstance().getConfig().getString("title", "&b&lEZScore"))));
            for(String line : EZScore.getInstance().getConfig().getStringList("lines")) {
                lines.add(CC.translate(PlaceholderAPI.setPlaceholders(board.getPlayer(), line)));
            }
        } else {
            board.updateTitle(CC.translate(EZScore.getInstance().getConfig().getString("title", "&b&lEZScore")));
            for(String line : EZScore.getInstance().getConfig().getStringList("lines")) {
                lines.add(CC.translate(line));
            }
        }

        board.updateLines(lines);

    }

}
