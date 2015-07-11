package net.pixeldepth.extra_achievements.Listeners;

import net.pixeldepth.extra_achievements.Extra_Achievements;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public final class Block_Listeners implements Listener {

	private final Extra_Achievements plugin;

	public Block_Listeners(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void on_block_break(BlockBreakEvent event){
		Player player = event.getPlayer();

		if(player == null){
			return;
		}

		Material in_hand = player.getItemInHand().getType();

		Boolean pick_used = (in_hand == Material.WOOD_PICKAXE || in_hand == Material.STONE_PICKAXE || in_hand == Material.IRON_PICKAXE || in_hand == Material.GOLD_PICKAXE || in_hand == Material.DIAMOND_PICKAXE);

		if((event.getBlock().getType() == Material.COBBLESTONE || event.getBlock().getType() == Material.STONE || event.getBlock().getType() == Material.MOSSY_COBBLESTONE) && pick_used){
			this.plugin.achievements.check("mined_stone_cobblestone", event.getPlayer());
		}
	}

	@EventHandler
	public void on_block_place(BlockPlaceEvent event){
		this.plugin.achievements.check("placed_block", event.getPlayer());
	}

}
