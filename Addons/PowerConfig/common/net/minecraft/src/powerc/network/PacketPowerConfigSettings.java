package net.minecraft.src.powerc.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.wifi.network.PacketIds;
import net.minecraft.src.wifi.network.PacketPayload;
import net.minecraft.src.wifi.network.PacketUpdate;

public class PacketPowerConfigSettings extends PacketPowerConfig {
	public PacketPowerConfigSettings() {
		super(PacketIds.WIFI_POWERC);
	}
	
	public PacketPowerConfigSettings(String command) {
		this();
		PacketPayload p = new PacketPayload(1,1,1);
		p.stringPayload[0] = command;
		p.intPayload[0] = 0;
		p.floatPayload[0] = 0;
		this.payload = p;
	}
	
	public String toString() {
		return this.payload.stringPayload[0]+"("+xPosition+","+yPosition+","+zPosition+")["+this.payload.intPayload[0]+"]";
	}
	
	public int getValue()
	{
		return this.payload.intPayload[0];
	}
	
	public String getCommand()
	{
		return this.payload.stringPayload[0];
	}
	
	public void setPosition(int i, int j, int k) {
		this.xPosition = i;
		this.yPosition = j;
		this.zPosition = k;
	}

	public void setValue(int value) {
		this.payload.intPayload[0] = value;
	}	
}
