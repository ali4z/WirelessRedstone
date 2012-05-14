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
package net.minecraft.src.wifi;

import net.minecraft.src.ModLoader;
import net.minecraft.src.wifi.network.PacketHandlerRedstoneWireless;

public class GuiRedstoneWirelessOverrideClient implements GuiRedstoneWirelessOverride {

	@Override
	public boolean beforeFrequencyChange(TileEntityRedstoneWireless entity, Object oldFreq, Object newFreq) {
		if ( ( ModLoader.getMinecraftInstance().theWorld.isRemote ) ) {
			int OLD = Integer.parseInt(oldFreq.toString());
			int NEW = Integer.parseInt(newFreq.toString());
			PacketHandlerRedstoneWireless.PacketHandlerOutput.sendRedstoneEtherPacket("changeFreq", entity.getBlockCoord(0), entity.getBlockCoord(1), entity.getBlockCoord(2), (NEW-OLD), false);
			return true;
		}
		return false;
	}
}