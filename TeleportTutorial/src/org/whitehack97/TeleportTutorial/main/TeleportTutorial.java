package org.whitehack97.TeleportTutorial.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.whitehack97.TeleportTutorial.api.Tutorial;
import org.whitehack97.TeleportTutorial.config.LoadingConfig;
import org.whitehack97.TeleportTutorial.config.LoadingTutorial;
import org.whitehack97.TeleportTutorial.util.SubManager;

public class TeleportTutorial extends JavaPlugin implements Listener
{
	public static TeleportTutorial plugin;
	public static Server server;
	public static String Prefix = SubManager.RepColor("&6[&eT&beleport&aT&futorial&6]&f ");
	public static Map<String, Tutorial> tutorials = new HashMap<String, Tutorial>();
	public static Map<String, Tutorial> tutorialCommands = new HashMap<String, Tutorial>();
	public static String tPermission = "teleporttutorial.tutorial";
	
	@Override
	public void onEnable()
	{
		plugin = this;
		server = this.getServer();
		LoadingConfig.getConfig();
		LoadingTutorial.LoadTutorialConfig();
		for(Tutorial tutorial : tutorials.values())
		{
			if(tutorial.hasCommand())
			{
				tutorialCommands.put(tutorial.getCommand(), tutorial);
			}
		}
		SubManager.Cmsg(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " now &aEnabled");
		SubManager.Cmsg("&bMade by whitehack97@gmail.com (REAN KR)");
	}
	
	@Override
	public void onDisable()
	{
		SubManager.Cmsg(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " now &cDisabled");
		SubManager.Cmsg("&bMade by whitehack97@gmail.com (REAN KR)");
	}

}
