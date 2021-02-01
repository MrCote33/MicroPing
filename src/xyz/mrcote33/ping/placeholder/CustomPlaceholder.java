package xyz.mrcote33.ping.placeholder;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import xyz.mrcote33.ping.MicroPing;
import xyz.mrcote33.ping.utils.PingUtil;

public class CustomPlaceholder extends PlaceholderExpansion{

	private MicroPing plugin;
	
	public CustomPlaceholder(MicroPing plugin) {
		this.plugin = plugin;
	}
	
	public boolean persist() {
		return true;
	}
	
	public boolean canRegister() {
		return true;
	}
	
	public String getAuthor() {
		return plugin.getDescription().getAuthors().toString();
	}

	public String getIdentifier() {
		return plugin.getDescription().getName();
	}
	
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}
	
	public String onPlaceholderRequest(Player player, String identifier) {
		
		if(player == null) {
			return "";
		}
		
		if(identifier.equals("ping")) {
			String prefix = this.plugin.getConfig().getString("tablist.prefix");
			return ChatColor.translateAlternateColorCodes('&', prefix.replace("%ping%", "" + PingUtil.getPing(player)) + " ");
        }
		
		return null;
		
	}

}