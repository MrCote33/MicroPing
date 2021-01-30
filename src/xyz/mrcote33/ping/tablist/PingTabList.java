package xyz.mrcote33.ping.tablist;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.mrcote33.ping.MicroPing;
import xyz.mrcote33.ping.utils.PingUtil;

public class PingTabList extends BukkitRunnable {
	
	private MicroPing plugin;
	
	public PingTabList(final MicroPing plugin) {
		this.plugin = plugin;
	}
	
	public void run() {
		
		for(final Player player : this.plugin.getServer().getOnlinePlayers()) {
			
			String currentName = player.getName();
			final String prefix = this.plugin.getConfig().getString("tablist.prefix");
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', prefix.replace("%ping%", "" + PingUtil.getPing(player))) + " " + currentName + " ");
			
		}
		
	}

}
