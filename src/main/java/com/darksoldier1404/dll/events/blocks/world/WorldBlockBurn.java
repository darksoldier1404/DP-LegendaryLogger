package com.darksoldier1404.dll.events.blocks.world;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

@SuppressWarnings("all")
public class WorldBlockBurn implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerBlockPlace(BlockBurnEvent e) {
        YamlConfiguration data = plugin.worldLog;
        if (data == null) return;
        String now = DLLFunction.getNow();
        Block b = e.getBlock();
        Bukkit.getScheduler().runTask(plugin, () -> {
        data.set(now + ".BlockBurnEvent.location", b.getLocation());
        data.set(now + ".BlockBurnEvent.type", b.getType().toString());
        });
    }
}
