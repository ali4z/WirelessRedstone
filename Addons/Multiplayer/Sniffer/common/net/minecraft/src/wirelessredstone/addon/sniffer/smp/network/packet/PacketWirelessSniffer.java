package net.minecraft.src.wirelessredstone.addon.sniffer.smp.network.packet;

import net.minecraft.src.wirelessredstone.smp.network.packet.PacketWireless;

public class PacketWirelessSniffer extends PacketWireless {

	public PacketWirelessSniffer(int packetId) {
		super(packetId);
		this.channel = "WR-SNIFFER";
	}
}
