package io.sparksync.spigot.profile;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import io.sparksync.spigot.util.Base64Util;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerProfile {

	private static Map<UUID, PlayerProfile> profileMap = new HashMap<UUID, PlayerProfile>();

	private UUID uuid;
	private ItemStack[] inventory, armor;
	private byte[] rawInventory;

	public PlayerProfile(UUID uuid) {
		this.uuid = uuid;

		profileMap.put(uuid, this);
	}
	
    public PlayerProfile(Player player) {
        this(player.getUniqueId());
    }
	
	//TODO: Check the performance between caching and calling Bukkit.getPlayer every time.
	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public UUID getUUID() {
		return uuid;
	}
	
	public void clearInventory() {
		Player player = Bukkit.getPlayer(uuid);
		
		player.getInventory().clear();
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		
		player.updateInventory();
	}
	
	public void storeInventory() {
		Player player = Bukkit.getPlayer(uuid);
		
		inventory = player.getInventory().getContents();
		armor = player.getInventory().getArmorContents();
		
		MessagePack msgpack = new MessagePack();
		
		try {
			rawInventory = msgpack.write(Base64Util.itemStackArrayToBase64(inventory));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void giveInventory() {
		MessagePack msgpack = new MessagePack();
		Player player = Bukkit.getPlayer(uuid);
		
		clearInventory();
		
		if (rawInventory == null) {
			return;
		}
		
		try {
			player.getInventory().setContents(Base64Util.itemStackArrayFromBase64(msgpack.read(rawInventory, Templates.TString)));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player.getInventory().setArmorContents(armor);
		
		player.updateInventory();
	}
	
	public static PlayerProfile getByUuid(UUID uuid) {
		if (profileMap.containsKey(uuid)) {
			return profileMap.get(uuid);
		}
		return new PlayerProfile(uuid);
	}

	public static Map<UUID, PlayerProfile> getProfileMap() {
		return profileMap;
	}

	public static Set<PlayerProfile> getProfiles() {
		return new HashSet<>(profileMap.values());
	}

}
