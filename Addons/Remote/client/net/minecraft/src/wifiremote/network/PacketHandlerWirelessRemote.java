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
package net.minecraft.src.wifiremote.network;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.wifi.BlockRedstoneWirelessR;
import net.minecraft.src.wifi.LoggerRedstoneWireless;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessR;
import net.minecraft.src.wifi.WirelessRedstone;
import net.minecraft.src.wifi.network.PacketRedstoneEther;
import net.minecraft.src.wifi.network.PacketUpdate;
import net.minecraft.src.wifiremote.MemRedstoneWirelessRemote;


public class PacketHandlerWirelessRemote {
	
	public static void handlePacket(PacketUpdate packet)
	{
		if ( packet instanceof PacketWirelessRemote ) {
			PacketHandlerInput.handleWirelessRemote((PacketWirelessRemote)packet);
		}
	}
	

	private static class PacketHandlerInput {
		private static void handleWirelessRemote(PacketWirelessRemote packet)
		{
			LoggerRedstoneWireless.getInstance("PacketHandlerInput").write("handleWirelessRemotePacket:"+packet.toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
		}
	}

	public static class PacketHandlerOutput
	{
		public static void sendWirelessRemotePacket(EntityPlayer player, String freq, int i, int j, int k, boolean state) {
			PacketWirelessRemoteSettings packet = new PacketWirelessRemoteSettings(freq);
			packet.setState(state);
			packet.setPosition(i, j, k);
			LoggerRedstoneWireless.getInstance("PacketHandlerOutput").write("sendRedstoneEtherPacket:"+packet.toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
			ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Sending Packet: " + packet.channel);
			ModLoader.getMinecraftInstance().getSendQueue().addToSendQueue(packet.getPacket());
		}
	}
}