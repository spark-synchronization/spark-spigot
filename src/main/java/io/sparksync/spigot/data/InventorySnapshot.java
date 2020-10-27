package io.sparksync.spigot.data;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import lombok.Getter;

@Getter
public class InventorySnapshot {
	
	private PlayerInventory inventory;
	
	private ItemStack[] contents;
	private ItemStack[] armor;
	
	public InventorySnapshot(PlayerInventory inventory) {
		this.inventory = inventory;
		
		this.contents = inventory.getContents();
		this.armor = inventory.getArmorContents();
	}
}
