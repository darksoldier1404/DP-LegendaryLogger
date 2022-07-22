package com.darksoldier1404.dll.events.blocks.player;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageAbortEvent;

public class PlayerBlockDamageAbort implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerBlockPlace(BlockDamageAbortEvent e) {
        Player p = e.getPlayer();
        YamlConfiguration data = plugin.udata.get(p.getUniqueId());
        if (data == null) return;
        String now = DLLFunction.getNow();
        Block b = e.getBlock();
        Bukkit.getScheduler().runTask(plugin, () -> {
        data.set(now + ".BlockDamageAbortEvent.location", b.getLocation());
        data.set(now + ".BlockDamageAbortEvent.type", b.getType().toString());
        data.set(now + ".BlockDamageAbortEvent.itemInHand", e.getItemInHand());
        });
    }
}
