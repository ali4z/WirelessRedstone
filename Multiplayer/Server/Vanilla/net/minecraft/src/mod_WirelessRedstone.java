package net.minecraft.src;

import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.wirelessredstone.RedstoneEther;
import net.minecraft.src.wirelessredstone.WirelessRedstone;
import net.minecraft.src.wirelessredstone.block.BlockRedstoneWireless;
import net.minecraft.src.wirelessredstone.block.BlockRedstoneWirelessOverride;
import net.minecraft.src.wirelessredstone.data.LoggerRedstoneWireless;
import net.minecraft.src.wirelessredstone.network.NetworkConnection;
import net.minecraft.src.wirelessredstone.overrides.RedstoneEtherOverrideServer;

public class mod_WirelessRedstone extends NetworkMod
{
	public static NetworkMod instance;
	
	public mod_WirelessRedstone() {
		instance = this;
    	MinecraftForge.registerConnectionHandler(new NetworkConnection());
		WirelessRedstone.load();
		
		RedstoneEtherOverrideServer etherOverride = new RedstoneEtherOverrideServer();
		RedstoneEther.getInstance().addOverride(etherOverride);
	}
	
	public static void addOverrideToReceiver(BlockRedstoneWirelessOverride override) {
		LoggerRedstoneWireless.getInstance("Wireless Redstone").write("Override added to "+WirelessRedstone.blockWirelessR.getClass().toString()+": "+override.getClass().toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
		((BlockRedstoneWireless)WirelessRedstone.blockWirelessR).addOverride(override);
	}
	
	public static void addOverrideToTransmitter(BlockRedstoneWirelessOverride override) {
		LoggerRedstoneWireless.getInstance("Wireless Redstone").write("Override added to "+WirelessRedstone.blockWirelessT.getClass().toString()+": "+override.getClass().toString(), LoggerRedstoneWireless.LogLevel.DEBUG);
		((BlockRedstoneWireless)WirelessRedstone.blockWirelessT).addOverride(override);
	}
	
	@Override
	public void load()
	{
	}
	
	@Override
	public String getVersion() {
		return "1.6";
	}

	@Override
	public String toString() {
		return "WirelessRedstone-SMP "+getVersion();
	}

	@Override
	public boolean clientSideRequired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean serverSideRequired()
	{
		// TODO Auto-generated method stub
		return true;
	}
}