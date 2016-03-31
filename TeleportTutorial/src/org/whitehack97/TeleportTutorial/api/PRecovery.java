package org.whitehack97.TeleportTutorial.api;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PRecovery
{
	private Player player;
	private int TID = 0;
	private float FlySpeed = 0.8F;
	private float WalkSpeed = 0.8F;
	private GameMode Gamemode = GameMode.SURVIVAL;
	private Tutorial tutorial;
	private String Met;
	private Location location;

	public PRecovery(Player player)
	{
		this.player = player;
		this.FlySpeed = player.getFlySpeed();
		this.WalkSpeed = player.getWalkSpeed();
		this.Gamemode  = player.getGameMode();
	}
	public void setPlayer(Player player)
	{
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public void setTutorial(Tutorial tutorial)
	{
		this.tutorial = tutorial;
	}
	
	public void setTutorialMethod(String Met)
	{
		this.Met = Met;
	}
	
	public void setTID(int TID)
	{
		this.TID = TID;
	}
	
	public int getTID()
	{
		return this.TID;
	}
	
	public float getFlySpeed()
	{
		return this.FlySpeed;
	}
	
	public float getWalkSpeed()
	{
		return this.WalkSpeed;
	}
	
	public GameMode getGameMode()
	{
		return this.Gamemode;
	}
	public void setLocation(Location location)
	{
		this.location = location;
	}
}
