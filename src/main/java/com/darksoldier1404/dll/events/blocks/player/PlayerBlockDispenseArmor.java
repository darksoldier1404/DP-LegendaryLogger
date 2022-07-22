package com.darksoldier1404.dll.events.blocks.player;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;

public class PlayerBlockDispenseArmor implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerBlockPlace(BlockDispenseArmorEvent e) {
        if(e.getTargetEntity() instanceof Player) {
            Player p = (Player) e.getTargetEntity();
            YamlConfiguration data = plugin.udata.get(p.getUniqueId());
            if (data == null) return;
            String now = DLLFunction.getNow();
            Block b = e.getBlock();
            Bukkit.getScheduler().runTask(plugin, () -> {
                data.set(now + ".BlockDispenseArmorEvent.location", b.getLocation());
                data.set(now + ".BlockDispenseArmorEvent.type", b.getType().toString());
                data.set(now + ".BlockDispenseArmorEvent.uuid", e.getTargetEntity().getUniqueId());
                data.set(now + ".BlockDispenseArmorEvent.item", e.getItem());
                data.set(now + ".BlockDispenseArmorEvent.vector", e.getVelocity());
            });
        }
    }
}
