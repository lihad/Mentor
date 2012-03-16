package Lihad.Mentor.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;

import Lihad.Mentor.Mentor;


public class BeyondConfigReader {
	public static Mentor plugin;
    public BeyondConfigReader(Mentor instance) {
        plugin = instance;
    }
    
    public static int getInt(String string) {
        return Mentor.configuration.getInt(string, 0);
    }
    public static int[] getIntArray(int size, String string, int arg){
    	int[] array = new int[size];
    	for(int i = 0;i<size;i++){
    		array[i] = getInt(string);
    	}
    	return array; 	
    }
    public static long getLong(String string) {
        return Long.parseLong(Mentor.configuration.getString(string));
    }
    public static long[] getLongArray(int size, String string, String string2, long arg){
    	long[] array = new long[size];
    	for(int i = 0;i<size;i++){
    		array[i] = getLong(string + i + string2);
    	}
    	return array; 	
    }
    public static String getString(String string) {
        return Mentor.configuration.getString(string);
    }
    
    public static String[] getStringArray(int size, String string, String string2){
    	String[] array = new String[size];
    	for(int i = 0;i<size;i++){
    		array[i] = getString(string + i + string2);
    	}
    	return array; 	
    }
	public static List<String> getKeyList(String path){
		if(Mentor.configuration.getConfigurationSection(path) == null)return null;
		Set<String> raw = Mentor.configuration.getConfigurationSection(path).getKeys(false);
    	return Arrays.asList(raw.toArray(new String[raw.size()]));
    }
    public static boolean getBoolean(String string, boolean arg) {
        return Mentor.configuration.getBoolean(string, arg);
    }
    
    public static boolean[] getBooleanArray(int size, String string, String string2, boolean arg){
    	boolean[] array = new boolean[size];
    	for(int i = 0;i<size;i++){
    		array[i] = getBoolean(string + i + string2, arg);
    	}
    	return array; 	
    }
    public static double getDouble(String string, double arg) {
        return Mentor.configuration.getDouble(string, arg);
    }
    
    public static List<Integer> stringToIntList(String string){
    	string = string.replaceAll(" ", "");
    	String[] strArray = string.split(",");
        List<Integer> intList = new ArrayList<Integer>();
    	for(int i=0; i<strArray.length; i++){
    		intList.add(Integer.parseInt(strArray[i]));
    	}
    	return intList;
    }
}
