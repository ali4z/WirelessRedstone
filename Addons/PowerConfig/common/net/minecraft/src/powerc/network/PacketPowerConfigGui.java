package net.minecraft.src.powerc.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.wifi.TileEntityRedstoneWireless;
import net.minecraft.src.wifi.network.PacketIds;
import net.minecraft.src.wifi.network.PacketPayload;
import net.minecraft.src.wifi.network.PacketUpdate;

public class PacketPowerConfigGui extends PacketPowerConfig 
{
	public PacketPowerConfigGui()
	{
		super(PacketIds.WIFI_POWERCGUI);
	}
	
	public PacketPowerConfigGui(int x, int y, int z) {
		this();
		xPosition = x;
		yPosition = y;
		zPosition = z;
	}
}
