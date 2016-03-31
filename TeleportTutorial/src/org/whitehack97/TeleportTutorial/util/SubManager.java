package org.whitehack97.TeleportTutorial.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;

public class SubManager
{
	public static String RepColor(String Str)
	{
		return ChatColor.translateAlternateColorCodes('&', Str);
	}
	
	public static List<String> RepColorList(List<String> List)
	{
		List<String> RepList = new ArrayList<String>();
		for(String Str : List)
		{
			RepList.add(ChatColor.translateAlternateColorCodes('&', Str));
		}
		return RepList;
	}
	
	public static String VarReplace(String Str, String Value, Object newValue)
	{
		String ObjectValue = newValue.toString();
		return Str.replaceAll(Value, ObjectValue);
	}
	
	public static void msg(Player player, String message)
	{
		msg(player, message, true);
	}
	
	public static void msg(Player player, String message, boolean addPrefix)
	{
		if(addPrefix) player.sendMessage(TeleportTutorial.Prefix + ChatColor.translateAlternateColorCodes('&', message));
		else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void Cmsg(String message)
	{
		Cmsg(message, true);
	}
	
	public static void Cmsg(String message, boolean addPrefix)
	{
		if(addPrefix) Bukkit.getConsoleSender().sendMessage(TeleportTutorial.Prefix + ChatColor.translateAlternateColorCodes('&', message));
		else Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}
