package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Fall_Distance {

	public Fall_Distance(Extra_Achievements plugin, Player player, String uuid){
		double distance = 0;

		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(data.containsKey("best_fall_distance")){
				distance = (double) data.get("best_fall_distance");
			}

			if(distance >= 200 && !data.containsKey("fallen_200")){
				data.put("fallen_200", 1);
				plugin.announce.achievement(plugin.config.getString("fallen_200_title"), plugin.config.getString("fallen_200_desc"), player);
			}
		}
	}

}
