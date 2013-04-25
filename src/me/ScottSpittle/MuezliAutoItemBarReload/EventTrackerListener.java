/*
   Copyright 2013 Scott Spittle, James Loyd

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package me.ScottSpittle.MuezliAutoItemBarReload;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EventTrackerListener implements Listener {

	public static main plugin;

	public EventTrackerListener(main instance) {
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	// naughty, but there seems to be no alternative... oh bukkit
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();

		if (player.hasPermission("muezli.itemreload.allow")) {
			if (player.getItemInHand().getAmount() == 1) {
				if (player.getInventory().contains(event.getBlockPlaced().getTypeId())) {

					PlayerInventory inv = player.getInventory();
					int slot = 0;

					for (slot = 9; slot < inv.getSize(); slot++) {
						if (inv.getItem(slot) != null && inv.getItem(slot).getType() == event.getBlockPlaced().getType()) {

							inv.setItemInHand(inv.getItem(slot));
							inv.setItem(slot, new ItemStack(Material.AIR, 1));
							player.updateInventory();
							break;
						}
					}
				}
			}
		}
		return;
	}
}