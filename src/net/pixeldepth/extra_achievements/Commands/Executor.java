package net.pixeldepth.extra_achievements.Commands;

import net.pixeldepth.extra_achievements.Extra_Achievements;
import net.pixeldepth.extra_achievements.items.Written_Book;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {

	private final Extra_Achievements plugin;

	public Executor(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			String uuid = player.getUniqueId().toString();

			if(this.plugin.player_data.data_exists(uuid)){
				new Written_Book(player, uuid, this.plugin.data.get(uuid));
			}
		}

		return false;
	}

}
