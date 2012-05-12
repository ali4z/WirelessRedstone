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

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.wifi.network.PacketHandlerRedstoneWireless;
import net.minecraft.src.wifi.network.PacketHandlerRedstoneWireless.PacketHandlerOutput;

public class BlockRedstoneWirelessR extends BlockRedstoneWireless {	
	private boolean initialSchedule;
	public BlockRedstoneWirelessR(int i, float hardness, float resistance) {
		super(i, hardness, resistance);
		setStepSound(Block.soundMetalFootstep);
		setTickRandomly(true);
		initialSchedule = true;
	}

	/*
	 * Class specific
	 */
	public boolean hasTicked() {
		return !this.initialSchedule;
	}
	
	/*
	 * Overrides of BlockRedstoneWireless
	 */
	
	// Changes the frequency.	
	@Override
	public void changeFreq(World world, int i, int j, int k, String oldFreq, String freq) {
		RedstoneEther.getInstance().remReceiver(world,i,j,k, oldFreq);
		RedstoneEther.getInstance().addReceiver(world,i,j,k, freq);
		updateTick(world, i, j, k, null);
	}
	


	@Override
	protected void onBlockRedstoneWirelessAdded(World world, int i, int j, int k) {
        RedstoneEther.getInstance().addReceiver(world,i,j,k, getFreq(world,i,j,k));
        world.notifyBlocksOfNeighborChange(i, j, k, blockID);

        updateTick(world, i, j, k, null);
	}


	@Override
	protected void onBlockRedstoneWirelessRemoved(World world, int i, int j, int k) {
        RedstoneEther.getInstance().remReceiver(world,i,j,k, getFreq(world,i,j,k));
        world.notifyBlocksOfNeighborChange(i, j, k, blockID);
	}


	@Override
	protected boolean onBlockRedstoneWirelessActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);

		if ( tileentity instanceof TileEntityRedstoneWirelessR ) {
			PacketHandlerRedstoneWireless.PacketHandlerOutput.sendGuiPacketTo((EntityPlayerMP)entityplayer, (TileEntityRedstoneWirelessR)tileentity, 0);
		}
		
		return true;
	}


	@Override
	protected void onBlockRedstoneWirelessNeighborChange(World world, int i, int j, int k, int l) {
    	if ( l == this.blockID ) return;
    	
		updateTick(world, i, j, k, null);
	}


	@Override
	protected int getBlockRedstoneWirelessTexture(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		if ( getState(iblockaccess.getBlockMetadata(i, j, k)) ) {
			if(l == 0 || l == 1) {
				return WirelessRedstone.spriteTopOn;
			}
			return WirelessRedstone.spriteROn;
		} else {
			return getBlockRedstoneWirelessTextureFromSide(l);
		}
	}


	@Override
	protected int getBlockRedstoneWirelessTextureFromSide(int l) {
		if(l == 0 || l == 1) {
			return WirelessRedstone.spriteTopOff;
		}
		return WirelessRedstone.spriteROff;
	}


	@Override
	protected TileEntityRedstoneWireless getBlockRedstoneWirelessEntity() {
		return new TileEntityRedstoneWirelessR();
	}
	
	@Override
	protected void updateRedstoneWirelessTick(World world, int i, int j, int k, Random random) {
		if ( initialSchedule ) initialSchedule = false;
		if ( world == null ) return;
		String freq = getFreq(world,i,j,k);
		
		boolean oldState = getState(world,i,j,k);
		boolean newState = RedstoneEther.getInstance().getFreqState(world, freq);
		if ( newState != oldState ) {
			setState(world,i,j,k,newState);
			world.markBlockNeedsUpdate(i, j, k);
			notifyNeighbors(world,i,j,k);
			
			TileEntity entity = world.getBlockTileEntity(i, j, k);
			if ( entity instanceof TileEntityRedstoneWireless )
				PacketHandlerRedstoneWireless.PacketHandlerOutput.sendEtherTileToAll((TileEntityRedstoneWireless)entity,world,0);
		}
	}
	
	@Override
	protected boolean isRedstoneWirelessPoweringTo(World world, int i, int j, int k, int l) {

    	TileEntity entity = world.getBlockTileEntity(i, j, k);
    	if ( entity instanceof TileEntityRedstoneWireless ) {
	    	return ( ((TileEntityRedstoneWireless)entity).isPoweringDirection(l) && getState(world,i,j,k) );
    	}
    	return false;
	}

	@Override
	protected boolean isRedstoneWirelessIndirectlyPoweringTo(World world, int i, int j, int k, int l) {
    	TileEntity entity = world.getBlockTileEntity(i, j, k);
    	if ( entity instanceof TileEntityRedstoneWireless ) {
    		if ( !((TileEntityRedstoneWireless)entity).isPoweringIndirectly(l) )
    			return false;
    		else
    	    	return isPoweringTo(world,i,j,k,l);
		}
    	return false;
	}
}