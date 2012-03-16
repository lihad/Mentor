package Lihad.Mentor.Command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Lihad.Mentor.Mentor;
import Lihad.Mentor.Config.BeyondConfig;



public class CommandRunner implements CommandExecutor {
	public static Mentor plugin;
	//<mentor,mentoree>
	public static Map<String, String> invite = new HashMap<String,String>();

	public CommandRunner(Mentor instance){
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string,
			String[] arg) {
		if(cmd.getName().equalsIgnoreCase("mentor") && arg.length == 0 && sender instanceof Player){
			Player player = (Player)sender;
			if(Mentor.handler.has(player, "mentor.can") && !BeyondConfig.isMentoree(player)){
				if(BeyondConfig.isMentor(player)){
					player.sendMessage("You are currently mentoring: "+ChatColor.GREEN.toString()+BeyondConfig.getMentoree(player)+ChatColor.WHITE.toString()+". You have "+ChatColor.GREEN.toString()+BeyondConfig.getStamp(player)+ChatColor.WHITE.toString()+" points stored!");
					player.sendMessage("To abandon this player, type "+ChatColor.RED.toString()+" /mentor ditch");
				}else{
					player.sendMessage(ChatColor.RED.toString()+"You currently have no drifter looking up to you! Oh no!");
					player.sendMessage("Type"+ChatColor.GREEN.toString()+" /mentor <playername>"+ChatColor.WHITE.toString()+" to become a mentor of a drifter!");
				}
			}else if(Mentor.handler.inGroup(player.getWorld().getName(), player.getName(), "Drifter")){
				if(BeyondConfig.isMentoree(player)){
					player.sendMessage("You are currently being mentored by: "+ChatColor.GREEN.toString()+BeyondConfig.getMentor(player.getName()));
					player.sendMessage("Type "+ChatColor.GREEN.toString()+"/mc"+ChatColor.WHITE.toString()+" to join a private chat with your mentor.  Type "+ChatColor.RED.toString()+"/mentor ditch"+ChatColor.WHITE.toString()+" to ditch your mentor");
				}else if(invite.containsValue(player.getName())){
					String mentor = "no one";
					for(int i=0;i<invite.size();i++){
						if(invite.get(invite.keySet().toArray()[i]).equals(player.getName())){
							mentor = (String)(invite.keySet().toArray()[i]);
						}
					}
					player.sendMessage(ChatColor.GREEN.toString()+"Looks like you've recieved an invitation from "+mentor+"!");
					player.sendMessage("Type "+ChatColor.GREEN.toString()+"/mentor "+player.getName()+ChatColor.WHITE.toString()+" tp get mentored by that player!");

				}else{
					player.sendMessage(ChatColor.GREEN.toString()+"You are requesting the help of a Mentor!");
					plugin.getServer().broadcastMessage(ChatColor.GREEN.toString()+"Drifter "+player.getName()+ChatColor.WHITE.toString()+" is requesting a Mentor! Type "+ChatColor.GRAY.toString()+"/mentor"+player.getName()+ChatColor.WHITE.toString()+" to become their mentor!");
				}
			}else{
				player.sendMessage(ChatColor.RED.toString()+"You are unable to make a player your mentor :(");
			}
			return true;
		}else if(cmd.getName().equalsIgnoreCase("mentor") && arg.length == 1 && sender instanceof Player){
			Player player = (Player)sender;
			if(arg[0].equals("ditch")){
				if(BeyondConfig.isMentoree(player)){
					player.sendMessage(ChatColor.RED.toString()+"You have ditched your mentor.");
					plugin.getServer().broadcastMessage(ChatColor.RED.toString()+player.getName()+" has ditched their mentor, "+BeyondConfig.getMentor(player.getName()));
					BeyondConfig.removeMentor(BeyondConfig.getMentor(player.getName()));
				}else if(BeyondConfig.isMentor(player)){
					if(player.getLevel() >= 30){
						player.sendMessage(ChatColor.RED.toString()+"You have ditched your drifter... shame.");
						plugin.getServer().broadcastMessage(ChatColor.RED.toString()+player.getName()+" has ditched their drifter, "+BeyondConfig.getMentoree(player.getName()));
						player.setLevel(player.getLevel()-30);
						BeyondConfig.removeMentor(player.getName());
					}else{
						player.sendMessage(ChatColor.RED.toString()+"You must be level 30 to ditch your drifter.");
					}
				}else{
					player.sendMessage(ChatColor.RED.toString()+"Not a valid command for your current situation");
				}
			}else if(Mentor.handler.has(player, "mentor.can") && !BeyondConfig.isMentoree(player) && !BeyondConfig.isMentor(player)){
				if(plugin.getServer().getPlayer(arg[0]) != null){
					invite.put(player.getName(), arg[0]);
					player.sendMessage(ChatColor.GREEN.toString()+"You have invited "+arg[0]+" to be mentored by you!");
					plugin.getServer().getPlayer(arg[0]).sendMessage("Type "+ChatColor.GREEN.toString()+"/mentor "+player.getName()+ChatColor.WHITE.toString()+" to accept!");
				}else{
					player.sendMessage(ChatColor.RED.toString()+"Not a valid player");
				}
			}else if(Mentor.handler.inGroup(player.getWorld().getName(), player.getName(), "Drifter") && !BeyondConfig.isMentoree(player) && !BeyondConfig.isMentor(player)){
				if(plugin.getServer().getPlayer(arg[0]) != null){
					if(!BeyondConfig.isMentor(plugin.getServer().getPlayer(arg[0]))){
						BeyondConfig.setMentoree(plugin.getServer().getPlayer(arg[0]).getName(), player.getName());
						BeyondConfig.setStamp(plugin.getServer().getPlayer(arg[0]).getName(), 1);
						plugin.getServer().getPlayer(arg[0]).sendMessage(ChatColor.GREEN.toString()+"Congrats!  You are now a mentor to "+player.getName());
						plugin.getServer().getPlayer(arg[0]).sendMessage("Use "+ChatColor.GREEN.toString()+"/mc"+ChatColor.WHITE.toString()+" to privately chat with your drifter!");
						player.sendMessage(ChatColor.GREEN.toString()+"Congrats!  You are now being mentored by "+plugin.getServer().getPlayer(arg[0]).getName());
						player.sendMessage("Use "+ChatColor.GREEN.toString()+"/mc"+ChatColor.WHITE.toString()+" to privately chat with your mentor!");
						invite.remove(plugin.getServer().getPlayer(arg[0]));
					}else{
						player.sendMessage(ChatColor.RED.toString()+"This mentor is already mentoring another player! Try another one.");
					}
				}else{
					player.sendMessage(ChatColor.RED.toString()+"That mentor either doesn't exist or isn't online. Try again.");
				}
			}else if(BeyondConfig.isMentoree(player)){
				player.sendMessage(ChatColor.RED.toString()+"You are already being mentored by "+BeyondConfig.getMentor(player.getName()));
				player.sendMessage("Type "+ChatColor.RED.toString()+"/mentor ditch"+ChatColor.WHITE.toString()+" to ditch "+BeyondConfig.getMentor(player.getName()));
			}else if(BeyondConfig.isMentor(player)){
				player.sendMessage(ChatColor.RED.toString()+"You are already mentoring "+BeyondConfig.getMentoree(player.getName()));
				player.sendMessage("Type "+ChatColor.RED.toString()+"/mentor ditch"+ChatColor.WHITE.toString()+" to ditch "+BeyondConfig.getMentoree(player.getName()));
			}else{
				player.sendMessage(ChatColor.RED.toString()+"Not a valid command for your current situation");
			}
			return true;
		}else if(cmd.getName().equalsIgnoreCase("mc") && arg.length == 0 && sender instanceof Player){
			Player player = (Player)sender;
			if(BeyondConfig.isMentoree(player)){
				player.performCommand("tell "+BeyondConfig.getMentor(player.getName()));
			}else if(BeyondConfig.isMentor(player)){
				player.performCommand("tell "+BeyondConfig.getMentoree(player.getName()));
			}else{
				player.sendMessage(ChatColor.RED.toString()+"Not a valid command for your current situation");
			}
		}
		return false;
	}
}
