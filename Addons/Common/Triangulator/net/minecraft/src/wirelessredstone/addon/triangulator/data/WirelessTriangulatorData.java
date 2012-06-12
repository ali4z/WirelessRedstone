package net.minecraft.src.wirelessredstone.addon.triangulator.data;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.wirelessredstone.data.WirelessDeviceData;

public class WirelessTriangulatorData extends WirelessDeviceData {

	public WirelessTriangulatorData(String index, int id, String name,
			World world, EntityPlayer entityplayer) {
		super(index, id, name, world, entityplayer);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
	}
}
