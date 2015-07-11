package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;

import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Placed_Block {

	public Placed_Block(Extra_Achievements plugin, Player player, String uuid){
		long amount = 0;

		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(data.containsKey("placed_block_amount")){
				amount = (long) data.get("placed_block_amount");
			}

			amount ++;

			if(amount >= 100 && !data.containsKey("placed_block_100")){
				data.put("placed_block_100", 1);
				plugin.announce.achievement(plugin.config.getString("placed_block_100_title"), plugin.config.getString("placed_block_100_desc"), player);
			}

			if(amount >= 5000 && !data.containsKey("placed_block_5000")){
				data.put("placed_block_5000", 1);
				plugin.announce.achievement(plugin.config.getString("placed_block_5000_title"), plugin.config.getString("placed_block_5000_desc"), player);
			}

			if(amount >= 10000 && !data.containsKey("placed_block_10000")){
				data.put("placed_block_10000", 1);
				plugin.announce.achievement(plugin.config.getString("placed_block_10000_title"), plugin.config.getString("placed_block_10000_desc"), player);
			}

			if(amount >= 100000 && !data.containsKey("placed_block_100000")){
				data.put("placed_block_100000", 1);
				plugin.announce.achievement(plugin.config.getString("placed_block_100000_title"), plugin.config.getString("placed_block_100000_desc"), player);
			}

			data.put("placed_block_amount", amount);
		}
	}

}