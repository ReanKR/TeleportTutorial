package org.whitehack97.TeleportTutorial.config;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.whitehack97.TeleportTutorial.api.ResultCommand;
import org.whitehack97.TeleportTutorial.api.ResultCommand.Type;
import org.whitehack97.TeleportTutorial.api.Tutorial;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;
import org.whitehack97.TeleportTutorial.util.SubManager;

public class LoadingTutorial
{
	@SuppressWarnings("deprecation")
	public static void LoadTutorialConfig()
	{
		InitialLoader.getInitialSet();
		File directory = new File(TeleportTutorial.plugin.getDataFolder().toString() + "/Tutorials");
		File[] files = directory.listFiles();
		for(File file : files)
		{
			if(file.isFile())
			{
				YamlConfiguration TutorialSection = YamlConfiguration.loadConfiguration(file);
				if(TutorialSection.contains("Name"))
				{
					Tutorial tutorial = new Tutorial(file.getName());
					
					if(TutorialSection.contains("Command")) tutorial.setRunCommand(TutorialSection.getString("Command"));
					if(TutorialSection.contains("Set-File")) tutorial.setMethodFile(TutorialSection.getString("Set-File"));
					if(TutorialSection.contains("#Need-Permission")) tutorial.setNeedPermission(TutorialSection.getBoolean("#Need-Permission"));
					if(TutorialSection.contains("Name")) tutorial.setName(TutorialSection.getString("Name"));
					if(TutorialSection.contains("Block-Movement")) tutorial.setBlockMovement(TutorialSection.getBoolean("Block-Movement"));
					if(TutorialSection.contains("Block-All-Commands")) tutorial.setBloackAllCommands(TutorialSection.getBoolean("Block-All-Commands"));
					if(TutorialSection.contains("Broadcast-Complete-Tutorial")) tutorial.setBroadcast(TutorialSection.getBoolean("Broadcast-Complete-Tutorial"));
					if(TutorialSection.contains("Default-Delay-Seconds")) tutorial.setDelaySeconds(TutorialSection.getInt("Default-Delay-Seconds"));
					if(TutorialSection.contains("Default-Cooldown-Seconds")) tutorial.setCooldownSeconds(TutorialSection.getInt("Default-Cooldown-Seconds"));
					if(TutorialSection.contains("Sound-Disabled")) tutorial.setDisableSound(TutorialSection.getBoolean("Sound-Disabled"));
					if(TutorialSection.contains("Sound")) tutorial.setSoundType(TutorialSection.getString("Sound"));
					if(TutorialSection.contains("Using-TitleAPI")) tutorial.setUseTitleAPI(TutorialSection.getBoolean("Using-TitleAPI"));
					
					if(TutorialSection.contains("Result.Run-Commands")) tutorial.isRunResultCommands(TutorialSection.getBoolean("Result.Run-Commands"));
					if(TutorialSection.contains("Result.Reward-Items")) tutorial.isGiveResultItems(TutorialSection.getBoolean("Result.Reward-Items"));
					if(TutorialSection.contains("Result.Commands"))
					{
						for(String undefineCommand : TutorialSection.getStringList("Result.Commands"))
						{
							ResultCommand command = new ResultCommand();
							if(undefineCommand.contains(": "))
							{
								command.setType(command.ValueOf(undefineCommand.split(": ")[0]));
								command.setCommand(undefineCommand.split(": ")[1]);
							}
							else
							{
								command.setType(Type.PLAYER);
								command.setCommand(undefineCommand);
							}
							tutorial.addResultCommand(command);
						}
					}
					if(TutorialSection.contains("Result.Items"))
					{
						
						for(String Name : TutorialSection.getConfigurationSection("Result.Items").getKeys(false))
						{
							int ID = 1;
							byte Data = 0;
							int Amounts = 1;
							ConfigurationSection Section = TutorialSection.getConfigurationSection("Result.Items." + Name);
							if(Section.contains("ID")) ID = Section.getInt("ID");
							if(Section.contains("DATA-VALUE")) Data = Byte.parseByte(Section.getString("DATA-VALUE"));
							if(Section.contains("Amounts")) Amounts = Section.getInt("Amounts");
							ItemStack itemstack = new ItemStack(ID, Amounts, Data);
							ItemMeta Metadata = itemstack.getItemMeta();
							if(Section.contains("NAME")) Metadata.setDisplayName(SubManager.RepColor(Section.getString("NAME")));
							if(Section.contains("Lore"))
							{
								Metadata.setLore(SubManager.RepColorList(Section.getStringList("Lore")));
							}
							else if(Section.contains("DESCRIPTION"))
							{
								Metadata.setLore(SubManager.RepColorList(Section.getStringList("DESCRIPTION")));
							}
							
							if(Section.contains("ENCHANTMENT"))
							{
								List<String> Enchantments = Section.getStringList("ENCHANTMENT");
								for(String Enchant : Enchantments)
								{
									try
									{
										String[] EnchantCutter = Enchant.split(", ");
										Metadata.addEnchant(Enchantment.getByName(EnchantCutter[0]), Integer.parseInt(EnchantCutter[1]), true);
									}
									catch(NullPointerException e)
									{
										/* Invailed Enchantment */
										continue;
									}
								}
							}
							itemstack.setItemMeta(Metadata);
							tutorial.addItem(Name, itemstack);
						}
					}
					tutorial.setLocationClass(LoadingSetTutorial.LoadSetTutorialConfig(tutorial.getSetFilePath()));
					TeleportTutorial.tutorials.put(file.getName().replaceAll(".yml", ""), tutorial);
				}
				else
				{
					continue;
				}
			}
		}
	}
}
