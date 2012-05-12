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
package net.minecraft.src.wifiremote;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.mod_WirelessRemote;
import net.minecraft.src.wifi.LoggerRedstoneWireless;
import net.minecraft.src.wifi.RedstoneEther;

public class ThreadWirelessRemote implements Runnable {
	protected int i;
	protected int j;
	protected int k;
	protected String freq;
	protected World world;
	protected static EntityPlayer player;
	public static int tc = 0;
	
	
	public ThreadWirelessRemote(int i, int j, int k, String freq, World world) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.freq = freq;
		this.world = world;
	}


	@Override
	public void run() {
		tc++;
			RedstoneEther.getInstance().addTransmitter(
					player.worldObj,
					i,j,k,
					freq
			);
	
	    	RedstoneEther.getInstance().setTransmitterState(
	    			player.worldObj,
					i,j,k,
					freq,
					true
	    	);
	    	
	    	if ( WirelessRemote.pulseTime > 0 ) {
				try {
					Thread.sleep(WirelessRemote.pulseTime);
				} catch (InterruptedException e) {
					LoggerRedstoneWireless.getInstance("WirelessRedstone.Remote").writeStackTrace(e);
				}
	    	}
			
			RedstoneEther.getInstance().remTransmitter(
					player.worldObj,
					i,j,k,
					freq
			);
		tc--;
	}
	
	private boolean playerChangedPosition() {
		if ((int)player.posX == i &&
			(int)player.posY == j &&
			(int)player.posZ == k) {
			return false;
		}
		return true;
	}


	public static void pulse(EntityPlayer entityplayer, World world, String freq) {
		player = entityplayer;
		int x, y, z;
		x = (int)player.posX;
		y = (int)player.posY;
		z = (int)player.posZ;
		if ( tc < WirelessRemote.maxPulseThreads ) {
			Thread thr = new Thread(new ThreadWirelessRemote(x, y, z, freq, world));
			thr.setName("WirelessRemoteThread");
			thr.start();
		}
	}
}