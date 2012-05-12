package net.minecraft.src.wifi.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.mod_WirelessRedstone;
import net.minecraft.src.wifi.BlockRedstoneWireless;
import net.minecraft.src.wifi.LoggerRedstoneWireless;
import net.minecraft.src.wifi.LoggerRedstoneWireless.LogLevel;
import net.minecraft.src.wifi.TileEntityRedstoneWireless;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessR;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessT;
import net.minecraft.src.wifi.WirelessRedstone;


public class PacketRedstoneEther extends PacketWifiSMP {
	//public boolean state = false;
	
	public PacketRedstoneEther() {
		super(PacketIds.WIFI_ETHER);
	}
	
	public PacketRedstoneEther(String command) {
		this();
		this.payload = new PacketPayload(0,0,2,1);
	}
	
	public PacketRedstoneEther(TileEntityRedstoneWireless entity, World world)
	{
		this();
		this.xPosition = entity.getBlockCoord(0);
		this.yPosition = entity.getBlockCoord(1);
		this.zPosition = entity.getBlockCoord(2);
		this.payload = new PacketPayload(0,0,2,1);
		if ( entity instanceof TileEntityRedstoneWirelessR) {
			setState(((BlockRedstoneWireless)WirelessRedstone.blockWirelessR).getState(world, this.xPosition, this.yPosition, this.zPosition));
			setCommand("addReceiver");
		} else if ( entity instanceof TileEntityRedstoneWirelessT) {
			setState(((BlockRedstoneWireless)WirelessRedstone.blockWirelessT).getState(world, this.xPosition, this.yPosition, this.zPosition));
			setCommand("addTransmitter");
		}
	}

	public String toString() {
		return this.getCommand()+"("+xPosition+","+yPosition+","+zPosition+")["+this.getFreq()+"]:"+this.getState();
	}
	
	public String getCommand()
	{
		return this.payload.getStringPayload(0);
	}
	
	public void setCommand(String command)
	{
		this.payload.setStringPayload(0, command);
	}
	
	public String getFreq()
	{
		return this.payload.getStringPayload(1);
	}

	public void setFreq(Object freq) {
		this.payload.setStringPayload(1, freq.toString());
	}
	
	public void setPosition(int i, int j, int k) {
		this.xPosition = i;
		this.yPosition = j;
		this.zPosition = k;
	}

	public void setState(boolean state) {
		this.payload.setBoolPayload(0, state);
	}

	public boolean getState() {
		return this.payload.getBoolPayload(0);
	}
}