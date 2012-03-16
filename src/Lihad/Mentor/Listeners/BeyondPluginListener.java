package Lihad.Mentor.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

import Lihad.Mentor.Mentor;



public class BeyondPluginListener implements Listener {
	public static Mentor plugin;
    public BeyondPluginListener(Mentor instance) {
        plugin = instance;
    }
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event){
    	if((event.getPlugin().getDescription().getName().equals("Permissions"))) plugin.setupPermissions();
    }
}
