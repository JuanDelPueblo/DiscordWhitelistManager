package net.mcpueblo.discordwhitelistmanager.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RemoveWhitelistUtil {
    public static String removeWhitelist(UUID mcUUID) {
        Player player = Bukkit.getPlayer(mcUUID);
        String playerName = player.getName();
        if (!(Bukkit.getPlayer(mcUUID) == null)) {
            player.setWhitelisted(false);
            return playerName;
        }
        return "unlinked";
    }
}
