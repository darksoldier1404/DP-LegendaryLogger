package com.darksoldier1404.dll.events.players;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PlayerItemPickup implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerItemPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        YamlConfiguration data = plugin.udata.get(p.getUniqueId());
        if (data == null) return;
        String now = DLLFunction.getNow();
        Item item = e.getItem();
        Bukkit.getScheduler().runTask(plugin, () -> {
        data.set(now + ".EntityPickupItemEvent.location", item.getLocation());
        data.set(now + ".EntityPickupItemEvent.itemstack", item.getItemStack());
        });
    }
}
