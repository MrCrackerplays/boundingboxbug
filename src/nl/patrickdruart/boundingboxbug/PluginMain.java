package nl.patrickdruart.boundingboxbug;

import java.util.function.Predicate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class PluginMain extends JavaPlugin implements CommandExecutor {
	@Override
	public void onEnable() {
		this.getCommand("ray").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		Player player = (Player) sender;
		Location origin = player.getEyeLocation();
		Vector direction = origin.getDirection();
		Predicate<Entity> filter = e -> e != player;
		RayTraceResult result = origin.getWorld().rayTraceEntities(origin, direction, 25, 0, filter);
		if (result == null)
			Bukkit.broadcastMessage("No entity hit");
		else
			Bukkit.broadcastMessage("Hit entity with type:" + result.getHitEntity().getType());
		return true;
	}
}
