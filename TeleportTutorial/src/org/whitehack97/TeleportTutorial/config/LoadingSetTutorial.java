package org.whitehack97.TeleportTutorial.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.TeleportTutorial.api.TutorialLocation;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;

public class LoadingSetTutorial
{

	@SuppressWarnings("deprecation")
	public static Map<String, TutorialLocation> LoadSetTutorialConfig(String setFilePath)
	{
		if(! setFilePath.endsWith(".yml")) setFilePath = setFilePath + ".yml";
		
		File file = new File(TeleportTutorial.plugin.getDataFolder() + "/Tutorials/Sets/" + setFilePath);
		if(! file.exists())
		{
			return null;
		}
		else
		{
			Map<String, TutorialLocation> locations = new HashMap<String, TutorialLocation>();
			YamlConfiguration LocationSection = YamlConfiguration.loadConfiguration(file);
			for(String MethodName : LocationSection.getKeys(false))
			{
				ConfigurationSection Section = LocationSection.getConfigurationSection(MethodName);
				TutorialLocation tlocation = new TutorialLocation(MethodName);
				try
				{
					tlocation.setWorld(Section.getString("World"));
					tlocation.setX(Section.getDouble("Coordinates.X"));
					tlocation.setY(Section.getDouble("Coordinates.Y"));
					tlocation.setZ(Section.getDouble("Coordinates.Z"));
					tlocation.setYaw(Float.parseFloat(Section.getString("Angle.Yaw")));
					tlocation.setPitch(Float.parseFloat(Section.getString("Angle.Pitch")));
					tlocation.setMainMessage(Section.getString("Messages.Main"));
					if(Section.contains("Messages.Sub")) tlocation.setSubMessage(Section.getString("Messages.Sub"));
				}
				catch(NullPointerException e)
				{
					e.printStackTrace();
					continue;
				}
				locations.put(MethodName, tlocation);
			}
			return locations;
		}
	}

}
