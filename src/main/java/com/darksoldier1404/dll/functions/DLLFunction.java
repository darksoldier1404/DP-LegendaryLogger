package com.darksoldier1404.dll.functions;

import com.darksoldier1404.dll.LegendaryLogger;
import com.darksoldier1404.dll.events.blocks.player.*;
import com.darksoldier1404.dll.events.blocks.world.WorldBlockBurn;
import com.darksoldier1404.dll.events.blocks.world.WorldBlockCook;
import com.darksoldier1404.dll.events.blocks.world.WorldBlockDispenseEvent;
import com.darksoldier1404.dll.events.players.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DLLFunction {
    private static final LegendaryLogger plugin = LegendaryLogger.getInstance();
    public static long move_per_tick;

    public static String getNow() {
        return new SimpleDateFormat("yyyy년-MM월-dd일-HH시-mm분-ss초-SSS", Locale.KOREA).format(new Date());
    }
    public static void initEvents() {
        PluginManager pm = plugin.getServer().getPluginManager();
        String bp, bw, p;
        bp = "Settings.BlockEvent.Player.";
        bw = "Settings.BlockEvent.World.";
        p = "Settings.PlayerEvent.";
        move_per_tick = plugin.config.getLong("Settings.etc.PlayerMoveEvent.log_every_tick");
        if(plugin.config.getBoolean(bp + "BlockBreakEvent")) pm.registerEvents(new PlayerBlockBreak(), plugin);
        if(plugin.config.getBoolean(bp + "BlockPlaceEvent")) pm.registerEvents(new PlayerBlockPlace(), plugin);
        if(plugin.config.getBoolean(bp + "BlockCanBuildEvent")) pm.registerEvents(new PlayerBlockCanBuild(), plugin);
        if(plugin.config.getBoolean(bp + "BlockDamageAbortEvent")) pm.registerEvents(new PlayerBlockDamageAbort(), plugin);
        if(plugin.config.getBoolean(bp + "BlockDispenseArmorEvent")) pm.registerEvents(new PlayerBlockDispenseArmor(), plugin);
        if(plugin.config.getBoolean(bw + "BlockBurnEvent")) pm.registerEvents(new WorldBlockBurn(), plugin);
        if(plugin.config.getBoolean(bw + "BlockCookEvent")) pm.registerEvents(new WorldBlockCook(), plugin);
        if(plugin.config.getBoolean(bw + "BlockDispenseEvent")) pm.registerEvents(new WorldBlockDispenseEvent(), plugin);
        if(plugin.config.getBoolean(p + "PlayerChatEvent")) pm.registerEvents(new PlayerChat(), plugin);
        if(plugin.config.getBoolean(p + "PlayerCommandPreprocessEvent")) pm.registerEvents(new PlayerCommandPreProcess(), plugin);
        if(plugin.config.getBoolean(p + "PlayerCommandSendEvent")) pm.registerEvents(new PlayerCommandSend(), plugin);
        if(plugin.config.getBoolean(p + "PlayerInteractEvent")) pm.registerEvents(new PlayerInteract(), plugin);
        if(plugin.config.getBoolean(p + "PlayerInventoryClickEvent")) pm.registerEvents(new PlayerInventoryClick(), plugin);
        if(plugin.config.getBoolean(p + "PlayerInventoryCloseEvent")) pm.registerEvents(new PlayerInventoryClose(), plugin);
        if(plugin.config.getBoolean(p + "PlayerInventoryOpenEvent")) pm.registerEvents(new PlayerInventoryOpen(), plugin);
        if(plugin.config.getBoolean(p + "PlayerItemDropEvent")) pm.registerEvents(new PlayerItemDrop(), plugin);
        if(plugin.config.getBoolean(p + "PlayerItemPickupEvent")) pm.registerEvents(new PlayerItemPickup(), plugin);
        if(plugin.config.getBoolean(p + "PlayerJoinEvent")) pm.registerEvents(new PlayerJoin(), plugin);
        if(plugin.config.getBoolean(p + "PlayerQuitEvent")) pm.registerEvents(new PlayerQuit(), plugin);
        if(plugin.config.getBoolean(p + "PlayerMoveEvent")) initMove();
    }

    public static void initMove() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> Bukkit.getOnlinePlayers().forEach(p -> {
            YamlConfiguration data = plugin.udata.get(p.getUniqueId());
            if (data == null) return;
            String now = DLLFunction.getNow();
            Bukkit.getScheduler().runTask(plugin, () -> data.set(now + ".PlayerMoveEvent.location", p.getLocation()));
        }), 0L, move_per_tick);
    }
}
