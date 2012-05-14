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

import net.minecraft.src.wifi.BlockRedstoneWireless;
import net.minecraft.src.wifi.BlockRedstoneWirelessOverride;
import net.minecraft.src.wifi.GuiRedstoneWirelessOverride;
import net.minecraft.src.wifi.LoggerRedstoneWireless;
import net.minecraft.src.wifi.RedstoneEther;
import net.minecraft.src.wifi.RedstoneEtherOverrideSSP;
import net.minecraft.src.wifi.WirelessRedstone;

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
	 * Adds a Block override to the Receiver.
	 * 
	 * @param override Block override
	 */
	public static void addOverrideToReceiver(BlockRedstoneWirelessOverride override) {
		LoggerRedstoneWireless.getInstance("Wireless Redstone").write("Override added to "+WirelessRedstone.blockWirelessR.getClass().toString()+": "+override.getClass().toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
		((BlockRedstoneWireless)WirelessRedstone.blockWirelessR).addOverride(override);
	}
	
	/**
	 * Adds a Block override to the Transmitter.
	 * 
	 * @param override Block override
	 */
	public static void addOverrideToTransmitter(BlockRedstoneWirelessOverride override) {
		LoggerRedstoneWireless.getInstance("Wireless Redstone").write("Override added to "+WirelessRedstone.blockWirelessT.getClass().toString()+": "+override.getClass().toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
		((BlockRedstoneWireless)WirelessRedstone.blockWirelessT).addOverride(override);
	}

	/**
	 * Adds a GUI override to the Receiver.
	 * 
	 * @param override GUI override
	 */
	public static void addGuiOverrideToReceiver(GuiRedstoneWirelessOverride override) {
		LoggerRedstoneWireless.getInstance("Wireless Redstone").write("Override added to "+WirelessRedstone.guiWirelessR.getClass().toString()+": "+override.getClass().toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
		WirelessRedstone.guiWirelessR.addOverride(override);
	}

	/**
	 * Adds a GUI override to the Transmitter.
	 * 
	 * @param override GUI override
	 */
	public static void addGuiOverrideToTransmitter(GuiRedstoneWirelessOverride override) {
		LoggerRedstoneWireless.getInstance("Wireless Redstone").write("Override added to "+WirelessRedstone.guiWirelessT.getClass().toString()+": "+override.getClass().toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
		WirelessRedstone.guiWirelessT.addOverride(override);
	}
	/**
	 * Constructor sets the instance.
	 */
	public mod_WirelessRedstone() {
		instance = this;
		WirelessRedstone.initialize();
		RedstoneEtherOverrideSSP etherOverride = new RedstoneEtherOverrideSSP();
		RedstoneEther.getInstance().addOverride(etherOverride);
	}
	
	/**
	 * Contains the mod's version.
	 */
	@Override
	public String getVersion() {
		return "1.6";
	}

	/**
	 * Returns the mod's name.
	 */
	@Override
	public String toString() {
		return "WirelessRedstone "+getVersion();
	}
	
	@Override
	public void load() {
	}
}
