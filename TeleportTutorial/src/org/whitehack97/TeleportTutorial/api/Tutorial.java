package org.whitehack97.TeleportTutorial.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class Tutorial
{
	private String Name = UUID.randomUUID().toString();
	private String TutorialName = UUID.randomUUID().toString();
	private Sound Sound = org.bukkit.Sound.NOTE_PLING;
	private int DelaySecond = 6;
	private int CooldownSecond = 5;
	private boolean DisabledSound = false;
	private boolean BlockedMovement = true;
	private boolean BlockedAllCommands = true;
	private boolean EnabledBroadcast = false;
	private boolean UsingTitleAPI = true;
	private boolean EnableResultCommand = false;
	private boolean EnableResultItem = false;
	private String Command = null;
	private String MethodFile;
	private List<ResultCommand> ResultCommands = new ArrayList<ResultCommand>();
	private Map<String, ItemStack> ResultItems = new HashMap<String, ItemStack>();
	private Map<String, TutorialLocation> LocationMethod = new HashMap<String, TutorialLocation>();
	private boolean NeedPermission = false;

	public Tutorial(String Yaml)
	{
		this.MethodFile = Yaml.replaceAll(".yml", "") + "_Set.yml";
		this.Name = Yaml.replaceAll(".yml", "");
	}
	
	public String getFiletoString()
	{
		return this.Name;
	}
	
	public void setName(String Name)
	{
		this.TutorialName  = Name;
	}
	
	public void setRunCommand(String Command)
	{
		this.Command  = Command;
	}
	
	public void setMethodFile(String Filename)
	{
		String Yamlname = Filename;
		if(! Yamlname.endsWith(".yml")) Yamlname = Yamlname + ".yml";
		this.MethodFile  = Yamlname;
	}
	
	public void setBlockMovement(boolean Enabled)
	{
		this.BlockedMovement = Enabled;
	}
	
	public void setBloackAllCommands(boolean Enabled)
	{
		this.BlockedAllCommands = Enabled;
	}
	
	public void setBroadcast(boolean Enabled)
	{
		this.EnabledBroadcast  = Enabled;
	}
	
	public void setDelaySeconds(int Seconds)
	{
		this.DelaySecond = Seconds;
	}
	
	public void setCooldownSeconds(int Seconds)
	{
		this.CooldownSecond = Seconds;
	}
	
	public void setDisableSound(boolean Disabled)
	{
		this.DisabledSound = Disabled;
	}
	
	public void setSoundType(String soundName)
	{
		this.Sound = org.bukkit.Sound.valueOf(soundName);
	}
	
	public void setUseTitleAPI(boolean Enabled)
	{
		this.UsingTitleAPI = Enabled;
	}
	
	public void isRunResultCommands(boolean Enabled)
	{
		this.EnableResultCommand = Enabled;
	}
	
	public void isGiveResultItems(boolean Enabled)
	{
		this.EnableResultItem = Enabled;
	}

	public void addResultCommand(ResultCommand command)
	{
		this.ResultCommands.add(command);
	}
	
	public void addItem(String Name,ItemStack item)
	{
		this.ResultItems.put(Name, item);
	}

	public String getSetFilePath()
	{
		return this.MethodFile;
	}

	public void setLocationClass(String MethodName, TutorialLocation LocationClass)
	{
		this.LocationMethod.put(MethodName, LocationClass);
	}

	public void setLocationClass(Map<String, TutorialLocation> tutorials)
	{
		LocationMethod.putAll(tutorials);
	}

	public void setNeedPermission(boolean Required)
	{
		this.NeedPermission  = Required;
	}
	
	public boolean hasCommand()
	{
		if(Command.isEmpty()) return false;
		else return true;
	}

	public String getCommand()
	{
		return this.Command;
	}
	
	public boolean isNeedPermission()
	{
		return this.NeedPermission;
	}
	
	public Sound getSound()
	{
		return this.Sound;
	}
	
	public int getDefaultDelaySecond()
	{
		return this.DelaySecond;
	}
	
	public int getDefaultCooldownSeconds()
	{
		return this.CooldownSecond;
	}
	
	public boolean isUsingTitleAPI()
	{
		return this.UsingTitleAPI;
	}
	
	public String getTutorialName()
	{
		return this.TutorialName;
	}

	public boolean isSoundDisabled()
	{
		return this.DisabledSound;
	}

	public Map<String, TutorialLocation> getTutorialMethods()
	{
		return this.LocationMethod;
	}
}
