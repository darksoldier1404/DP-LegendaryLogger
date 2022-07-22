package com.darksoldier1404.dll.events.players;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class PlayerInventoryClick implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        YamlConfiguration data = plugin.udata.get(p.getUniqueId());
        if (data == null) return;
        String now = DLLFunction.getNow();
        InventoryView view = e.getView();
        Bukkit.getScheduler().runTask(plugin, () -> {
            data.set(now + ".InventoryClickEvent.title", view.getTitle());
            data.set(now + ".InventoryClickEvent.invtype", view.getType().toString());
            data.set(now + ".InventoryClickEvent.slot", e.getSlot());
            data.set(now + ".InventoryClickEvent.rawslot", e.getRawSlot());
            data.set(now + ".InventoryClickEvent.item", e.getCurrentItem());
            data.set(now + ".InventoryClickEvent.action", e.getAction().toString());
            data.set(now + ".InventoryClickEvent.result", e.getResult().toString());
            data.set(now + ".InventoryClickEvent.hotbar", e.getHotbarButton());
        });
    }
}
