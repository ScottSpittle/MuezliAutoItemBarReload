package me.ScottSpittle.MuezliAutoItemBarReload;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

	public static main plugin;
	public final BukkitLogger blo = new BukkitLogger(this);
  //public final ConfigManager cfManager = new ConfigManager(this);
	public final EventTrackerListener evenListener = new EventTrackerListener(this);
    public static Permission perms = null;
    public boolean isPlayer = false;
    
	@Override
	public void onDisable(){
		blo.enabled(false);
	}
	
	@Override
	public void onEnable(){
        //ConfigManager cfManager = new ConfigManager(this);
		//register with the plugin manager
		PluginManager pm = getServer().getPluginManager();
		//register player events
		pm.registerEvents(this.evenListener, this);
		//set plugin to this instance.
		plugin = this;
	    //run BukkitLogger class on enable.
		blo.enabled(true);
		//using vault setting up permissions.
		setupPermissions();
	}
    
	//Register Permissions via Vault.
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
    
	//Disables the plugin
	public void disablePlugin(){
		Bukkit.getPluginManager().disablePlugin(this);
	}
	
}
