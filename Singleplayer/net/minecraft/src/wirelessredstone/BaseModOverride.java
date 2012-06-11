package net.minecraft.src.wirelessredstone;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public interface BaseModOverride {
	public boolean beforeOpenGui(EntityPlayer entityplayer, World world,
			TileEntity tileentity);
}