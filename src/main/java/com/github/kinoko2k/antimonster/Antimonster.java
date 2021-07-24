package com.github.kinoko2k.antimonster;

import com.github.kinoko2k.antimonster.listeners.EnderDragon;
import com.github.kinoko2k.antimonster.listeners.Wither;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class Antimonster extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();

        FileConfiguration conf = getConfig();

        if (conf.getBoolean("WitherMonster.Enable", false)) {
            getServer().getPluginManager().registerEvents(new Wither(new HashSet<>(conf.getStringList("Wither.Allow"))), this);
        }

        if (conf.getBoolean("EnderDragonMonster.Enable", false)) {
            getServer().getPluginManager().registerEvents(new EnderDragon(new HashSet<>(conf.getStringList("EnderDragon.Allow"))), this);
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(ChatColor.RED + "=========" + ChatColor.WHITE + " ウィザー召喚防止Plugin " + ChatColor.RED + "=========");
            sender.sendMessage("/ウィザー召喚防止Plugin reload - Reload config.");
            return true;
        }

        if (!sender.hasPermission("antimonster.reload")) {
            sender.sendMessage("[ウィザー召喚防止Plugin] " + ChatColor.RED + "あなたに実行する権限はありません！");
            return true;
        }

        onDisable();
        onEnable();
        sender.sendMessage("[ウィザー召喚防止Plugin] config.ymlをReloadしました。");
        return true;
    }
}