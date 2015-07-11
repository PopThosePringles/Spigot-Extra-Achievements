package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Tamed_Horses {

	public Tamed_Horses(Extra_Achievements plugin, Player player, String uuid){
		long amount = 0;

		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(data.containsKey("tamed_horse_amount")){
				amount = (long) data.get("tamed_horse_amount");
			}

			amount ++;

			if(amount >= 5 && !data.containsKey("tamed_horse_5")){
				data.put("tamed_horse_5", 1);
				plugin.announce.achievement(plugin.config.getString("tamed_horse_5_title"), plugin.config.getString("tamed_horse_5_desc"), player);
			}

			if(amount >= 10 && !data.containsKey("tamed_horse_10")){
				data.put("tamed_horse_10", 1);
				plugin.announce.achievement(plugin.config.getString("tamed_horse_10_title"), plugin.config.getString("tamed_horse_10_desc"), player);
			}

			if(amount >= 75 && !data.containsKey("tamed_horse_75")){
				data.put("tamed_horse_75", 1);
				plugin.announce.achievement(plugin.config.getString("tamed_horse_75_title"), plugin.config.getString("tamed_horse_75_desc"), player);
			}

			if(amount >= 150 && !data.containsKey("tamed_horse_150")){
				data.put("tamed_horse_150", 1);
				plugin.announce.achievement(plugin.config.getString("tamed_horse_150_title"), plugin.config.getString("tamed_horse_150_desc"), player);
			}

			if(amount >= 500 && !data.containsKey("tamed_horse_500")){
				data.put("tamed_horse_500", 1);
				plugin.announce.achievement(plugin.config.getString("tamed_horse_500_title"), plugin.config.getString("tamed_horse_500_desc"), player);
			}

			data.put("tamed_horse_amount", amount);
		}
	}

}
