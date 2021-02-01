package xyz.mrcote33.ping;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.mrcote33.ping.commands.PingReloadCommand;
import xyz.mrcote33.ping.placeholder.CustomPlaceholder;
import xyz.mrcote33.ping.tablist.PingTabList;

public class MicroPing extends JavaPlugin {
	
	private static MicroPing instance;
	
	public static MicroPing getInstance() {
		return MicroPing.instance;
	}
	
	public void onEnable() {
		
		(MicroPing.instance = this).saveDefaultConfig();
		
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			new CustomPlaceholder(this).register();
		}
		
		this.getCommand("pingreload").setExecutor((CommandExecutor) new PingReloadCommand(this));
		this.registerTask();
		
	}
	
	public void onDisable() {
		
		MicroPing.instance = null;
		this.getLogger().info("Cancelling tasks...");
        this.getServer().getScheduler().cancelTasks((Plugin)this);
        
	}
	
	private void registerTask() {
		
		if(this.getConfig().getBoolean("permission-system.enabled")) {
            this.getLogger().info("The permission-system is enabled. Please check that users have proper permissions.");
        }
		
		Long delay = this.getConfig().getLong("tablist.update-delay");
		
		if(this.getConfig().getBoolean("tablist.enabled")) {
			new PingTabList(this).runTaskTimerAsynchronously((Plugin)this, delay * 20L, delay * 20L);
			this.getLogger().info("TabList is enabled, task added with a delay of " + delay + " second/s.");
		}
        
	}
	
	public void reload() {
		
		this.getLogger().info("Reloading the plugin...");
        this.getServer().getScheduler().cancelTasks((Plugin)this);
        this.reloadConfig();
        this.registerTask();
        this.getLogger().info("Plugin reloaded.");
        
	}

}
