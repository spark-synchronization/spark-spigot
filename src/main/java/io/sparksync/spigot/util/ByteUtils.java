package io.sparksync.spigot.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.json.simple.JSONObject;

public class ByteUtils {
	
	public static byte[] jsonObjectToByte(JSONObject object) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

			dataOutput.writeObject(object);
			
			dataOutput.close();
			
			return outputStream.toByteArray();
			
		} catch (Exception exception) {
			throw new IllegalStateException("Unable to convert Object to byte!", exception);
		}
	}
	
	public static JSONObject jsonObjectFromByte(byte[] data) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			
			JSONObject object = (JSONObject) dataInput.readObject();
			
			dataInput.close();
			
			return object;
			
		} catch (Exception exception) {
			throw new IllegalStateException("Unable to convert Object from byte!", exception);
		}
	}

	public static byte[] itemStackToByte(ItemStack[] itemStack) throws IllegalStateException {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

			dataOutput.writeInt(itemStack.length);

			for (int i = 0; i < itemStack.length; i++) {
				dataOutput.writeObject(itemStack[i]);
			}

			dataOutput.close();

			return outputStream.toByteArray();

		} catch (Exception exception) {
			throw new IllegalStateException("Unable to convert ItemStack to byte!", exception);
		}
	}
	
	public static ItemStack[] itemStackFromByte(byte[] data) throws IOException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

			ItemStack[] items = new ItemStack[dataInput.readInt()];

			// Read the serialized inventory
			for (int i = 0; i < items.length; i++) {
				items[i] = (ItemStack) dataInput.readObject();
			}

			dataInput.close();
			
			return items;
		} catch (ClassNotFoundException e) {
			throw new IOException("Unable to convert ItemStack from byte!", e);
		}
	}
}
