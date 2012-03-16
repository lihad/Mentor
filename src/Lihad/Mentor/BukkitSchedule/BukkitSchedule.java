package Lihad.Mentor.BukkitSchedule;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Lihad.Mentor.Config.BeyondConfig;

public class BukkitSchedule implements Runnable {

	@Override
	public void run() {
		List<String> mentors = BeyondConfig.getMentors();
		if(mentors != null && !mentors.isEmpty()){
			for(int i = 0;i<mentors.size();i++){
				if(BeyondConfig.getStamp(mentors.get(i)) >= 120){
					if(BeyondConfig.plugin.getServer().getPlayer(mentors.get(i)) != null){
						Player player = BeyondConfig.plugin.getServer().getPlayer(mentors.get(i));
						player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.DIAMOND, 20));
						player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.GOLD_INGOT, 20));
						player.sendMessage("You have been a successful mentor! Congrats!");
						BeyondConfig.removeMentor(player);
					}else{
						System.out.println("[Mentor]"+mentors.get(i)+" is valid to claim prize.  Not online.");
					}
				}else if(BeyondConfig.plugin.getServer().getPlayer(mentors.get(i)) != null && BeyondConfig.plugin.getServer().getPlayer(BeyondConfig.getMentoree(mentors.get(i))) != null){
					BeyondConfig.setStamp(mentors.get(i), BeyondConfig.getStamp(mentors.get(i))+1);
				}
			}
		}
	}
}
