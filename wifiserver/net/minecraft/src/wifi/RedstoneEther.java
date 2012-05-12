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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import net.minecraft.src.*;
import net.minecraft.src.wifi.network.PacketHandlerRedstoneWireless;

public class RedstoneEther {
	private Map<String,RedstoneEtherFrequency> ether;
	private int currentWorldHash = 0;
	private static RedstoneEther instance;
	private JFrame gui;
	
	private RedstoneEther() {
		ether = new HashMap<String,RedstoneEtherFrequency>();
	}
	
	public static RedstoneEther getInstance() {
		if ( instance == null ) {
			instance = new RedstoneEther();
		}
		return instance;
	}
	
	public void assGui(JFrame gui) {
		this.gui = gui;
	}
	
	public synchronized void addTransmitter(World world, int i, int j, int k, String freq) {
		synchronized(this) {
			try {
				LoggerRedstoneWireless.getInstance("RedstoneEther").write("addTransmitter(world, "+i+", "+j+", "+k+", "+freq+")", LoggerRedstoneWireless.LogLevel.DEBUG);
				checkWorldHash(world);
				if ( !freqIsset(freq) ) createFreq(freq);
		
				RedstoneEtherNode node = new RedstoneEtherNode(i,j,k);
				node.freq = freq;
				ether.get(freq).addTransmitter(node);
				
				PacketHandlerRedstoneWireless.PacketHandlerOutput.sendEtherNodeTileToAll(node,0);
				
				if ( gui != null ) gui.repaint();
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
		}
	}
	public synchronized void remTransmitter(World world, int i, int j, int k, String freq) {
		synchronized(this) {
			try {
				LoggerRedstoneWireless.getInstance("RedstoneEther").write("remTransmitter(world, "+i+", "+j+", "+k+", "+freq+")", LoggerRedstoneWireless.LogLevel.DEBUG);
		
				checkWorldHash(world);
				if ( freqIsset(freq) ) {
					ether.get(freq).remTransmitter(world, i, j, k);
					if ( ether.get(freq).count() == 0 )
						ether.remove(freq);
				}
				if ( gui != null ) gui.repaint();
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
		}
	}
	public synchronized void addReceiver(World world, int i, int j, int k, String freq) {
		synchronized(this) {
			try {			
				LoggerRedstoneWireless.getInstance("RedstoneEther").write("addReceiver(world, "+i+", "+j+", "+k+", "+freq+")", LoggerRedstoneWireless.LogLevel.DEBUG);
		
				checkWorldHash(world);
				if ( !freqIsset(freq) ) createFreq(freq);
				
				RedstoneEtherNode node = new RedstoneEtherNode(i,j,k);
				node.freq = freq;
				ether.get(freq).addReceiver(node);
				
				PacketHandlerRedstoneWireless.PacketHandlerOutput.sendEtherNodeTileToAll(node,0);
				
				if ( gui != null ) gui.repaint();
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
		}
	}
	public synchronized void remReceiver(World world, int i, int j, int k, String freq) {
		synchronized(this) {
			try {	
				LoggerRedstoneWireless.getInstance("RedstoneEther").write("remReceiver(world, "+i+", "+j+", "+k+", "+freq+")", LoggerRedstoneWireless.LogLevel.DEBUG);
		
				checkWorldHash(world);
				if ( freqIsset(freq) ) {
					ether.get(freq).remReceiver(i, j, k);
					if ( ether.get(freq).count() == 0 )
						ether.remove(freq);
				}
				if ( gui != null ) gui.repaint();
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
		}
	}
	
	private void checkWorldHash(World world) {
		if ( world != null && world.hashCode() != currentWorldHash ) {
			ether = new HashMap<String,RedstoneEtherFrequency>();
			currentWorldHash = world.hashCode();
		}
		if ( gui != null ) gui.repaint();
	}
	
	private void createFreq(String freq) {
		ether.put(freq, new RedstoneEtherFrequency());
	}
	private boolean freqIsset(Object freq) {
		return ether.containsKey(freq);
	}
	
	public synchronized boolean getFreqState(World world, String freq) {	
		synchronized(this) {
			if ( !freqIsset(freq) ) 
				return false;
			else
				return ether.get(freq).getState(world);
		}
	}
	
	public synchronized void setTransmitterState(World world, int i, int j, int k, String freq, boolean state) {
		synchronized(this) {
			try {
				LoggerRedstoneWireless.getInstance("RedstoneEther").write("setTransmitterState(world, "+i+", "+j+", "+k+", "+freq+", "+state+")", LoggerRedstoneWireless.LogLevel.DEBUG);
	
				
				if ( freqIsset(freq) ) {
					ether.get(freq).setTransmitterState(world, i, j, k, state);
					PacketHandlerRedstoneWireless.PacketHandlerOutput.sendEtherFrequencyTilesToAll(
							ether.get(freq),
							WirelessRedstone.stateUpdate
					);
				}
				
				
				if ( gui != null ) gui.repaint();
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
		}
	}
	
	public synchronized int[] getClosestActiveTransmitter(int i, int j, int k, String freq) {
		synchronized(this) {
			if ( freqIsset(freq) )
				return ether.get(freq).getClosestActiveTransmitter(i, j, k);
			else return null;
		}
	}
	public synchronized int[] getClosestTransmitter(int i, int j, int k, String freq) {
		synchronized(this) {
			if ( freqIsset(freq) )
				return ether.get(freq).getClosestTransmitter(i, j, k);
			else return null;
		}
	}
	
	public static float pythagoras(int[] a, int[] b ) {
		double x = 0;
		if ( a.length <= b.length ) {
			for ( int n = 0; n < a.length; n++ ) {
				x += Math.pow((a[n]-b[n]), 2);
			}
		} else {
			for ( int n = 0; n < b.length; n++ ) {
				x += Math.pow((a[n]-b[n]), 2);
			}
		}
		return (float)Math.sqrt(x);
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<RedstoneEtherNode> getRXNodes() {
		synchronized(this) {
			List<RedstoneEtherNode> list = new LinkedList<RedstoneEtherNode>();
			try {
				HashMap<String,RedstoneEtherFrequency> etherClone = (HashMap<String, RedstoneEtherFrequency>)((HashMap<String, RedstoneEtherFrequency>)ether).clone();
				for ( RedstoneEtherFrequency freq : etherClone.values() )
					list.addAll( freq.rxs.values());
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
			return list;
		}
	}
	@SuppressWarnings("unchecked")
	public synchronized List<RedstoneEtherNode> getTXNodes() {
		synchronized(this) {
			List<RedstoneEtherNode> list = new LinkedList<RedstoneEtherNode>();
			try {
				HashMap<String,RedstoneEtherFrequency> etherClone = (HashMap<String, RedstoneEtherFrequency>)((HashMap<String, RedstoneEtherFrequency>)ether).clone();
				for ( RedstoneEtherFrequency freq : etherClone.values() )
					list.addAll(freq.txs.values());
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
			return list;
		}
	}
	@SuppressWarnings("unchecked")
	public synchronized Map<String,Integer> getLoadedFrequencies() {
		synchronized(this) {
			Map<String,Integer> list = new HashMap<String,Integer>();
			try {
				HashMap<String,RedstoneEtherFrequency> etherClone = (HashMap<String, RedstoneEtherFrequency>)((HashMap<String, RedstoneEtherFrequency>)ether).clone();
				for ( String freq : etherClone.keySet() )
					list.put(freq, etherClone.get(freq).count());
			} catch ( Exception e) {
				LoggerRedstoneWireless.getInstance("WirelessRedstone: "+this.getClass().toString()).writeStackTrace(e);
			}
			return list;
		}
	}
	
	public static boolean isBlockLoaded(World world, int i, int j, int k) {
		LoggerRedstoneWireless.getInstance("RedstoneEther").write(
				"isBlockLoaded(world, "+i+", "+j+", "+k+
				"):["+(world.getBlockId(i, j, k) != 0)+"&"+(world.getBlockTileEntity(i, j, k) != null)+"]",
		LoggerRedstoneWireless.LogLevel.DEBUG);
		return ( 
				world.getBlockId(i, j, k) != 0 &&
				world.getBlockTileEntity(i, j, k) != null
		);
	}
}