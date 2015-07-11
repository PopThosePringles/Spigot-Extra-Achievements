package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Mined_Stone_Cobblestone {
	
	public Mined_Stone_Cobblestone(Extra_Achievements plugin, Player player, String uuid){
		long amount = 0;

		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(data.containsKey("stone_cobblestone_amount")){
				amount = (long) data.get("stone_cobblestone_amount");
			}

			amount ++;

			if(amount >= 1 && !data.containsKey("stone_cobblestone_1")){
				data.put("stone_cobblestone_1", 1);
				plugin.announce.achievement(plugin.config.getString("stone_cobblestone_1_title"), plugin.config.getString("stone_cobblestone_1_desc"), player);
			}

			if(amount >= 100 && !data.containsKey("stone_cobblestone_100")){
				data.put("stone_cobblestone_100", 1);
				plugin.announce.achievement(plugin.config.getString("stone_cobblestone_100_title"), plugin.config.getString("stone_cobblestone_100_desc"), player);
			}

			if(amount >= 1000 && !data.containsKey("stone_cobblestone_1000")){
				data.put("stone_cobblestone_1000", 1);
				plugin.announce.achievement(plugin.config.getString("stone_cobblestone_1000_title"), plugin.config.getString("stone_cobblestone_1000_desc"), player);
			}

			if(amount >= 10000 && !data.containsKey("stone_cobblestone_10000")){
				data.put("stone_cobblestone_10000", 1);
				plugin.announce.achievement(plugin.config.getString("stone_cobblestone_10000_title"), plugin.config.getString("stone_cobblestone_10000_desc"), player);
			}

			if(amount >= 100000 && !data.containsKey("stone_cobblestone_100000")){
				data.put("stone_cobblestone_100000", 1);
				plugin.announce.achievement(plugin.config.getString("stone_cobblestone_100000_title"), plugin.config.getString("stone_cobblestone_100000_desc"), player);
			}

			if(amount >= 1000000 && !data.containsKey("stone_cobblestone_1000000")){
				data.put("stone_cobblestone_1000000", 1);
				plugin.announce.achievement(plugin.config.getString("stone_cobblestone_1000000_title"), plugin.config.getString("stone_cobblestone_1000000_desc"), player);
			}

			data.put("stone_cobblestone_amount", amount);
		}
	}
	
}
