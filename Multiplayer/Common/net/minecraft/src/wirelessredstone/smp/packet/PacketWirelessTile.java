package net.minecraft.src.wirelessredstone.smp.packet;

import net.minecraft.src.wirelessredstone.data.LoggerRedstoneWireless;
import net.minecraft.src.wirelessredstone.data.LoggerRedstoneWireless.LogLevel;
import net.minecraft.src.wirelessredstone.tileentity.TileEntityRedstoneWireless;


public class PacketWirelessTile extends PacketWireless {
	public PacketWirelessTile() {
		super(PacketIds.WIFI_TILE);
	}
	
	public PacketWirelessTile(String command, TileEntityRedstoneWireless entity)
	{
		super(PacketIds.WIFI_TILE);
		this.setPosition(entity.getBlockCoord(0), entity.getBlockCoord(1), entity.getBlockCoord(2));
		LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).write("[fetchTile]" + this.xPosition + this.yPosition + this.zPosition, LogLevel.INFO);
		this.payload = new PacketPayload(0, 0, 2, 12);
		this.setCommand(command);
		this.setFreq(entity.getFreq());
		this.setPowerDirections(entity.getPowerDirections());
		this.setInDirectlyPowering(entity.getInDirectlyPowering());
	}

	@Override
	public String toString() {
		return this.getCommand()+"("+xPosition+","+yPosition+","+zPosition+")["+this.getFreq()+"]";
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
}
