package net.minecraft.src.wifi;

import java.util.Random;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.wifi.BlockRedstoneWirelessOverride;

public class BlockRedstoneWirelessOverrideClient implements BlockRedstoneWirelessOverride {

	@Override
	public void afterBlockRedstoneWirelessAdded(World world, int i, int j, int k) {}

	@Override
	public void afterBlockRedstoneWirelessRemoved(World world, int i, int j,
			int k) {}

	@Override
	public boolean beforeBlockRedstoneWirelessActivated(World world, int i,
			int j, int k, EntityPlayer entityplayer) {
		if ( !world.isRemote ) return false;
		
		return true;
	}

	@Override
	public void afterBlockRedstoneWirelessActivated(World world, int i, int j,
			int k, EntityPlayer entityplayer) {}

	@Override
	public void afterBlockRedstoneWirelessNeighborChange(World world, int i,
			int j, int k, int l) {}

	@Override
	public boolean beforeUpdateRedstoneWirelessTick(World world, int i, int j,
			int k, Random random) {
		if ( !world.isRemote ) return false;
		
		return false;
	}

}