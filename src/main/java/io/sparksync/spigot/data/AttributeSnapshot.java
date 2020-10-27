package io.sparksync.spigot.data;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONObject;

import lombok.Getter;

@Getter
public class AttributeSnapshot {
	
	private Player player;
	
	private float experienceProgress;
	private int experienceLevel;
	private boolean allowFlight;
	private boolean isFlying;
	private float flySpeed;
	private float exhaustion;
	private int foodLevel;
	private double healthScale;
	private boolean isHealthScaled;
	private float saturation;
	private float walkSpeed;
	private GameMode gamemode;
	private Collection<PotionEffect> potionEffects;
	
	public AttributeSnapshot(Player player) {
		this.player = player;
		
		this.experienceProgress = player.getExp();
		this.experienceLevel = player.getTotalExperience();		
		this.allowFlight = player.getAllowFlight();
		this.isFlying = player.isFlying();
		this.flySpeed = player.getFlySpeed();
		this.exhaustion = player.getExhaustion();
		this.foodLevel = player.getFoodLevel();
		this.healthScale = player.getHealthScale();
		this.isHealthScaled = player.isHealthScaled();
		this.saturation = player.getSaturation();
		this.walkSpeed = player.getWalkSpeed();
		this.gamemode = player.getGameMode();
		this.potionEffects = player.getActivePotionEffects();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJson() {
		 JSONObject attribute = new JSONObject();
		 
		 attribute.put("experienceProgress", experienceProgress);
		 attribute.put("experienceLevel", experienceLevel);
		 attribute.put("allowFlight", allowFlight);
		 attribute.put("isFlying", isFlying);
		 attribute.put("flySpeed", flySpeed);
		 attribute.put("exhaustion", exhaustion);
		 attribute.put("foodLevel", foodLevel);
		 attribute.put("healthScale", healthScale);
		 attribute.put("isHealthScaled", isHealthScaled);
		 attribute.put("saturation", saturation);
		 attribute.put("walkSpeed", walkSpeed);
		 attribute.put("gamemode", gamemode);
		 attribute.put("potionEffects", potionEffects);
		 
		 return attribute;
	}
}
