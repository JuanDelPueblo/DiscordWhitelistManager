package net.mcpueblo.discordwhitelistmanager.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.ListenerPriority;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordReadyEvent;
import github.scarsz.discordsrv.dependencies.jda.api.JDA;
import github.scarsz.discordsrv.dependencies.jda.api.events.guild.member.GuildMemberRemoveEvent;
import github.scarsz.discordsrv.dependencies.jda.api.hooks.ListenerAdapter;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.mcpueblo.discordwhitelistmanager.utils.RemoveWhitelistUtil;

import java.util.UUID;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;
import static org.bukkit.Bukkit.getLogger;

public class GuildLeaveListener extends ListenerAdapter {

    @Subscribe
    public void isJDAReady(DiscordReadyEvent event) {
        JDA api = DiscordUtil.getJda();
        api.addEventListener(new GuildLeaveListener());
        getLogger().log(INFO, "[DiscordWhitelistManager] DiscordSRV has finished loading, now listening to members leaving the guild...");
    }

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        String userID = event.getUser().getId();
        String userName = event.getUser().getAsTag();
        if (!(DiscordSRV.getPlugin().getAccountLinkManager().getUuid(userID) == null)) {
            getLogger().log(INFO, "[DiscordWhitelistManager] Linked user " + userName + " has left the Discord guild! Commencing removal procedures.");
            UUID mcUUID = DiscordSRV.getPlugin().getAccountLinkManager().getUuid(userID);
            DiscordSRV.getPlugin().getAccountLinkManager().unlink(userID);
            RemoveWhitelistUtil.removeWhitelist(mcUUID);
            String playerName = RemoveWhitelistUtil.removeWhitelist(mcUUID);
            if (!(playerName == null)) {
                getLogger().log(INFO, "[DiscordWhitelistManager] " + playerName + " (" + mcUUID + "), linked to " + userName + ", has been fully removed from the server.");
            } else {
                getLogger().log(WARNING, "[DiscordWhitelistManager] Failed to remove player from whitelist!");
            }
        }
    }
}
