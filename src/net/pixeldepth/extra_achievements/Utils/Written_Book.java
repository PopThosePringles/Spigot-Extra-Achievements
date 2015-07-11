package net.pixeldepth.extra_achievements.Utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.json.simple.JSONObject;

public final class Written_Book {

	public Written_Book(Player player, String uuid, JSONObject data){
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();

		meta.setTitle("Achievements");
		meta.setAuthor("The Achievements Collector"); // Change to something better later
		meta.addPage("apples", "pears");

		book.setItemMeta(meta);

		player.getInventory().addItem(book);
	}

}
