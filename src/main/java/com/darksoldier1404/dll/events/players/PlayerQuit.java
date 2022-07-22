package com.darksoldier1404.dll.events.players;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.functions.DLLFunction;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PlayerQuit implements Listener {
    private final LegendaryLogger plugin = LegendaryLogger.getInstance();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        YamlConfiguration data = plugin.udata.get(p.getUniqueId());
        Bukkit.getScheduler().runTask(plugin, () -> {
            data.set(DLLFunction.getNow() + ".PlayerQuitEvent", p.getUniqueId());
            data.set(DLLFunction.getNow() + ".PlayerQuitEvent.lastLocation", p.getLocation());
            String now = DLLFunction.getNow();
            ConfigUtils.saveCustomData(plugin, data, now, "logs/" + p.getUniqueId());
            try {
                byte[] buf = new byte[4096];
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(plugin.getDataFolder() + "/logs/" + p.getUniqueId() + "/" + now + ".zip"));
                FileInputStream in = new FileInputStream(plugin.getDataFolder() + "/logs/" + p.getUniqueId() + "/" + now + ".yml");
                Path path = Paths.get(plugin.getDataFolder() + "/logs/" + p.getUniqueId() + "/" + now + ".yml");
                String fileName = path.getFileName().toString();
                ZipEntry ze = new ZipEntry(fileName);
                out.putNextEntry(ze);
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
                out.close();
            } catch (IOException ee) {
                ee.printStackTrace();
            }
            Path path = Paths.get(plugin.getDataFolder() + "/logs/" + p.getUniqueId() + "/" + now + ".yml");
            path.toFile().delete();
            plugin.udata.remove(p.getUniqueId());
        });
    }
}
