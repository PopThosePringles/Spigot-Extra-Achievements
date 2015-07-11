package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Days_Alive {

	public Days_Alive(Extra_Achievements plugin, Player player, String uuid){
		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);
			long current_alive_ticks = 0;

			if(data.containsKey("current_alive_ticks")){
				current_alive_ticks = (long) data.get("current_alive_ticks");
			}

			int days = (int) (current_alive_ticks / 24000);

			if(days >= 1 && !data.containsKey("survived_1")){
				data.put("survived_1", 1);
				plugin.announce.achievement(plugin.config.getString("survived_1_title"), plugin.config.getString("survived_1_desc"), player);
			}

			if(days >= 10 && !data.containsKey("survived_10")){
				data.put("survived_10", 1);
				plugin.announce.achievement(plugin.config.getString("survived_10_title"), plugin.config.getString("survived_10_desc"), player);
			}

			if(days >= 100 && !data.containsKey("survived_100")){
				data.put("survived_100", 1);
				plugin.announce.achievement(plugin.config.getString("survived_100_title"), plugin.config.getString("survived_100_desc"), player);
			}

			if(days >= 1000 && !data.containsKey("survived_1000")){
				data.put("survived_1000", 1);
				plugin.announce.achievement(plugin.config.getString("survived_1000_title"), plugin.config.getString("survived_1000_desc"), player);
			}
		}
	}

}
