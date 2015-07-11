package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;

import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Killed_Mob {

	public Killed_Mob(Extra_Achievements plugin, Player player, String uuid){
		long amount = 0;

		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(data.containsKey("killed_mob_amount")){
				amount = (long) data.get("killed_mob_amount");
			}

			amount ++;

			if(amount >= 50 && !data.containsKey("killed_mob_50")){
				data.put("killed_mob_50", 1);
				plugin.announce.achievement(plugin.config.getString("killed_mob_50_title"), plugin.config.getString("killed_mob_50_desc"), player);
			}

			if(amount >= 100 && !data.containsKey("killed_mob_100")){
				data.put("killed_mob_100", 1);
				plugin.announce.achievement(plugin.config.getString("killed_mob_100_title"), plugin.config.getString("killed_mob_100_desc"), player);
			}

			if(amount >= 1000 && !data.containsKey("killed_mob_1000")){
				data.put("killed_mob_1000", 1);
				plugin.announce.achievement(plugin.config.getString("killed_mob_1000_title"), plugin.config.getString("killed_mob_1000_desc"), player);
			}

			if(amount >= 10000 && !data.containsKey("killed_mob_10000")){
				data.put("killed_mob_10000", 1);
				plugin.announce.achievement(plugin.config.getString("killed_mob_10000_title"), plugin.config.getString("killed_mob_10000_desc"), player);
			}

			if(amount >= 100000 && !data.containsKey("killed_mob_100000")){
				data.put("killed_mob_100000", 1);
				plugin.announce.achievement(plugin.config.getString("killed_mob_100000_title"), plugin.config.getString("killed_mob_100000_desc"), player);
			}

			data.put("killed_mob_amount", amount);
		}
	}

}