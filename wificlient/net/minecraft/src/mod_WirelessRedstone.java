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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.minecraft.client.Minecraft;
import net.minecraft.src.wifi.BlockItemRedstoneWirelessR;
import net.minecraft.src.wifi.BlockItemRedstoneWirelessT;
import net.minecraft.src.wifi.BlockRedstoneWireless;
import net.minecraft.src.wifi.BlockRedstoneWirelessOverride;
import net.minecraft.src.wifi.ConfigStoreRedstoneWireless;
import net.minecraft.src.wifi.GuiRedstoneWireless;
import net.minecraft.src.wifi.GuiRedstoneWirelessOverride;
import net.minecraft.src.wifi.GuiRedstoneWirelessR;
import net.minecraft.src.wifi.GuiRedstoneWirelessT;
import net.minecraft.src.wifi.LoggerRedstoneWireless;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessR;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessRenderer;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessT;
import net.minecraft.src.wifi.WirelessRedstone;

/**
 * Wireless Redstone ModLoader initializing class.
 * 
 * @author ali4z
 */
public class mod_WirelessRedstone extends BaseMod
{
	/**
	 * Instance object.
	 */
	public static BaseMod instance;
	
	/**
	 * Constructor sets the instance.
	 */
	public mod_WirelessRedstone() {
		instance = this;
		WirelessRedstone.load();
	}
	
	/**
	 * Contains the mod's version.
	 */
	@Override
	public String getVersion() {
		return "1.5";
	}

	/**
	 * Returns the mod's name.
	 */
	@Override
	public String toString() {
		return "WirelessRedstone "+getVersion();
	}
	
	/**
	 * Loads ModLoader related stuff.<br>
	 * - Load Block textures<br>
	 * - Register Blocks and Tile Entities<br>
	 * - Recipes
	 */
	@Override
	public void load() {
	}
}
