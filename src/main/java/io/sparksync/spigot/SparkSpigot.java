package io.sparksync.spigot;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

import io.sparksync.spigot.listener.ProfileListener;

public class SparkSpigot extends JavaPlugin {
	
	private static SparkSpigot instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		Arrays.asList(new ProfileListener()).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));			
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public static SparkSpigot getInstance() {
		return instance;
	}
}
