package xyz.mrcote33.ping.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PingUtil {
	
	public static int getPing(Player p) {
		
		String v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		
        if (!p.getClass().getName().equals("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer")) {
            p = Bukkit.getPlayer(p.getUniqueId());
        }
        
        try {
        	
            Class<?> CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer");
            Object CraftPlayer = CraftPlayerClass.cast(p);
            Method getHandle = CraftPlayer.getClass().getMethod("getHandle", (Class<?>[])new Class[0]);
            Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);
            Field ping = EntityPlayer.getClass().getDeclaredField("ping");
            return ping.getInt(EntityPlayer);
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            return 0;
            
        }
		
	}

}
