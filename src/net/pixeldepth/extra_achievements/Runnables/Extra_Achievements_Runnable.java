package net.pixeldepth.extra_achievements.Runnables;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.util.Collection;

public final class Extra_Achievements_Runnable implements Runnable {

	private final Extra_Achievements plugin;
	private final Collection <? extends Player> players;

	public Extra_Achievements_Runnable(Extra_Achievements plugin, Collection <? extends Player> players){
		this.plugin = plugin;
		this.players = players;
	}

	@Override
	public void run(){
		for(Player player : this.players){
			String uuid = player.getUniqueId().toString();

			if(this.plugin.player_data.data_exists(uuid)){
				JSONObject data = plugin.data.get(uuid);

				this.monitor_armour(data, player);
				this.increment_alive_ticks(data, player);
			}
		}
	}

	private void monitor_armour(JSONObject data, Player player){
		if(!data.containsKey("all_diamond_armour")){
			ItemStack[] armour = player.getInventory().getArmorContents();

			if(armour[3].getType() == Material.DIAMOND_HELMET && armour[2].getType() == Material.DIAMOND_CHESTPLATE && armour[1].getType() == Material.DIAMOND_LEGGINGS && armour[0].getType() == Material.DIAMOND_BOOTS){
				this.plugin.achievements.check("all_diamond_armour", player);
			}
		}
	}

	private void increment_alive_ticks(JSONObject data, Player player){
		long current_alive_ticks = 0;
		long tps = Math.round(MinecraftServer.getServer().recentTps[0]);

		if(data.containsKey("current_alive_ticks")){
			current_alive_ticks = (Long) data.get("current_alive_ticks");
		}

		// Add to the current alive value.
		// We get the server tps as we can't assume we are at 20

		long ticks_to_add = this.plugin.config.getLong("runnable_period") * tps;

		data.put("current_alive_ticks", current_alive_ticks + ticks_to_add);

		// While we are here, lets do a quick check if the current is a new best
		// If it's better then keep it up to date.  This saves us having to do it
		// in the quit / kick events

		long best_alive_ticks = 0;

		if(data.containsKey("best_alive_ticks")){
			best_alive_ticks = (long) data.get("best_alive_ticks");
		}

		if((current_alive_ticks + ticks_to_add) > best_alive_ticks){
			data.put("best_alive_ticks", (current_alive_ticks + ticks_to_add));
		}

		// Now let's see if the player has earned any achievements

		this.plugin.achievements.check("days_alive", player);
	}

}
