package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public final class Music_Disc {

	public Music_Disc(Extra_Achievements plugin, Player player, String uuid){
		if(plugin.player_data.data_exists(uuid)){
			JSONObject data = plugin.data.get(uuid);

			if(!data.containsKey("music_disc")){
				data.put("music_disc", 1);
				plugin.announce.achievement(plugin.config.getString("music_disc_title"), plugin.config.getString("music_disc_desc"), player);
			}
		}
	}
}
