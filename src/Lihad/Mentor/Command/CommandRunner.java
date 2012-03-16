package Lihad.Mentor.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import Lihad.Mentor.Mentor;



public class CommandRunner implements CommandExecutor {
	public static Mentor plugin;

	public CommandRunner(Mentor instance){
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
