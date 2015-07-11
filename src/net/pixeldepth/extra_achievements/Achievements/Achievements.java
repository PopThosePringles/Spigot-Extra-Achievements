package net.pixeldepth.extra_achievements.Achievements;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.entity.Player;

public final class Achievements {

	private final Extra_Achievements plugin;

	public Achievements(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	public void check(String key, Player player){
		String uuid = player.getUniqueId().toString();

		switch(key){

			case "mined_stone_cobblestone" :
				new Mined_Stone_Cobblestone(this.plugin, player, uuid);
				break;

			case "fish_caught" :
				new Fish_Caught(this.plugin, player, uuid);
				break;

			case "tamed_horses" :
				new Tamed_Horses(this.plugin, player, uuid);
				break;

			case "placed_block" :
				new Placed_Block(this.plugin, player, uuid);
				break;

			case "killed_mob" :
				new Killed_Mob(this.plugin, player, uuid);
				break;

			case "fall_distance" :
				new Fall_Distance(this.plugin, player, uuid);
				break;

			case "all_diamond_armour" :
				new Diamond_Armour(this.plugin, player, uuid);
				break;

			case "days_alive" :
				new Days_Alive(this.plugin, player, uuid);
				break;

			case "music_disc" :
				new Music_Disc(this.plugin, player, uuid);
				break;

		}
	}

}
