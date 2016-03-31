package org.whitehack97.TeleportTutorial.config;

import java.io.File;

import org.whitehack97.TeleportTutorial.main.TeleportTutorial;

public class LoadingConfig
{
	public static void getConfig()
	{
		File file = new File(TeleportTutorial.plugin.getDataFolder() + "/config.yml");
		if(! file.exists()) TeleportTutorial.plugin.saveDefaultConfig();
	}
}
