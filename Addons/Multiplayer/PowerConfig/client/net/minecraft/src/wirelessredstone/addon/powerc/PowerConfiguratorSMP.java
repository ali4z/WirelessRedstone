package net.minecraft.src.wirelessredstone.addon.powerc;

import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.wirelessredstone.addon.powerc.smp.network.NetworkConnection;
import net.minecraft.src.wirelessredstone.addon.powerc.smp.overrides.GuiRedstoneWirelessPowerCOverrideSMP;
import net.minecraft.src.wirelessredstone.data.LoggerRedstoneWireless;
import net.minecraft.src.wirelessredstone.smp.overrides.BaseModOverrideSMP;

public class PowerConfiguratorSMP {
	public static boolean isLoaded = false;

	public static boolean initialize() {
		try {
			registerConnHandler();
			addGuiOverride();
			addBaseModOverride();

			return true;
		} catch (Exception e) {
			LoggerRedstoneWireless.getInstance(
					LoggerRedstoneWireless
							.filterClassName(PowerConfiguratorSMP.class
									.toString())).write(
					"Initialization failed.",
					LoggerRedstoneWireless.LogLevel.WARNING);
			return false;
		}
	}

	private static void registerConnHandler() {
		MinecraftForge.registerConnectionHandler(new NetworkConnection());
	}

	private static void addBaseModOverride() {
		BaseModOverrideSMP baseModOverride = new BaseModOverrideSMP();
		PowerConfigurator.addOverride(baseModOverride);
	}

	private static void addGuiOverride() {
		GuiRedstoneWirelessPowerCOverrideSMP override = new GuiRedstoneWirelessPowerCOverrideSMP();
		PowerConfigurator.guiPowerC.addOverride(override);
	}

}
