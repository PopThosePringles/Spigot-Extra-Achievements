package net.pixeldepth.extra_achievements.Utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.pixeldepth.extra_achievements.Extra_Achievements;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.StringTokenizer;

public final class Announce {

	private final Extra_Achievements plugin;

	public Announce(Extra_Achievements plugin){
		this.plugin = plugin;
	}

	public void achievement(String title, String desc, Player player) {
		boolean broadcast_to_all = (this.plugin.getConfig().getBoolean("broadcast_to_all"))? true : false;

		String broken_desc = this.break_long_descriptions(desc);
		String to_user = (broadcast_to_all)? player.getDisplayName() + " has just" : "You have";

		String json = "[{text:\"" + to_user + " earned the achievement \", extra: [{text: \"[" + title + "]\", color: \"green\", hoverEvent: {action: show_text, value: [{text: \"" + title + "\", color: \"green\"}, {text: \"\\n" + broken_desc + "\"}]}}]}]";

		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a(json);
		PacketPlayOutChat packet = new PacketPlayOutChat(chat);

		if(broadcast_to_all){
			for(Player p : Bukkit.getOnlinePlayers()){
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
		} else {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
	}

	private String break_long_descriptions(String desc){
		StringTokenizer tok = new StringTokenizer(desc, " ");
		StringBuilder output = new StringBuilder(desc.length());

		int len = 0;

		while(tok.hasMoreTokens()){
			String word = tok.nextToken();

			if(len + word.length() > 30) {
				output.append("\n");
				len = 0;
			}

			output.append(word + " ");
			len += word.length();
		}

		return output.toString();
	}

}
