package net.mcpueblo.discordwhitelistmanager.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RemoveWhitelistUtil {
    public static String removeWhitelist(UUID mcUUID) {
        if (!(Bukkit.getPlayer(mcUUID) == null)) {
            Player player = Bukkit.getPlayer(mcUUID);
            String playerName = player.getName();
            player.setWhitelisted(false);
            return playerName;
        } else if (!(Bukkit.getOfflinePlayer(mcUUID) == null)) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(mcUUID);
            String playerName = player.getName();
            player.setWhitelisted(false);
            return playerName;
        } else {
            return null;
        }
    }
}
