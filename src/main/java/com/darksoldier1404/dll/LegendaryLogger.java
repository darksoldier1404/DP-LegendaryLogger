package com.darksoldier1404.dll;

import com.darksoldier1404.dll.enums.BukkitType;
import com.darksoldier1404.dll.functions.DLLFunction;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
todo 플레이어뿐이 아닌 LivingEntity를 추가로 처리해야하는 이벤트 목록
ItemDropEvent
ItemPickupEvent
BlockDispenseArmorEvent
 */
public class LegendaryLogger extends JavaPlugin {
    private static LegendaryLogger plugin;
    public YamlConfiguration config;
    public String prefix;
    public Map<UUID, YamlConfiguration> udata = new HashMap<>();
    public static YamlConfiguration worldLog;
    public static String currentWorldLogDate;
    public static BukkitType bukkitType;

    public static LegendaryLogger getInstance() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Settings.Prefix"));
        DLLFunction.initEvents();
        File path = new File(plugin.getDataFolder() + "/world_logs");
        if (!path.exists()) path.mkdir();
        currentWorldLogDate = DLLFunction.getNow();
        worldLog = ConfigUtils.createCustomData(plugin, currentWorldLogDate, "world_logs");
        if (Bukkit.getServer().getVersion().contains("Spigot")) {
            System.out.println(prefix + "This Server is Spigot Bukkit");
            bukkitType = BukkitType.SPIGOT;
        }
        if (Bukkit.getServer().getVersion().contains("CatServer")) {
            System.out.println(prefix + "This Server is CatServer(Spigot) Bukkit");
            bukkitType = BukkitType.CATSERVER;
        }
        if (Bukkit.getServer().getVersion().contains("Paper")) {
            System.out.println(prefix + "This Server is Paper Bukkit");
            bukkitType = BukkitType.PAPER;
        }
        if (Bukkit.getServer().getVersion().contains("Purpur")) {
            System.out.println(prefix + "This Server is Purpur(Paper) Bukkit");
            bukkitType = BukkitType.PURPUR;
        }
    }

    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            YamlConfiguration data = plugin.udata.get(p.getUniqueId());
            data.set(DLLFunction.getNow() + ".PlayerQuitEvent", p.getUniqueId());
            data.set(DLLFunction.getNow() + ".PlayerQuitEvent.lastLocation", p.getLocation());
            ConfigUtils.saveCustomData(plugin, data, DLLFunction.getNow().replace(':', '-'), "logs/" + p.getUniqueId());
        });
        ConfigUtils.saveCustomData(plugin, worldLog, currentWorldLogDate, "world_logs");
    }
}
