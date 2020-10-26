package io.sparksync.spigot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.sparksync.spigot.SparkSpigot;
import io.sparksync.spigot.profile.PlayerProfile;

public class ProfileListeners implements Listener {
	
	private SparkSpigot instance = SparkSpigot.getInstance();
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		PlayerProfile profile = PlayerProfile.getByUuid(event.getPlayer().getUniqueId());
		
		event.getPlayer().sendMessage("!" + profile.getUUID());
	}
	
	@EventHandler
	public void test(BlockPlaceEvent event) {
		PlayerProfile profile = PlayerProfile.getByUuid(event.getPlayer().getUniqueId());
			
		event.getPlayer().sendMessage("!" + profile.getUUID());
		
		if (event.getPlayer().isSneaking()) {
			profile.clearInventory();
		} else {
			profile.storeInventory();
		}
	}
	
	@EventHandler
	public void test(BlockBreakEvent event) {
		PlayerProfile profile = PlayerProfile.getByUuid(event.getPlayer().getUniqueId());
			
		event.getPlayer().sendMessage("!" + profile.getUUID());
		
		profile.giveInventory();
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		PlayerProfile profile = PlayerProfile.getByUuid(event.getPlayer().getUniqueId());
		
		if (profile != null) {
			PlayerProfile.getProfiles().remove(profile);
			PlayerProfile.getProfileMap().remove(profile.getUUID());
		}
	}

}
