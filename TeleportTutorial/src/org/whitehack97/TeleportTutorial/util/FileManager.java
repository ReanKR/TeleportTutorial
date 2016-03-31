package org.whitehack97.TeleportTutorial.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;

public class FileManager
{
	public static boolean MoveFile(File originPath, File newPath)
	{
		try
		{
			FileInputStream inputStream = new FileInputStream(originPath);         
			FileOutputStream outputStream = new FileOutputStream(newPath);
			  
			FileChannel fcin =  inputStream.getChannel();
			FileChannel fcout = outputStream.getChannel();
			  
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
			  
			fcout.close();
			fcin.close();
			  
			outputStream.close();
			inputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		File file = originPath;
		file.delete();
		return true;
	}

	public static YamlConfiguration getFile(String fileName)
	{
		if(! fileName.endsWith(".yml")) fileName = fileName + ".yml";
		YamlConfiguration path = getFile(fileName, TeleportTutorial.plugin.getDataFolder().toString() + "/");
		return path;
	}
	
	public static YamlConfiguration getFile(String fileName, String Path)
	{
		if(! Path.endsWith("/")) Path = Path + "/";
		if(! fileName.endsWith(".yml")) fileName = fileName + ".yml";
		File file = new File(Path + fileName);
		if(! file.exists())
		{
			try
			{
				TeleportTutorial.plugin.saveResource(fileName, false);
				MoveFile(new File(TeleportTutorial.plugin.getDataFolder() + "/" + fileName), file);
			}
			catch(IllegalArgumentException e)
			{
				e.printStackTrace();
				try
				{
					file.createNewFile();
					MoveFile(new File(TeleportTutorial.plugin.getDataFolder() + "/" + fileName), file);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		YamlConfiguration path = YamlConfiguration.loadConfiguration(file);
		return path;
	}
}
