package com.darksoldier1404.dll.events.blocks.player;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;

public class PlayerBlockCanBuild implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerBlockPlace(BlockCanBuildEvent e) {
        Player p = e.getPlayer();
        YamlConfiguration data = plugin.udata.get(p.getUniqueId());
        if (data == null) return;
        String now = DLLFunction.getNow();
        Block b = e.getBlock();
        Bukkit.getScheduler().runTask(plugin, () -> {
        data.set(now + ".BlockCanBuildEvent.location", b.getLocation());
        data.set(now + ".BlockCanBuildEvent.data", e.getBlockData());
        data.set(now + ".BlockCanBuildEvent.type", b.getType().toString());
        data.set(now + ".BlockCanBuildEvent.material", e.getMaterial());
        });
    }
}
