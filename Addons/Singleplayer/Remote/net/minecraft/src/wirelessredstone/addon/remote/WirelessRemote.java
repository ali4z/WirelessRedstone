package net.minecraft.src.wirelessredstone.addon.remote;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraft.src.mod_WirelessRemote;
import net.minecraft.src.wirelessredstone.WirelessRedstone;
import net.minecraft.src.wirelessredstone.data.ConfigStoreRedstoneWireless;
import net.minecraft.src.wirelessredstone.data.LoggerRedstoneWireless;
import net.minecraft.src.wirelessredstone.presentation.GuiRedstoneWireless;

import org.lwjgl.input.Mouse;

public class WirelessRemote {
	public static boolean isLoaded = false;
	public static Item itemRemote;
	public static int remoteon, remoteoff;
	public static int remoteID = 6245;

	public static Remote remote;

	public static boolean mouseDown, wasMouseDown;

	public static long pulseTime = 2500;
	public static boolean duraTogg = true;
	public static int maxPulseThreads = 1;
	static int ticksInGui;

	public static boolean initialize() {
		try {
			ModLoader.setInGameHook(mod_WirelessRemote.instance, true, true);
			loadConfig();
			mouseDown = false;
			wasMouseDown = false;
			itemRemote = (new ItemRedstoneWirelessRemote(remoteID - 256))
					.setItemName("remote");
			loadItemTextures();
			addRecipes();
			addNames();
			return true;
		} catch (Exception e) {
			LoggerRedstoneWireless.getInstance(
					LoggerRedstoneWireless.filterClassName(WirelessRemote.class
							.toString())).write("Initialization failed.",
					LoggerRedstoneWireless.LogLevel.WARNING);
			return false;
		}
	}

	public static void loadItemTextures() {
		remoteoff = ModLoader.addOverride("/gui/items.png",
				"/WirelessSprites/remoteoff.png");
		remoteon = ModLoader.addOverride("/gui/items.png",
				"/WirelessSprites/remote.png");
		itemRemote.setIconIndex(remoteoff);
	}

	public static void addRecipes() {
		ModLoader.addRecipe(new ItemStack(itemRemote, 1), new Object[] { "i",
				"#", Character.valueOf('i'), Block.torchRedstoneActive,
				Character.valueOf('#'), WirelessRedstone.blockWirelessT });
	}

	public static void addNames() {
		ModLoader.addName(itemRemote, "Wireless Remote");
	}

	private static void loadConfig() {
		remoteID = (Integer) ConfigStoreRedstoneWireless.getInstance("Remote")
				.get("ID", Integer.class, new Integer(remoteID));
		duraTogg = (Boolean) ConfigStoreRedstoneWireless.getInstance("Remote")
				.get("Durability", Boolean.class, new Boolean(duraTogg));
		pulseTime = (Long) ConfigStoreRedstoneWireless.getInstance("Remote")
				.get("PulseDurration", Long.class, new Long(pulseTime));
		maxPulseThreads = (Integer) ConfigStoreRedstoneWireless.getInstance(
				"Remote").get("MaxPulseThreads", Integer.class,
				new Integer(maxPulseThreads));
	}

	public static void openGUI(EntityPlayer entityplayer, World world) {
		ModLoader.openGUI(entityplayer, new GuiRedstoneWirelessRemote(
				entityplayer, world));
	}

	public static void activateRemote(World world, EntityPlayer entityplayer) {
		if (remote != null) {
			if (remote.isBeingHeld())
				return;

			deactivateRemote(entityplayer, world);
		}
		remote = new Remote(entityplayer, world);
		remote.activate();
	}

	public static boolean deactivateRemote(EntityPlayer entityplayer,
			World world) {
		if (remote == null) {
			return false;
		} else {
			remote.deactivate();
			remote = null;
			return true;
		}
	}

	public static void processRemote(World world, EntityPlayer entityplayer,
			GuiScreen gui, MovingObjectPosition mop) {
		if (remote != null && !mouseDown)
			deactivateRemote(entityplayer, world);

		if (mouseClicked()
				&& remote == null
				&& entityplayer.inventory.getCurrentItem() != null
				&& entityplayer.inventory.getCurrentItem().getItem() == WirelessRemote.itemRemote
				&& gui != null && gui instanceof GuiRedstoneWireless
				&& !entityplayer.isSneaking() && WirelessRemote.ticksInGui > 0)
			activateRemote(world, entityplayer);
	}

	public static boolean isRemoteOn(EntityPlayer entityplayer, String freq) {
		return remote == null ? false : remote.getFreq() == freq;
	}

	public static boolean mouseClicked() {
		return mouseDown && !wasMouseDown;
	}

	public static void checkMouseClicks() {
		wasMouseDown = mouseDown;
		mouseDown = Mouse.isButtonDown(1);
	}

	public static void tick(Minecraft mc) {
		checkMouseClicks();
		processRemote(mc.theWorld, mc.thePlayer, mc.currentScreen,
				mc.objectMouseOver);

		if (mc.currentScreen == null)
			ticksInGui = 0;
		else
			++ticksInGui;
	}
}
