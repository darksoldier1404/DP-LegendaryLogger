package com.darksoldier1404.dll.events.blocks.world;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCookEvent;

@SuppressWarnings("all")
public class WorldBlockCook implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerBlockPlace(BlockCookEvent e) {
        YamlConfiguration data = plugin.worldLog;
        if (data == null) return;
        String now = DLLFunction.getNow();
        Block b = e.getBlock();
        Bukkit.getScheduler().runTask(plugin, () -> {
        data.set(now + ".BlockCookEvent.location", b.getLocation());
        data.set(now + ".BlockCookEvent.type", b.getType().toString());
        data.set(now + ".BlockCookEvent.item.result", e.getResult());
        data.set(now + ".BlockCookEvent.item.source", e.getSource());
        });
    }
}
