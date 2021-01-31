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
		
		if(this.plugin.getConfig().getBoolean("tablist.justify")) {
			
			int maxPing = 0;
			
			for(final Player player : this.plugin.getServer().getOnlinePlayers()) {
				
				if(maxPing < PingUtil.getPing(player)) {
					maxPing = PingUtil.getPing(player);
				}
				
			}
			
			for(final Player player : this.plugin.getServer().getOnlinePlayers()) {
				
				String currentName = player.getName();
				String playerPing = "";
				final String prefix = this.plugin.getConfig().getString("tablist.prefix");
				
				if(Integer.toString(maxPing).length() == Integer.toString(PingUtil.getPing(player)).length()) {
					
					playerPing = Integer.toString(PingUtil.getPing(player));
					
				} else {
					
					int difference = Integer.toString(maxPing).length() - Integer.toString(PingUtil.getPing(player)).length();
					
					for(int repeatChar = 0; repeatChar < difference; repeatChar++) {
						playerPing = playerPing + "0";
					}
					
					playerPing = playerPing + Integer.toString(PingUtil.getPing(player));
					
				}
				
				player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', prefix.replace("%ping%", "" + playerPing)) + " " + currentName + " ");
				
			}
			
		} else {
			
			for(final Player player : this.plugin.getServer().getOnlinePlayers()) {
				
				String currentName = player.getName();
				final String prefix = this.plugin.getConfig().getString("tablist.prefix");
	            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', prefix.replace("%ping%", "" + PingUtil.getPing(player))) + " " + currentName + " ");
				
			}
			
		}
		
	}

}
