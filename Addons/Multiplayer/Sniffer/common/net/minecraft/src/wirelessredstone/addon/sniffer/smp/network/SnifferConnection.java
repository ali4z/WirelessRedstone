package net.minecraft.src.wirelessredstone.addon.sniffer.smp.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.World;
import net.minecraft.src.wirelessredstone.WirelessRedstone;
import net.minecraft.src.wirelessredstone.addon.sniffer.smp.network.packet.PacketWirelessSnifferEtherCopy;
import net.minecraft.src.wirelessredstone.addon.sniffer.smp.network.packet.PacketWirelessSnifferOpenGui;
import net.minecraft.src.wirelessredstone.addon.sniffer.smp.network.packet.PacketWirelessSnifferSettings;
import net.minecraft.src.wirelessredstone.smp.network.NetworkConnections;
import net.minecraft.src.wirelessredstone.smp.network.packet.PacketIds;

public class SnifferConnection extends NetworkConnections {

	public SnifferConnection(String channel) {
		super(channel);
	}

	@Override
	public void onPacketData(NetworkManager network, String channel,
			byte[] bytes) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(
				bytes));
		try {
			World world = WirelessRedstone.getWorld(network);
			EntityPlayer player = WirelessRedstone.getPlayer(network);
			int packetID = data.read();
			switch (packetID) {
			case PacketIds.ADDON:
				PacketWirelessSnifferSettings pWS = new PacketWirelessSnifferSettings();
				pWS.readData(data);
				PacketHandlerWirelessSniffer.handlePacket(pWS, world, player);
				break;
			case PacketIds.ETHER:
				PacketWirelessSnifferEtherCopy pWSEC = new PacketWirelessSnifferEtherCopy();
				pWSEC.readData(data);
				PacketHandlerWirelessSniffer.handlePacket(pWSEC, world, player);
				break;
			case PacketIds.GUI:
				PacketWirelessSnifferOpenGui pWSG = new PacketWirelessSnifferOpenGui();
				pWSG.readData(data);
				PacketHandlerWirelessSniffer.handlePacket(pWSG, world, player);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onConnect(NetworkManager network) {
	}

	@Override
	public void onDisconnect(NetworkManager network, String message,
			Object[] args) {
	}

	@Override
	public void onPacketData(EntityPlayer entityplayer,
			Packet250CustomPayload packet) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		try {
			World world = entityplayer.worldObj;
			int packetID = data.read();
			switch (packetID) {
			case PacketIds.ADDON:
				PacketWirelessSnifferSettings pWS = new PacketWirelessSnifferSettings();
				pWS.readData(data);
				PacketHandlerWirelessSniffer.handlePacket(pWS, world,
						entityplayer);
				break;
			case PacketIds.ETHER:
				PacketWirelessSnifferEtherCopy pWSEC = new PacketWirelessSnifferEtherCopy();
				pWSEC.readData(data);
				PacketHandlerWirelessSniffer.handlePacket(pWSEC, world,
						entityplayer);
				break;
			case PacketIds.GUI:
				PacketWirelessSnifferOpenGui pWSG = new PacketWirelessSnifferOpenGui();
				pWSG.readData(data);
				PacketHandlerWirelessSniffer.handlePacket(pWSG, world,
						entityplayer);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}