package net.minecraft.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.wifi.BlockRedstoneWireless;
import net.minecraft.src.wifi.BlockRedstoneWirelessOverride;
import net.minecraft.src.wifi.BlockRedstoneWirelessR;
import net.minecraft.src.wifi.BlockRedstoneWirelessT;
import net.minecraft.src.wifi.ConfigStoreRedstoneWireless;
import net.minecraft.src.wifi.LoggerRedstoneWireless;
import net.minecraft.src.wifi.RedstoneEther;
import net.minecraft.src.wifi.RedstoneEtherGui;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessR;
import net.minecraft.src.wifi.TileEntityRedstoneWirelessT;
import net.minecraft.src.wifi.WirelessRedstone;
import net.minecraft.src.wifi.network.NetworkConnection;
import net.minecraft.src.wifi.network.PacketHandlerRedstoneWireless;

public class mod_WirelessRedstone extends NetworkMod
{
	public static NetworkMod instance;
	
	public mod_WirelessRedstone() {
		instance = this;
		load();
	}
	
	@Override
	public void load()
	{
		WirelessRedstone.load();
	}
	
	@Override
	public String getVersion() {
		return "1.5";
	}

	@Override
	public String toString() {
		return "WirelessRedstone "+getVersion();
	}
}
