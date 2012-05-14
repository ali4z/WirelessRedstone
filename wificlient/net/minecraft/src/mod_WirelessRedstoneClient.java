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

import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.wifi.BlockRedstoneWirelessOverrideClient;
import net.minecraft.src.wifi.GuiRedstoneWirelessOverrideClient;
import net.minecraft.src.wifi.RedstoneEther;
import net.minecraft.src.wifi.RedstoneEtherOverrideClient;
import net.minecraft.src.wifi.network.NetworkConnection;

public class mod_WirelessRedstoneClient extends NetworkMod
{

	public static NetworkMod instance;
	
	@Override
	public void modsLoaded()
	{
		if(ModLoader.isModLoaded("mod_WirelessRedstone"))
		{
	    	MinecraftForge.registerConnectionHandler(new NetworkConnection());
			
			GuiRedstoneWirelessOverrideClient GUIOverride = new GuiRedstoneWirelessOverrideClient();
			mod_WirelessRedstone.addGuiOverrideToReceiver(GUIOverride);
			mod_WirelessRedstone.addGuiOverrideToTransmitter(GUIOverride);
	
			BlockRedstoneWirelessOverrideClient blockOverride = new BlockRedstoneWirelessOverrideClient();
			mod_WirelessRedstone.addOverrideToReceiver(blockOverride);
			mod_WirelessRedstone.addOverrideToTransmitter(blockOverride);
			
			RedstoneEtherOverrideClient etherOverride = new RedstoneEtherOverrideClient();
			RedstoneEther.getInstance().addOverride(etherOverride);
		}
	}
	
	public mod_WirelessRedstoneClient() {
		instance = this;
	}
	
	@Override
	public String getVersion() {
		return "1.6";
	}

	@Override
	public void load() {
	}

	@Override
	public String toString() {
		return "WirelessRedstone-SMP "+getVersion();
	}

	@Override
	public boolean clientSideRequired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean serverSideRequired()
	{
		// TODO Auto-generated method stub
		return false;
	}

/*	@Override
	public void handlePacket(Packet230ModLoader packet) {
		PacketHandlerRedstoneWireless.handlePacket(packet);
	}*/
}
