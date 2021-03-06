package net.minecraft.src.wirelessredstone.smp.network.packet;

import net.minecraft.src.EntityPlayer;

public interface IPacketHandling {
	public void handleTileEntityPacket(PacketUpdate packet, EntityPlayer var2);

	public void handleGuiPacket(PacketUpdate packet, EntityPlayer var2);
}