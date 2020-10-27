package io.sparksync.spigot.listener;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import io.sparksync.spigot.SparkSpigot;
import io.sparksync.spigot.data.AttributeSnapshot;
import io.sparksync.spigot.data.InventorySnapshot;
import io.sparksync.spigot.profile.SparkProfile;
import io.sparksync.spigot.util.ByteUtils;

public class ProfileListener implements Listener {

	private static SparkSpigot instance = SparkSpigot.getInstance();

	@EventHandler
	public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
		Player player = Bukkit.getPlayer(event.getUniqueId());

		SparkProfile profile = new SparkProfile(event.getUniqueId());
	}

	//ALL TEST LOGIC
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
		Player player = event.getPlayer();
		
		SparkProfile profile = SparkProfile.getByUuid(player.getUniqueId());

		profile.setInventorySnapshot(new InventorySnapshot(player.getInventory()));
		profile.setAttributeSnapshot(new AttributeSnapshot(player));
		
		byte[] test = ByteUtils.itemStackToByte(profile.getInventorySnapshot().getContents());
		
		ItemStack[] test1 = ByteUtils.itemStackFromByte(test);
		
		player.sendMessage(test.toString());

		for (ItemStack item : test1) {
			if (item != null) {
				player.sendMessage("" + item.getType().toString());
			}
		}
		
		for (PotionEffect effect : profile.getAttributeSnapshot().getPotionEffects()) {
			player.sendMessage(effect.toString());
		}
		
		System.out.println("!!!" + ByteUtils.jsonObjectToByte(profile.getAttributeSnapshot().toJson()));
		System.out.println("!!!!!" + ByteUtils.jsonObjectFromByte(ByteUtils.jsonObjectToByte(profile.getAttributeSnapshot().toJson())));
	}
}
