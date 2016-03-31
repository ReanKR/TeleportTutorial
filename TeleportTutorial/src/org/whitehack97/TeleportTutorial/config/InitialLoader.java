package org.whitehack97.TeleportTutorial.config;

import java.io.File;

import org.whitehack97.TeleportTutorial.main.TeleportTutorial;
import org.whitehack97.TeleportTutorial.util.FileManager;
import org.whitehack97.TeleportTutorial.util.SubManager;

public class InitialLoader
{
	public static void getInitialSet()
	{
		File TutorialDir = new File(TeleportTutorial.plugin.getDataFolder().toString() + "/Tutorials");
		File TutorialSetDir = new File(TeleportTutorial.plugin.getDataFolder().toString() + "/Tutorials/Sets");
		if(! TutorialDir.exists()) TutorialDir.mkdir();
		if(! TutorialSetDir.exists()) TutorialSetDir.mkdir();
		
		File exampleFile = new File(TeleportTutorial.plugin.getDataFolder().toString() + "/Tutorials/Example.yml");
		File exampleSetFile = new File(TeleportTutorial.plugin.getDataFolder().toString() + "/Tutorials/Sets/Example_Set.yml");
		if(! exampleFile.exists())
		{
			FileManager.getFile("Example", "plugins/TeleportTutorial/Tutorials");
			SubManager.Cmsg("&aCreating new File: Example.yml");
		}
		
		if(! exampleSetFile.exists())
		{
			FileManager.getFile("Example_Set", "plugins/TeleportTutorial/Tutorials/Sets");
			SubManager.Cmsg("&aCreating new File: Example_set.yml");
		}
	}

}
