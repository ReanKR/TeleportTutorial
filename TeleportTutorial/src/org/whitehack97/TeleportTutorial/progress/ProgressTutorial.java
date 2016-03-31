package org.whitehack97.TeleportTutorial.progress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.whitehack97.TeleportTutorial.api.PRecovery;
import org.whitehack97.TeleportTutorial.api.Tutorial;
import org.whitehack97.TeleportTutorial.api.TutorialLocation;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;
import org.whitehack97.TeleportTutorial.titleapi.TitleAPI;

public class ProgressTutorial
{
	public Map<Player, Integer> ProgressBox = new HashMap<Player, Integer>();
	public static Map<Player, Boolean> RunningStatus = new HashMap<Player, Boolean>();
	public static Map<Player, Tutorial> ProgressTutorial = new HashMap<Player, Tutorial>();
	
	public void StartTutorial(final Player p, Tutorial tutorial)
	{
		PRecovery pr = new PRecovery(p);
		final int tid = TeleportTutorial.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(TeleportTutorial.plugin, new Runnable()
		{
			@Override
			public void run()
			{
				if(! p.isOnline())
				{
					if(tutorial.isUsingTitleAPI())
					{
						TitleAPI.clearTitle(p);
					}
					endTask(pr.getTID());
					/* Creating player backup */
					return;
				}
				else
				{
					if(ProgressBox.get(p).intValue() > 0)
					{
						if(! tutorial.isSoundDisabled())
						{
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.2F, 1.0F);
						}

						if(tutorial.isUsingTitleAPI())
						{
							TitleAPI.sendTitle(p, 40, 30, 30, tutorial.getTutorialName(), "After " + ProgressBox.get(p).intValue() + " seconds to start");
						}
						else
						{
							p.sendMessage("After " + ProgressBox.get(p).intValue() + " seconds to start");
						}
					}
					else
					{
						endTask(pr.getTID());
						RunningTutorial(p, tutorial, pr);
					}
					ProgressBox.put(p, ProgressBox.get(p).intValue() - 1);
				}
			}

		}, 0L, 20L);
		pr.setTID(tid);
		ProgressBox.put(p, tutorial.getDefaultCooldownSeconds());
	}
	
	public void RunningTutorial(final Player p, Tutorial tutorial, PRecovery recovery)
	{
		Set<String> TutorialKey = tutorial.getTutorialMethods().keySet();
		List<String> TutorialMet = new ArrayList<String>();
		TutorialMet.addAll(TutorialKey);
		final int tid = TeleportTutorial.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(TeleportTutorial.plugin, new Runnable()
		{
			@Override
			public void run()
			{
				if(! p.isOnline())
				{
					endTask(recovery.getTID());
					/* Creating player backup */
					return;
				}
				else
				{
					if(TutorialMet.size() >= ProgressBox.get(p).intValue())
					{
						if(! tutorial.isSoundDisabled())
						{
							p.playSound(p.getLocation(), tutorial.getSound(), 1.2F, 1.0F);
						}
					  	TutorialLocation tlocation = tutorial.getTutorialMethods().get(TutorialMet.get(ProgressBox.get(p)));
					  	p.teleport(tlocation.getLocation());
					  	if(tutorial.isUsingTitleAPI())
					  	{
					  		TitleAPI.sendTitle(p, tutorial.getDefaultDelaySecond() + 20, (tutorial.getDefaultDelaySecond() / 2) * 20, (tutorial.getDefaultDelaySecond() / 2) * 20,
					  				tlocation.getMessage(), tlocation.getSubMessage());
					  	}
					}
					else
					{
						endTask(recovery.getTID());
						EndTutorial(p, tutorial, recovery);
					}
				}
			}
		}, 0L, tutorial.getDefaultDelaySecond());

		ProgressBox.put(p, 0);
		ProgressTutorial.put(p, tutorial);
		RunningStatus.put(p, true);
		recovery.setTID(tid);
		recovery.setLocation(p.getLocation());
	}
	

	public void EndTutorial(Player p, Tutorial tutorial, PRecovery recovery)
	{
		
	}

	public void endTask(int tid)
	{
		TeleportTutorial.plugin.getServer().getScheduler().cancelTask(tid);
	}
}
