package Lihad.Mentor;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import Lihad.Mentor.BukkitSchedule.BukkitSchedule;
import Lihad.Mentor.Command.CommandRunner;
import Lihad.Mentor.Config.BeyondConfig;
import Lihad.Mentor.Config.BeyondConfigReader;
import Lihad.Mentor.Config.BeyondConfigWriter;
import Lihad.Mentor.Listeners.BeyondPluginListener;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;


public class Mentor extends JavaPlugin {

	
    public static FileConfiguration configuration;

	public static PermissionHandler handler;
	private final BeyondPluginListener pluginListener = new BeyondPluginListener(this);
	public static boolean permissionsEngaged;
	public static CommandExecutor cmd;
	public static BeyondConfigWriter write;
	public static BeyondConfigReader read;
	public static BeyondConfig config;


	
    public void onDisable() {
		this.saveConfig();
		System.out.println("[Mentor] has disabled");
    }

    public void onEnable() {
		System.out.println("-----------------------------------------");
		
		//ConfigManager
		configuration = getConfig();

		//ClassManager
		config = new BeyondConfig(this);
		read = new BeyondConfigReader(this);
		write = new BeyondConfigWriter(this);
	
		//PermsManager
		setupPermissions();
		
		//PluginManager
		PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(pluginListener,this);
        
		//BukkitSchedulerManager
		getServer().getScheduler().scheduleAsyncRepeatingTask(this,new BukkitSchedule(), 0, 1200L);
		
        //CommandManager
		cmd = new CommandRunner(this);
		getCommand("mentor").setExecutor(cmd);
		getCommand("mc").setExecutor(cmd);

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
