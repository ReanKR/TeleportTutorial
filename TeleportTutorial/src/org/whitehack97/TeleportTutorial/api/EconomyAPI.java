package org.whitehack97.TeleportTutorial.api;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.whitehack97.TeleportTutorial.main.TeleportTutorial;

public class EconomyAPI
{
  public static Economy getEconomy()
  {
    Economy ecoHook = null;

    RegisteredServiceProvider<Economy> economyProvider = TeleportTutorial.plugin.getServer().getServicesManager().getRegistration(Economy.class);
    if (economyProvider != null)
    {
      ecoHook = (Economy)economyProvider.getProvider();
    }
    return ecoHook;
  }
}
