package xyz.mrcote33.ping.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PingUtil {
	
	public static int getPing(Player p) {
		
		final String v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		
        if (!p.getClass().getName().equals("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer")) {
            p = Bukkit.getPlayer(p.getUniqueId());
        }
        
        try {
        	
            final Class<?> CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer");
            final Object CraftPlayer = CraftPlayerClass.cast(p);
            final Method getHandle = CraftPlayer.getClass().getMethod("getHandle", (Class<?>[])new Class[0]);
            final Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);
            final Field ping = EntityPlayer.getClass().getDeclaredField("ping");
            return ping.getInt(EntityPlayer);
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            return 0;
            
        }
		
	}

}
