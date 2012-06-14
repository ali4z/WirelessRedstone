/*    
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package net.minecraft.src;

import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.wirelessredstone.WirelessRedstone;
import net.minecraft.src.wirelessredstone.ether.RedstoneEther;
import net.minecraft.src.wirelessredstone.smp.network.NetworkConnection;
import net.minecraft.src.wirelessredstone.smp.overrides.BlockRedstoneWirelessOverrideSMP;
import net.minecraft.src.wirelessredstone.smp.overrides.GuiRedstoneWirelessInventoryOverrideSMP;
import net.minecraft.src.wirelessredstone.smp.overrides.RedstoneEtherOverrideSMP;

public class mod_WirelessRedstoneSMP extends NetworkMod {

	public static NetworkMod instance;

	@Override
	public void modsLoaded() {
		if (ModLoader.isModLoaded("mod_WirelessRedstone")) {
			MinecraftForge.registerConnectionHandler(new NetworkConnection());

			GuiRedstoneWirelessInventoryOverrideSMP GUIOverride = new GuiRedstoneWirelessInventoryOverrideSMP();
			WirelessRedstone.addGuiOverrideToReceiver(GUIOverride);
			WirelessRedstone.addGuiOverrideToTransmitter(GUIOverride);

			BlockRedstoneWirelessOverrideSMP blockOverride = new BlockRedstoneWirelessOverrideSMP();
			WirelessRedstone.addOverrideToReceiver(blockOverride);
			WirelessRedstone.addOverrideToTransmitter(blockOverride);

			RedstoneEtherOverrideSMP etherOverrideSMP = new RedstoneEtherOverrideSMP();
			RedstoneEther.getInstance().addOverride(etherOverrideSMP);
		}
	}

	@Override
	public String getPriorities() {
		return "after:mod_WirelessRedstone";
	}

	public mod_WirelessRedstoneSMP() {
		instance = this;
	}

	@Override
	public String getVersion() {
		return "1.6";
	}

	@Override
	public void load() {
	}

	@Override
	public boolean clientSideRequired()
	{
		return true;
	}

	@Override
	public boolean serverSideRequired()
	{
		return false;
	}
}
