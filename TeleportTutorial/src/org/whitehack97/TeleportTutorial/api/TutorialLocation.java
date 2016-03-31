package org.whitehack97.TeleportTutorial.api;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;

public class TutorialLocation
{
	String Name = UUID.randomUUID().toString();
	Location location = new Location(null, 0, 0, 0, 0, 0);
	private String MainMessage = "";
	private String SubMessage = "";
	
	public TutorialLocation(String Name)
	{
		this.Name = Name;
	}
	public String getName()
	{
		return this.Name;
	}
	
	public void setX(Double X)
	{
		this.location.setX(X);
	}
	
	public void setY(Double Y)
	{
		this.location.setY(Y);
	}
	
	public void setZ(Double Z)
	{
		this.location.setZ(Z);
	}
	
	public void setYaw(Float Yaw)
	{
		this.location.setYaw(Yaw);
	}
	
	public void setPitch(Float Pitch)
	{
		this.location.setPitch(Pitch);
	}
	
	@Deprecated
	public void setWorld(String world)
	{
		this.location.setWorld(TeleportTutorial.plugin.getServer().getWorld(world));
	}
	
	public void setWorld(World world)
	{
		this.location.setWorld(world);
	}
	
	public void setMainMessage(String message)
	{
		this.MainMessage = ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public void setSubMessage(String message)
	{
		this.SubMessage = ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public Location getLocation()
	{
		return this.location;
	}
	
	public String getMessage()
	{
		return ChatColor.translateAlternateColorCodes('&', this.MainMessage);
	}
	
	public String getSubMessage()
	{
		return ChatColor.translateAlternateColorCodes('&', this.SubMessage);
	}
}
