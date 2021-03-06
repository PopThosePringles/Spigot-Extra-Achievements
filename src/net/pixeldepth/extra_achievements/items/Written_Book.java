package net.pixeldepth.extra_achievements.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.json.simple.JSONObject;

/**
 * This class will handle creating a written book for the player so that they
 * can view all their achievements, including vanilla (note to self: add config option for that).
 */

public final class Written_Book {

	public Written_Book(Player player, String uuid, JSONObject data){
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();

		meta.setTitle("Achievements");
		meta.setAuthor("The Achievements Collector"); // Change to something better later

		// Test

		meta.addPage("apples", "pears");

		book.setItemMeta(meta);

		player.getInventory().addItem(book);
	}

}
