package net.minecraft.src.wirelessredstone.addon.clocker;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.wirelessredstone.WirelessRedstone;
import net.minecraft.src.wirelessredstone.addon.clocker.overrides.GuiRedstoneWirelessClockerOverride;
import net.minecraft.src.wirelessredstone.block.BlockRedstoneWireless;
import net.minecraft.src.wirelessredstone.block.BlockRedstoneWirelessOverride;
import net.minecraft.src.wirelessredstone.data.ConfigStoreRedstoneWireless;
import net.minecraft.src.wirelessredstone.data.LoggerRedstoneWireless;
import net.minecraft.src.wirelessredstone.overrides.GuiRedstoneWirelessOverride;
import net.minecraft.src.wirelessredstone.tileentity.TileEntityRedstoneWireless;

public class WirelessClocker {
	public static boolean isLoaded = false;
	public static GuiRedstoneWirelessClocker guiClock;
	public static Block blockClock;

	public static int clockID = 128;

	public static int spriteSidesOff;
	public static int spriteSidesOn;

	public static boolean initialize() {
		try {
			loadConfig();
			loadBlockTextures();

			initBlock();
			initGui();

			addRecipes();
			addBlock();

			return true;
		} catch (Exception e) {
			LoggerRedstoneWireless.getInstance(
					LoggerRedstoneWireless
							.filterClassName(WirelessClocker.class.toString()))
					.write("Initialization failed.",
							LoggerRedstoneWireless.LogLevel.WARNING);
			return false;
		}
	}

	private static void loadBlockTextures() {
		spriteSidesOff = ModLoader.addOverride("/terrain.png",
				"/WirelessSprites/clockerSideOff.png");
		spriteSidesOn = ModLoader.addOverride("/terrain.png",
				"/WirelessSprites/clockerSideOn.png");
	}

	private static void loadConfig() {
		clockID = (Integer) ConfigStoreRedstoneWireless.getInstance("Clocker")
				.get("ID", Integer.class, new Integer(clockID));
	}

	private static void initBlock() {
		blockClock = (new BlockRedstoneWirelessClocker(clockID, 1.0F, 8.0F))
				.setBlockName("clocker");
	}

	private static void initGui() {
		WirelessClocker.guiClock = new GuiRedstoneWirelessClocker();
	}

	private static void addRecipes() {
		ModLoader.addRecipe(new ItemStack(blockClock, 1), new Object[] { "R R",
				" # ", "R R", Character.valueOf('R'), Item.redstoneRepeater,
				Character.valueOf('#'), WirelessRedstone.blockWirelessT });
	}

	public static void addOverrideToClocker(
			BlockRedstoneWirelessOverride override) {
		((BlockRedstoneWireless) WirelessClocker.blockClock)
				.addOverride(override);
	}

	public static void addGuiOverrideToClocker(
			GuiRedstoneWirelessOverride override) {
		if (override instanceof GuiRedstoneWirelessClockerOverride) {
			WirelessClocker.guiClock.addOverride((GuiRedstoneWirelessClockerOverride)override);
		}
		WirelessClocker.guiClock.addOverride(override);
	}

	private static void addBlock() {
		ModLoader.registerBlock(blockClock);
		ModLoader.addName(blockClock, "Wireless Clocker");
		ModLoader.registerTileEntity(TileEntityRedstoneWirelessClocker.class,
				"Wireless Clocker",
				new TileEntityRedstoneWirelessClockerRenderer());
	}

	public static void openGui(TileEntityRedstoneWireless tileentity,
			World world, EntityPlayer entityplayer) {
		WirelessClocker.guiClock.assTileEntity(tileentity);
		ModLoader.openGUI(entityplayer, WirelessClocker.guiClock);
	}
}
