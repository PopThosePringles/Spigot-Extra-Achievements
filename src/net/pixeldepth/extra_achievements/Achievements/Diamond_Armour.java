package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Diamond_Armour {

	public Diamond_Armour(Extra_Achievements plugin, Player player, String uuid){
		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(!data.containsKey("all_diamond_armour")){
				data.put("all_diamond_armour", 1);
				plugin.announce.achievement(plugin.config.getString("all_diamond_armour_title"), plugin.config.getString("all_diamond_armour_desc"), player);
			}
		}
	}

}
