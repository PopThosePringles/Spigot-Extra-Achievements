package net.pixeldepth.extra_achievements.Listeners;

import net.pixeldepth.extra_achievements.Extra_Achievements;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

// Armour checking is done, but only basic checks, it's easier to use a task.
// Check main class for the runnable.

public final class Inventory_Listeners implements Listener {

	private final Extra_Achievements plugin;

	public Inventory_Listeners(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void on_interact(PlayerInteractEvent event){
		Player player = event.getPlayer();

		if(player == null){
			return;
		}

		ItemStack[] armour = player.getInventory().getArmorContents();

		if(this.is_diamond_armour(armour)){
			this.plugin.achievements.check("all_diamond_armour", player);
		}
	}

	//@EventHandler
	public void on_click(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();

		if(player == null){
			return;
		}

		ItemStack armour[] = player.getInventory().getArmorContents();
		ItemStack cursor = event.getCursor();

		if(event.getSlotType() == InventoryType.SlotType.ARMOR){
			if(this.is_diamond_armour(armour)){
				this.plugin.achievements.check("all_diamond_armour", player);
			}
		}
	}

	@EventHandler
	public void on_close(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();

		if(player == null){
			return;
		}

		ItemStack armour[] = player.getInventory().getArmorContents();

		if(this.is_diamond_armour(armour)){
			this.plugin.achievements.check("all_diamond_armour", player);
		}
	}

	private boolean is_diamond_armour(ItemStack[] contents){
		if(contents[3].getType() == Material.DIAMOND_HELMET && contents[2].getType() == Material.DIAMOND_CHESTPLATE && contents[1].getType() == Material.DIAMOND_LEGGINGS && contents[0].getType() == Material.DIAMOND_BOOTS){
			return true;
		}

		return false;
	}

}
