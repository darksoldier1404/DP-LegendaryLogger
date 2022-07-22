package com.darksoldier1404.dll.events.players;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

public class PlayerInventoryClose implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        YamlConfiguration data = plugin.udata.get(p.getUniqueId());
        if (data == null) return;
        String now = DLLFunction.getNow();
        InventoryView view = e.getView();
        Bukkit.getScheduler().runTask(plugin, () -> {
        data.set(now + ".InventoryCloseEvent.title", view.getTitle());
        data.set(now + ".InventoryCloseEvent.invtype", view.getType().toString());
        });
    }
}
