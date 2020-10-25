package io.sparksync.spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class SparkSpigot extends JavaPlugin {
	
	private static SparkSpigot instance;
	
	@Override
	public void onEnable() {
		instance = this;
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public SparkSpigot getInstance() {
		return instance;
	}
}
