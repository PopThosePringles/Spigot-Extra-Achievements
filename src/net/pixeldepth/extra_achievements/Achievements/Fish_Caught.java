package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Fish_Caught {

	public Fish_Caught(Extra_Achievements plugin, Player player, String uuid){
		long amount = 0;

		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(data.containsKey("fish_caught_amount")){
				amount = (long) data.get("fish_caught_amount");
			}

			amount ++;

			if(amount >= 50 && !data.containsKey("fish_caught_50")){
				data.put("fish_caught_50", 1);
				plugin.announce.achievement(plugin.config.getString("fish_caught_50_title"), plugin.config.getString("fish_caught_50_desc"), player);
			}

			if(amount >= 100 && !data.containsKey("fish_caught_100")){
				data.put("fish_caught_100", 1);
				plugin.announce.achievement(plugin.config.getString("fish_caught_100_title"), plugin.config.getString("fish_caught_100_desc"), player);
			}

			if(amount >= 1000 && !data.containsKey("fish_caught_1000")){
				data.put("fish_caught_1000", 1);
				plugin.announce.achievement(plugin.config.getString("fish_caught_1000_title"), plugin.config.getString("fish_caught_1000_desc"), player);
			}

			if(amount >= 5000 && !data.containsKey("fish_caught_5000")){
				data.put("fish_caught_5000", 1);
				plugin.announce.achievement(plugin.config.getString("fish_caught_5000_title"), plugin.config.getString("fish_caught_5000_desc"), player);
			}

			if(amount >= 10000 && !data.containsKey("fish_caught_10000")){
				data.put("fish_caught_10000", 1);
				plugin.announce.achievement(plugin.config.getString("fish_caught_10000_title"), plugin.config.getString("fish_caught_10000_desc"), player);
			}

			data.put("fish_caught_amount", amount);
		}
	}

}
