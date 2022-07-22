package com.darksoldier1404.dll.events.blocks.world;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

@SuppressWarnings("all")
public class WorldBlockDispenseEvent implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerBlockPlace(BlockDispenseEvent e) {
        YamlConfiguration data = plugin.worldLog;
        if (data == null) return;
        String now = DLLFunction.getNow();
        Block b = e.getBlock();
        Bukkit.getScheduler().runTask(plugin, () -> {
        data.set(now + ".BlockDispenseEvent.location", b.getLocation());
        data.set(now + ".BlockDispenseEvent.type", b.getType().toString());
        data.set(now + ".BlockDispenseEvent.item", e.getItem());
        data.set(now + ".BlockDispenseEvent.vector", e.getVelocity());
        });
    }
}
