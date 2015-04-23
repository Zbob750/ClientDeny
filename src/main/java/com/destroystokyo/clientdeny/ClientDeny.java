package com.destroystokyo.clientdeny;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class ClientDeny extends JavaPlugin implements Listener {
    private int MAX_VERSION = 5; // 1.7.6 - 1.7.10 || see http://wiki.vg/Protocol_version_numbers

    @Override
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if ( ((CraftPlayer) event.getPlayer() ).getHandle().playerConnection.networkManager.getVersion() > MAX_VERSION )
        {
            event.getPlayer().kickPlayer("This server only supports 1.7 clients, please reconnect with one.");
            this.getLogger().log(Level.INFO, "Kicking player " + event.getPlayer().getName() + " for using an unsupported client");
        }
    }
}
