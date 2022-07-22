package com.darksoldier1404.dll.events.players;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        YamlConfiguration data = new YamlConfiguration();
        data.set(DLLFunction.getNow() + ".PlayerJoinEvent.uuid", p.getUniqueId().toString());
        data.set(DLLFunction.getNow() + ".PlayerJoinEvent.ip", p.getAddress().toString());
        LegendaryLogger.getInstance().udata.put(p.getUniqueId(), data);
    }
}
