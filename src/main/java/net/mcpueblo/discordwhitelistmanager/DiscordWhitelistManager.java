package net.mcpueblo.discordwhitelistmanager;

import github.scarsz.discordsrv.DiscordSRV;
import net.mcpueblo.discordwhitelistmanager.listeners.GuildLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordWhitelistManager extends JavaPlugin {

    private final GuildLeaveListener discordsrvListener = new GuildLeaveListener();

    @Override
    public void onEnable() {
        DiscordSRV.api.subscribe(discordsrvListener);
    }
    @Override
    public void onDisable() {
        DiscordSRV.api.unsubscribe(discordsrvListener);
    }
}
