package net.minecraft.src.powerc.network;

import net.minecraft.src.wifi.network.PacketUpdate;

public class PacketPowerConfig extends PacketUpdate
{
	public PacketPowerConfig(int packetId)
	{
		super(packetId);
		this.channel = "WIFI-POWERC";
	}
}
