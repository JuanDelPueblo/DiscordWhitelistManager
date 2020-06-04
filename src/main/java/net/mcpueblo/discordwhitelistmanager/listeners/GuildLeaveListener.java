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
import static org.bukkit.Bukkit.getLogger;

public class GuildLeaveListener extends ListenerAdapter {

    @Subscribe
    public void isJDAReady(DiscordReadyEvent event) {
        JDA api = DiscordUtil.getJda();
        api.addEventListener(new GuildLeaveListener());
        getLogger().log(INFO, "DiscordWhitelistManager has detected that DiscordSRV has been loaded!");
    }

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        getLogger().log(INFO, "Event is firing!");
        String userID = event.getUser().getId();
        String userName = event.getUser().getAsTag();
        if (!(DiscordSRV.getPlugin().getAccountLinkManager().getUuid(userID) == null)) {
            UUID mcUUID = DiscordSRV.getPlugin().getAccountLinkManager().getUuid(userID);
            /* Add back later when the async player kick bug in DiscordSRV is fixed
            DiscordSRV.getPlugin().getAccountLinkManager().unlink(userID); */
            RemoveWhitelistUtil.removeWhitelist(mcUUID);
            String playerName = RemoveWhitelistUtil.removeWhitelist(mcUUID);
            getLogger().log(INFO, ("User " + userName + " has left the Discord guild, so " + playerName + " " + mcUUID + " has been unwhitelisted."));
        }
    }
}
