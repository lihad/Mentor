package Lihad.Mentor.Config;

import Lihad.Mentor.Mentor;



public class BeyondConfigWriter {
	public static Mentor plugin;
    public BeyondConfigWriter(Mentor instance) {
        plugin = instance;
    }
    public static void writeConfigurationBoolean(String string, boolean arg){
    	Mentor.configuration.set(string, arg);
    }
    public static void writeConfigurationInt(String string, int arg){
    	Mentor.configuration.set(string, arg);
    }
    public static void writeConfigurationDouble(String string, double arg){
    	Mentor.configuration.set(string, arg);
    }
    public static void writeConfigurationString(String string, String arg){
    	Mentor.configuration.set(string, arg);
    }
    public static void writeConfigurationLong(String string, long arg){
    	Mentor.configuration.set(string, arg);
    }
    public static void writeConfigurationNull(String string){
    	Mentor.configuration.set(string, null);
    }
}
