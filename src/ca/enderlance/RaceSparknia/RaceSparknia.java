package ca.enderlance.RaceSparknia;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import me.Blanclour.AmestriaRaceCore.AmestriaRaceCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RaceSparknia extends JavaPlugin implements Listener{
	public static RaceSparknia plugin;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player) sender;
		if(label.equalsIgnoreCase("s") && AmestriaRaceCore.getRace(player).equalsIgnoreCase("Sparknia"))
		{
			if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("vitalize"))
				{
					double health = player.getHealth()/2;
					Player tplayer = player.getServer().getPlayer(args[1]);
					Location loc = player.getLocation();
					Location tloc = tplayer.getLocation();
					World wld = player.getWorld();
					World twld = tplayer.getWorld();
					
					if(player.getHealth() - (health*0.5) > 0)
					{
						wld.strikeLightningEffect(loc);
						twld.strikeLightningEffect(tloc);
						player.sendMessage(ChatColor.YELLOW + "You have given health to " + args[1] + ".");
						tplayer.sendMessage(player.getName() + " has given you life.");
						player.setHealth(player.getHealth() - health*0.5);
						tplayer.setHealth(tplayer.getHealth() + health);
					}
					
					else if(player.getHealth() - (health*0.5) <= 0)
					{
						player.sendMessage(ChatColor.YELLOW + "You are too low on health to do that.");
					}
				}
			}
		}
		return true;
	}
	
	@Deprecated
	public void onToggleSneakEvent(PlayerToggleSneakEvent e)
	{
		Player player = e.getPlayer();
		List<Player> onlinePlayers = Arrays.asList(Bukkit.getServer().getOnlinePlayers());
		Iterator<Player> iterator = onlinePlayers.iterator();
		while (iterator.hasNext()) {
			Player other = (Player)iterator.next();
			if (AmestriaRaceCore.getRace(player).equalsIgnoreCase("Sparknia") && (other != player) && (other.getWorld() == player.getWorld()) && (other.getLocation().distance(player.getLocation()) <= 10))
			{
				other.getWorld().strikeLightning(other.getLocation());
			}
		}
	}
}