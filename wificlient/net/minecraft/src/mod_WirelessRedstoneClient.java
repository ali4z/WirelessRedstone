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
import java.util.Properties;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.wifi.BlockRedstoneWirelessOverrideClient;
import net.minecraft.src.wifi.ConfigStoreRedstoneWireless;
import net.minecraft.src.wifi.GuiRedstoneWirelessOverrideClient;
import net.minecraft.src.wifi.WirelessRedstone;
import net.minecraft.src.wifi.network.NetworkConnection;

public class mod_WirelessRedstoneClient extends NetworkMod {
	public static int etherPackID=235;
	public static int guiPackID=236;

	public static NetworkMod instance;
	
	public mod_WirelessRedstoneClient() {
		instance = this;
		loadConfig();
    	MinecraftForge.registerConnectionHandler(new NetworkConnection());
		
		GuiRedstoneWirelessOverrideClient GUIOverride = new GuiRedstoneWirelessOverrideClient();
		WirelessRedstone.addGuiOverrideToReceiver(GUIOverride);
		WirelessRedstone.addGuiOverrideToTransmitter(GUIOverride);

		BlockRedstoneWirelessOverrideClient blockOverride = new BlockRedstoneWirelessOverrideClient();
		WirelessRedstone.addOverrideToReceiver(blockOverride);
		WirelessRedstone.addOverrideToTransmitter(blockOverride);
	}
	
	@Override
	public String getVersion() {
		return "1.5";
	}

	@Override
	public void load() {
	}

	@Override
	public String toString() {
		return "WirelessRedstone "+getVersion();
	}

/*	@Override
	public void handlePacket(Packet230ModLoader packet) {
		PacketHandlerRedstoneWireless.handlePacket(packet);
	}*/

	private static void loadConfig() {
	}
}
