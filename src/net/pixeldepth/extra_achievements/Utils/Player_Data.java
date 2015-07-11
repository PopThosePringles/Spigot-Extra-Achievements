package net.pixeldepth.extra_achievements.Utils;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public final class Player_Data {

	public final Extra_Achievements plugin;

	public Player_Data(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	public void load(Player player){
		String player_uuid = player.getUniqueId().toString();
		String player_file = this.plugin.getDataFolder().getPath() + File.separator + "Players" + File.separator + player_uuid + ".json";
		Path data_file = Paths.get(player_file);

		this.setup_session(player_uuid);

		if(this.file_data_exists(data_file)){
			try {
				if(new File(player_file).length() > 0) {
					FileReader reader = new FileReader(player_file);
					JSONParser parser = new JSONParser();

					this.plugin.data.put(player_uuid, (JSONObject) parser.parse(reader));

					reader.close();
				} else {
					this.plugin.data.put(player_uuid, new JSONObject());
				}
			} catch(IOException e){
				this.plugin.warn("Couldn't read json file: " + player.getDisplayName());
			} catch(ParseException e){
				this.plugin.warn("Error parsing data: " + player.getDisplayName());
			}
		} else {

			// We assume that if there is no data file, then it's a new member, or a member that hasn't logged
			// on since the plugin was added, so we will add an empty JSON object to the hashmap.

			this.plugin.data.put(player_uuid, new JSONObject());
		}
	}

	public void save(Player player){
		String player_uuid = player.getUniqueId().toString();
		String player_file = this.plugin.getDataFolder().getPath() + File.separator + "Players" + File.separator + player_uuid + ".json";

		try {
			FileWriter writer = new FileWriter(player_file);

			if(!this.plugin.data.containsKey(player_uuid)) {
				writer.write("");
			} else {
				writer.write(this.plugin.data.get(player_uuid).toJSONString());
			}

			writer.close();
		} catch(IOException e){
			this.plugin.warn("Couldn't write json file: " + player.getDisplayName());
		}
	}

	private boolean file_data_exists(Path data_file){
		if(Files.exists(data_file)){
			return true;
		}

		return false;
	}

	// When the server restarts or shutsdown, we need to save each players data

	public void save_all(Collection<? extends Player> players){
		for(Player p : players){
			this.save(p);
		}
	}

	public boolean data_exists(String uuid){
		if(this.plugin.data.containsKey(uuid)){
			return true;
		}

		return false;
	}

	private void setup_session(String uuid){
		this.plugin.session_data.put(uuid, new JSONObject());
	}

	public void remove(Player player){
		String uuid = player.getUniqueId().toString();

		if(this.plugin.data.containsKey(uuid)){
			this.plugin.data.remove(uuid);
		}

		if(this.plugin.session_data.containsKey(uuid)){
			this.plugin.session_data.remove(uuid);
		}
	}

}
