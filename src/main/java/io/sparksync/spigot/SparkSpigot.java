package io.sparksync.spigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.sparksync.spigot.listeners.ProfileListeners;

public class SparkSpigot extends JavaPlugin {
	
	private static SparkSpigot instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		registerListeners();
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new ProfileListeners(), this);
	}
	
	public static SparkSpigot getInstance() {
		return instance;
	}
}
