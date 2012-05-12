package net.minecraft.src.wifi.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.World;
import net.minecraft.src.mod_WirelessRedstone;
import net.minecraft.src.wifi.BlockRedstoneWireless;
import net.minecraft.src.wifi.TileEntityRedstoneWireless;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessR;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessT;
import net.minecraft.src.wifi.WirelessRedstone;


public class PacketRedstoneEther extends PacketWifiSMP {
	public boolean state = false;
	
	public PacketRedstoneEther() {
		super(PacketIds.WIFI_ETHER);
	}
	
	public PacketRedstoneEther(String command) {
		this();
		this.payload = getPayloadWithCommand(command);
	}
	
	public PacketRedstoneEther(TileEntityRedstoneWireless entity, World world)
	{
		this();
		this.xPosition = entity.getBlockCoord(0);
		this.yPosition = entity.getBlockCoord(1);
		this.zPosition = entity.getBlockCoord(2);
		String command = "";
		if ( entity instanceof TileEntityRedstoneWirelessR) {
			this.state = ((BlockRedstoneWireless)WirelessRedstone.blockWirelessR).getState(world, this.xPosition, this.yPosition, this.zPosition);
			command = "addReceiver";
		} else if ( entity instanceof TileEntityRedstoneWirelessT) {
			this.state = ((BlockRedstoneWireless)WirelessRedstone.blockWirelessT).getState(world, this.xPosition, this.yPosition, this.zPosition);
			command = "addTransmitter";
		}
		if (command != "") this.payload = getPayloadWithCommand(command);
	}
	
	public PacketRedstoneEther(TileEntityRedstoneWireless entity)
	{
		this();
		this.xPosition = entity.getBlockCoord(0);
		this.yPosition = entity.getBlockCoord(1);
		this.zPosition = entity.getBlockCoord(2);
		this.payload = getPayloadWithCommand("fetchTile");
		setFreq(entity.getFreq());
		setPowerDirections(entity.getPowerDirections());
		setInDirectlyPowering(entity.getInDirectlyPowering());
	}
	
	public PacketPayload getPayloadWithCommand(String command)
	{
		PacketPayload p = new PacketPayload(1,1,4,12);
		p.setStringPayload(1, command);
		return p;
	}

	public String toString() {
		return this.payload.getStringPayload(1)+"("+xPosition+","+yPosition+","+zPosition+")["+this.payload.getStringPayload(0)+"]:"+state;
	}
	
	public String getFreq()
	{
		return this.payload.getStringPayload(0);
	}
	
	public String getCommand()
	{
		return this.payload.getStringPayload(1);
	}
	
	public void setPosition(int i, int j, int k) {
		this.xPosition = i;
		this.yPosition = j;
		this.zPosition = k;
	}

	public void setFreq(Object freq) {
		this.payload.setStringPayload(0, freq.toString());
	}

	public void setPowerDirections(boolean[] dir) {
		for (int i = 0; i < 6; i++)
		{
			this.payload.setBoolPayload(i, dir[i]);
		}
	}

	public void setInDirectlyPowering(boolean[] indir) {
		int j = 6;
		for (int i = 0; i < 6; i++)
		{
			this.payload.setBoolPayload(j, indir[i]);
			j++;
		}
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public boolean[] getPowerDirections()
	{
		boolean[] dir = new boolean[6];
		for (int i = 0; i < 6; i++)
		{
			dir[i] = this.payload.getBoolPayload(i);
		}
		return dir;
	}
	
	public boolean[] getInDirectlyPowering()
	{
		boolean[] indir = new boolean[6];
		int j = 6;
		for (int i = 0; i < 6; i++)
		{
			indir[i] = this.payload.getBoolPayload(j);
			j++;
		}
		return indir;
	}

	@Override
	public void readData(DataInputStream datainputstream)
			throws IOException {
		super.readData(datainputstream);
		state = datainputstream.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream dataoutputstream)
			throws IOException {
		super.writeData(dataoutputstream);
		dataoutputstream.writeBoolean(state);
	}
	
}
