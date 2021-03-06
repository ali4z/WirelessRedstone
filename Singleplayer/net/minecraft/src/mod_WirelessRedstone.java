/*    
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package net.minecraft.src;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.wirelessredstone.WirelessRedstone;

/**
 * Wireless Redstone ModLoader initializing class.
 * 
 * @author ali4z
 */
public class mod_WirelessRedstone extends BaseMod {

	/**
	 * Instance object.
	 */
	public static BaseMod instance;

	/**
	 * Initialize WirelessRedstone once
	 */
	@Override
	public void modsLoaded() {
		if (!WirelessRedstone.isLoaded)
			WirelessRedstone.isLoaded = WirelessRedstone.initialize();
	}

	/**
	 * Constructor sets the instance.
	 */
	public mod_WirelessRedstone() {
		instance = this;
	}

	/**
	 * Contains the mod's version.
	 */
	@Override
	public String getVersion() {
		return "1.6";
	}

	@Override
	public boolean renderWorldBlock(RenderBlocks var1, IBlockAccess var2,
			int var3, int var4, int var5, Block var6, int var7) {
		return true;// RenderBlockRedstoneWireless.renderBlockRedstoneWireless(var1,
					// var2, var3, var4, var5, var6, var7);
	}

	/**
	 * Returns the mod's name.
	 */
	@Override
	public String toString() {
		return "WirelessRedstone " + getVersion();
	}

	@Override
	public void load() {
	}

	private static GuiScreen lastGuiOpen;

	public boolean onTickInGame(float tick, Minecraft mc) {
		if (mc.currentScreen == null) {
			lastGuiOpen = null;
		}
		return true;
	}

	public boolean onTickInGUI(float tick, Minecraft mc, GuiScreen gui) {
		if ((gui instanceof GuiContainerCreative)
				&& !(lastGuiOpen instanceof GuiContainerCreative)) {
			List<Block> creativeBlockList = WirelessRedstone
					.getCreativeBlockList();
			if (creativeBlockList != null) {
				Container container = ((GuiContainer) gui).inventorySlots;
				List list = ((ContainerCreative) container).itemList;
				for (int i = 0; i < creativeBlockList.size(); i++) {
					list.add(new ItemStack(creativeBlockList.get(i), 1));
				}
			}
			lastGuiOpen = gui;
		}
		return true;
	}
}
