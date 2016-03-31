package org.whitehack97.TeleportTutorial.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.whitehack97.TeleportTutorial.api.Tutorial;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;
import org.whitehack97.TeleportTutorial.progress.ProgressTutorial;

public class PlayerListener implements Listener
{
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void PlayerCommand(PlayerCommandPreprocessEvent event)
	{
		if(TeleportTutorial.tutorialCommands.containsKey(event.getMessage()))
		{
			Tutorial tutorial = TeleportTutorial.tutorialCommands.get(event.getMessage());
			if(tutorial.isNeedPermission())
			{
				if(event.getPlayer().hasPermission(TeleportTutorial.tPermission + "." + tutorial.getFiletoString()))
				{
					//ProgressTutorial.StartTutorial(event.getPlayer(), tutorial);
					return;
				}
				else
				{
					/* Need Permission */
					return;
				}
			}
			else
			{
				//ProgressTutorial.StartTutorial(tutorial);
				return;
			}
		}
	}
	
	@EventHandler
	public void RunningTutorial(PlayerMoveEvent event)
	{
		if(ProgressTutorial.RunningStatus.containsKey(event.getPlayer()))
		{
			if(ProgressTutorial.RunningStatus.get(event.getPlayer()).booleanValue())
			{
				event.getPlayer().setFlySpeed(0.0F);
				event.getPlayer().setWalkSpeed(0.0F);
				return;
			}
		}
		else
		{
			return;
		}
	}
}
