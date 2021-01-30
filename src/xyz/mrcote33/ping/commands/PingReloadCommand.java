package xyz.mrcote33.ping.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import xyz.mrcote33.ping.MicroPing;

public class PingReloadCommand implements CommandExecutor{
	
	private MicroPing plugin;
	
	public PingReloadCommand(final MicroPing plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(final CommandSender sender, final Command c, final String label, final String[] args) {
		
		if(!sender.hasPermission("microping.reload")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("permission-system.no-perm-message")));
            return true;
		}
		
		this.plugin.reload();
		
		if(sender instanceof Player) {
			sender.sendMessage(ChatColor.GREEN + "Plugin reloaded.");
		}
		
		return true;
		
	}

}
