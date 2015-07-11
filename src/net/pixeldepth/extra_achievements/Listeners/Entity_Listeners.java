package net.pixeldepth.extra_achievements.Listeners;

import net.pixeldepth.extra_achievements.Extra_Achievements;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Entity_Listeners implements Listener {

	private final Extra_Achievements plugin;

	public Entity_Listeners(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void on_tame(EntityTameEvent event){
		if(event.getEntity().getType().equals(EntityType.HORSE)){
			this.plugin.achievements.check("tamed_horses", (Player) event.getOwner());
		}
	}

	@EventHandler
	public void on_death(EntityDeathEvent event){
		if(event.getEntity() instanceof Monster){
			Monster monster = (Monster) event.getEntity();
			Player killer = monster.getKiller();

			if(killer != null){
				this.plugin.achievements.check("killed_mob", killer);
			}

			// Check to see if it's a creeper and if a music disc dropped

			if(monster.getType() == EntityType.CREEPER){
				List <ItemStack> drops = event.getDrops();

				for(ItemStack item : drops){
					if(item.getType().isRecord()){
						ItemMeta meta = item.getItemMeta();

						meta.setLore(Arrays.asList("Dropped by a Creeper"));
						item.setItemMeta(meta);
					}
				}
			}
		} else if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			String uuid = player.getUniqueId().toString();
			JSONObject data = this.plugin.data.get(uuid);

			// So player has died and we need to reset their
			// current alive ticks, but first we need to see
			// if it's a record

			long current_ticks_alive = (data.containsKey("current_alive_ticks"))? (Long) data.get("current_alive_ticks") : 0;
			long best_ticks_alive = (data.containsKey("best_alive_ticks"))? (Long) data.get("best_alive_ticks") : 0;

			if(current_ticks_alive > best_ticks_alive){
				data.put("best_alive_ticks", current_ticks_alive);
			}

			// Now reset current ticks alive for the player

			data.put("current_alive_ticks", 0);
		}
	}

}
