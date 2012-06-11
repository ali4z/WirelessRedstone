package net.minecraft.src.wirelessredstone.addon.sniffer.data;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.wirelessredstone.data.WirelessDeviceData;

public class WirelessSnifferData extends WirelessDeviceData {

	protected int pageNumber;

	public WirelessSnifferData(String par1Str) {
		super(par1Str);
	}

	public int getPage() {
		return this.pageNumber;
	}

	public void setPage(int pageNumber) {
		this.pageNumber = pageNumber;
		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.pageNumber = nbttagcompound.getInteger("pagenumber");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("pagenumber", this.pageNumber);
	}
}
