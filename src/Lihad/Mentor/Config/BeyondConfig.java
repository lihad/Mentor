package Lihad.Mentor.Config;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;

import Lihad.Mentor.Mentor;


public class BeyondConfig {
	public static Mentor plugin;


	public BeyondConfig(Mentor instance) {
		plugin = instance;
	}
	
	//get Functions
	public static List<String> getMentors(){
		return BeyondConfigReader.getKeyList("Mentors");
	}
	public static List<Player> getOnlineMentors(){
		List<String> mentornames = getMentors();
		List<Player> players = new LinkedList<Player>();
		for(int i = 0;i<mentornames.size();i++){
			if(plugin.getServer().getPlayer(mentornames.get(i)) != null)players.add(plugin.getServer().getPlayer(mentornames.get(i)));
		}
		return players;
	}
	public static String getMentor(String mentoree){
		List<String> mentornames = getMentors();
		for(int i = 0;i<mentornames.size();i++){
			if(getMentoree(mentornames.get(i)).equals(mentoree))return mentornames.get(i);
		}
		System.out.println("[Mentor] Error passed: LINE 35 Lihad.Mentor.BeyondConfig");
		return null;
	}
	public static String getMentoree(String mentor){
		return BeyondConfigReader.getString("Mentors."+mentor+".drifter");
	}
	public static String getMentoree(Player mentor){
		return BeyondConfigReader.getString("Mentors."+mentor.getName()+".drifter");
	}
	public static int getStamp(String mentor){
		return BeyondConfigReader.getInt("Mentors."+mentor+".stamp");	
	}
	public static int getStamp(Player mentor){
		return BeyondConfigReader.getInt("Mentors."+mentor.getName()+".stamp");	
	}
	public static List<String> getMentorees(){
		List<String> mentorees = new LinkedList<String>();
		List<String> mentornames = getMentors();
		for(int i = 0;i<mentornames.size();i++){
			mentorees.add(getMentoree(mentornames.get(i)));
		}
		return mentorees;
	}
	public static List<Player> getOnlineMentorees(){
		List<String> mentorees = getMentorees();
		List<Player> players = new LinkedList<Player>();
		for(int i = 0;i<mentorees.size();i++){
			if(plugin.getServer().getPlayer(mentorees.get(i)) != null)players.add(plugin.getServer().getPlayer(mentorees.get(i)));
		}
		return players;
	}
	//has Functions
	//set Functions
	public static void setMentoree(String mentor, String mentoree){
		BeyondConfigWriter.writeConfigurationString("Mentors."+mentor+".drifter", mentoree);
	}
	public static void setStamp(String mentor, int stamp){
		BeyondConfigWriter.writeConfigurationInt("Mentors."+mentor+".stamp", stamp);
	}
	//delete Functions
	public static void removeMentor(String mentor){
		BeyondConfigWriter.writeConfigurationNull("Mentors."+mentor);
	}
	public static void removeMentor(Player mentor){
		BeyondConfigWriter.writeConfigurationNull("Mentors."+mentor.getName());
	}
	//is Functions
	public static boolean isMentor(String player){
		if(getMentors().contains(player))return true;
		else return false;
	}
	public static boolean isMentor(Player player){
		return isMentor(player.getName());
	}
	public static boolean isMentoree(String player){
		if(getMentorees().contains(player))return true;
		else return false;
	}
	public static boolean isMentoree(Player player){
		return isMentoree(player.getName());

	}

}
