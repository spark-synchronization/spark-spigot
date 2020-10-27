package io.sparksync.spigot.profile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.sparksync.spigot.data.AttributeSnapshot;
import io.sparksync.spigot.data.InventorySnapshot;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SparkProfile {

	private static Map<UUID, SparkProfile> profiles = new HashMap<UUID, SparkProfile>();

	private final UUID uuid;
	private InventorySnapshot inventorySnapshot;
	private AttributeSnapshot attributeSnapshot;

	public SparkProfile(UUID uuid) {
		this.uuid = uuid;

		profiles.put(uuid, this);
	}
	
	public static SparkProfile getByUuid(UUID uuid) {
		if (profiles.containsKey(uuid)) {
			return profiles.get(uuid);
		}

		return new SparkProfile(uuid);
	}
	
	public static Set<SparkProfile> getProfiles() {
		return new HashSet<>(profiles.values());
	}
}
