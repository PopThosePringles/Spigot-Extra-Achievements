package net.pixeldepth.extra_achievements;

import net.pixeldepth.extra_achievements.Commands.Executor;
import net.pixeldepth.extra_achievements.Listeners.Entity_Listeners;
import net.pixeldepth.extra_achievements.Listeners.Inventory_Listeners;
import net.pixeldepth.extra_achievements.Runnables.Extra_Achievements_Runnable;
import net.pixeldepth.extra_achievements.Utils.Announce;
import net.pixeldepth.extra_achievements.Achievements.Achievements;
import net.pixeldepth.extra_achievements.Listeners.Block_Listeners;
import net.pixeldepth.extra_achievements.Listeners.Player_Listeners;
import net.pixeldepth.extra_achievements.Utils.Player_Data;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Logger;

public final class Extra_Achievements extends JavaPlugin {

	private String plugin_name;
	private Path players_path;

	public final FileConfiguration config = this.getConfig();
	private final Logger log = Logger.getLogger("Minecraft");

	public final Player_Data player_data = new Player_Data(this);
	public final Achievements achievements = new Achievements(this);
	public final Announce announce = new Announce(this);

	public final HashMap<String, JSONObject> data = new HashMap<>();
	public final HashMap<String, JSONObject> session_data = new HashMap<>();

	public boolean runnables_running = false;

	@Override
	public void onEnable(){
		this.plugin_name = this.getDescription().getName();

		this.saveDefaultConfig();

		if(this.config.getBoolean("enabled")){
			this.info("Enabled (From Config)");

			if(!this.getDataFolder().exists()){
				this.getDataFolder().mkdir();
			}

			// Check to see if the players folder exists, otherwise create it.

			this.players_path = Paths.get(this.getDataFolder().getPath() + File.separator + "Players");

			if(!Files.exists(this.players_path)){
				try {
					Files.createDirectory(this.players_path);
					this.info("Players directory created");
				} catch (IOException e){
					this.warn("Could not create players directory (" + this.getDataFolder().getPath() + File.separator + "Players" + ")");
				}
			} else {
				this.info("Players directory exists");
			}

			// Check again if the folder exists, as we may have had to create it, so the else above would not get called.
			// Register listener events only if the folder exists.

			if(Files.exists(this.players_path)){
				this.getServer().getPluginManager().registerEvents(new Player_Listeners(this), this);
				this.getServer().getPluginManager().registerEvents(new Block_Listeners(this), this);
				this.getServer().getPluginManager().registerEvents(new Entity_Listeners(this), this);
				this.getServer().getPluginManager().registerEvents(new Inventory_Listeners(this), this);

				this.init_waiting_runnable();
			}

			// Commands

			this.getCommand("achievements").setExecutor(new Executor(this));
		} else {
			this.info("Disabled");
		}
	}

	@Override
	public void onDisable(){
		this.player_data.save_all(Bukkit.getServer().getOnlinePlayers());
		this.info("Stopping");
	}

	public void info(String msg){
		this.log.info("[" + this.plugin_name + "]: " + msg + ".");
	}

	public void warn(String msg){
		this.log.warning("[" + this.plugin_name + "]: " + msg + ".");
	}

	public void init_waiting_runnable(){
		this.runnables_running = true;

		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		Extra_Achievements_Runnable runnable = new Extra_Achievements_Runnable(this, Bukkit.getOnlinePlayers());

		int period = this.config.getInt("runnable_period");

		// Default to 10

		if(period <= 0){
			period = 10;
		}

		long task_period = period * 20;

		scheduler.runTaskTimer(this, runnable, 0L, task_period);
	}

}
