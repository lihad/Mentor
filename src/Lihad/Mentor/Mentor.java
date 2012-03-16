package Lihad.Mentor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import Lihad.Mentor.Listeners.BeyondPluginListener;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;


public class Mentor extends JavaPlugin {

	
    public static FileConfiguration configuration;

	public static PermissionHandler handler;
	private final BeyondPluginListener pluginListener = new BeyondPluginListener(this);
	public static boolean permissionsEngaged;

	
    public void onDisable() {
		this.saveConfig();
		System.out.println("[Mentor] has disabled");
    }

    public void onEnable() {
		System.out.println("-----------------------------------------");
		
		//ConfigManager
		configuration = getConfig();

		//ClassManager
	
		//PermsManager
		setupPermissions();
		
		//PluginManager
		PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(pluginListener,this);
        
		//TimerManager
        
        //CommandManager
        

        //OutputManager
		if(permissionsEngaged)System.out.println("[Mentor] Permissions is Active");
		System.out.println("[Mentor] has launched successfully");
		System.out.println("-----------------------------------------");
    }
    
    
	public void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
		
		if (permissionsPlugin != null) {
			handler = ((Permissions) permissionsPlugin).getHandler();
			permissionsEngaged = true;

		} else {
			System.out.println("[Mentor] Permissions has failed to connect");

			permissionsEngaged = false;
		}
	}
}
