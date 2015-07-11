package net.pixeldepth.extra_achievements.Listeners;

import net.pixeldepth.extra_achievements.Extra_Achievements;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;

import java.util.List;

public final class Player_Listeners implements Listener {

	private final Extra_Achievements plugin;

	public Player_Listeners(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void on_join(PlayerJoinEvent event){
		Player player = event.getPlayer();

		this.plugin.player_data.load(player);
	}

	@EventHandler
	public void on_quit(PlayerQuitEvent event){
		this.plugin.player_data.save(event.getPlayer());
		this.plugin.player_data.remove(event.getPlayer());
	}

	@EventHandler
	public void on_kick(PlayerKickEvent event){
		this.plugin.player_data.save(event.getPlayer());
		this.plugin.player_data.remove(event.getPlayer());
	}

	@EventHandler
	public void on_fish(PlayerFishEvent event){
		if(event.getCaught() != null && event.getState() == PlayerFishEvent.State.CAUGHT_FISH){
			Item item = (Item) event.getCaught();

			if(item.getItemStack().getType().equals(Material.RAW_FISH)){
				this.plugin.achievements.check("fish_caught", event.getPlayer());
			}
		}
	}

	@EventHandler
	public void on_move(PlayerMoveEvent event){

		// @TODO: Move this into a class.  Possibly restructure the achievement classes?

		Entity entity = event.getPlayer();

		if(entity == null){
			return;
		}

		String uuid = event.getPlayer().getUniqueId().toString();
		JSONObject session_data = this.plugin.session_data.get(uuid);

		if(!entity.isDead()){
			if(this.plugin.player_data.data_exists(uuid)){
				JSONObject data = this.plugin.data.get(uuid);

				double current_y = event.getPlayer().getLocation().getY();
				double last_y;

				if(!session_data.containsKey("last_y")){
					session_data.put("last_y", current_y);
				}

				if(entity.isOnGround()){
					last_y = (Double) session_data.get("last_y");
					Double distance = last_y - current_y;

					if(last_y > current_y){
						if(distance >= 1){
							double old_distance = (data.containsKey("best_fall_distance"))? (Double) data.get("best_fall_distance") : 0;

							if(distance > old_distance){
								data.put("best_fall_distance", distance);
							}

							this.plugin.achievements.check("fall_distance", event.getPlayer());
						}
					}

					session_data.put("last_y", event.getPlayer().getLocation().getY());
				}
			}
		} else {
			session_data.remove("last_y");
		}
	}

	@EventHandler
	public void pickup_item(PlayerPickupItemEvent event){
		Player player = event.getPlayer();
		ItemStack item = event.getItem().getItemStack();

		if(item.getType().isRecord()){
			ItemMeta meta = item.getItemMeta();
			List <String> lore = meta.getLore();
			boolean has_creeper_record = false;

			if(lore != null && lore.size() > 0){
				for(String entry : lore){
					if(entry == "Dropped by a Creeper"){
						has_creeper_record = true;
						break;
					}
				}
			}

			// Need to remove lore
			// Lore is added to records dropped by creepers, this stops players from
			// passing records around, or dropping a record to get the achievement.

			if(has_creeper_record){
				this.plugin.achievements.check("music_disc", player);

				meta.setLore(null);
				item.setItemMeta(meta);
			}
		}
	}

}