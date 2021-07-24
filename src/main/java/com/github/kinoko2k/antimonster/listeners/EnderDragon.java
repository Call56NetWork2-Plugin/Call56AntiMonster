package com.github.kinoko2k.antimonster.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

@SuppressWarnings("unused")
public class EnderDragon implements Listener {

    // allowed worlds
    private final Set<String> worlds;

    public EnderDragon(Set<String> worlds) {
        this.worlds = worlds;
    }

    @EventHandler
    public void BlockPlace(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (e.getMaterial() != Material.END_CRYSTAL) {
            return;
        }
        if (e.getClickedBlock().getType() != Material.BEDROCK) {
            return;
        }

        Player player = e.getPlayer();
        World world = player.getWorld();
        if (world.getEnvironment() != Environment.THE_END) {
            return;
        }
        if (worlds.contains(world.getName())) {
            return;
        }
        if (player.hasPermission("antimonster.allowdragon")) {
            return;
        }
        /* StringBuilder b = new StringBuilder("エンダードラゴンの召喚が抑止されました。"); **/
        e.setCancelled(true);
    }
}
