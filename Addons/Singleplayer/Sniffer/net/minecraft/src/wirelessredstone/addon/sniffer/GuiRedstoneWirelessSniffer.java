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
package net.minecraft.src.wirelessredstone.addon.sniffer;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.World;
import net.minecraft.src.wirelessredstone.WirelessRedstone;
import net.minecraft.src.wirelessredstone.addon.sniffer.data.WirelessSnifferData;
import net.minecraft.src.wirelessredstone.data.LoggerRedstoneWireless;
import net.minecraft.src.wirelessredstone.data.WirelessDeviceData;
import net.minecraft.src.wirelessredstone.ether.RedstoneEther;
import net.minecraft.src.wirelessredstone.presentation.GuiButtonBoolean;
import net.minecraft.src.wirelessredstone.presentation.GuiButtonWirelessExit;
import net.minecraft.src.wirelessredstone.presentation.GuiRedstoneWireless;
import net.minecraft.src.wirelessredstone.presentation.GuiRedstoneWirelessDevice;

import org.lwjgl.opengl.GL11;

public class GuiRedstoneWirelessSniffer extends GuiRedstoneWirelessDevice {
	private int nodeSize = 4;
	private int pageWidth = 50;
	private int pageHeight = 30;
	private ThreadWirelessSniffer thr;
	GuiButtonBoolean nextButton;
	GuiButtonBoolean prevButton;

	public GuiRedstoneWirelessSniffer(WirelessSnifferData data, World world, EntityPlayer entityplayer) {
		super();
		this.world = world;
		this.entityplayer = entityplayer;
		ItemStack itemstack = entityplayer.getCurrentEquippedItem();
		this.wirelessDeviceData = (WirelessSnifferData) data;
		xSize = 256;
		ySize = 200;
		thr = new ThreadWirelessSniffer(this);
	}
	
	@Override
	protected void addControls() {
		int currentPage = this.getPage();
		nextButton = new GuiButtonBoolean(0, (width / 2) + 40,
				(height / 2) + 75, 40, 20, "Next", true, "Next Page");
		prevButton = new GuiButtonBoolean(1, (width / 2) - 80,
				(height / 2) + 75, 40, 20, "Prev", true, "Previous Page");
		nextButton.enabled = (currentPage >= 0 && currentPage < WirelessRedstone.maxEtherFrequencies
				/ (pageWidth * pageHeight));
		prevButton.enabled = (currentPage > 0 && currentPage <= WirelessRedstone.maxEtherFrequencies
				/ (pageWidth * pageHeight));
		controlList.add(new GuiButtonWirelessExit(100, (((width - xSize) / 2)
				+ xSize - 13 - 1), (((height - ySize) / 2) + 1)));
		controlList.add(nextButton);
		controlList.add(prevButton);
	}

	private int getPage() {
		return ((WirelessSnifferData) this.wirelessDeviceData).getPage();
	}

	private void setPage(int pageNumber) {
		((WirelessSnifferData) this.wirelessDeviceData)
				.setPage(pageNumber);
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		int page = this.getPage();
		int oldPage = this.getPage();
		switch (guibutton.id) {
		case 0:
			++page;
			break;
		case 1:
			--page;
			break;
		case 100:
			close();
			break;
		}
		if (page > 6)
			page = 6;
		if (page < 0)
			page = 0;
		if (oldPage != page) {
			if ((oldPage - page) == -1) {
				this.setPage(oldPage + 1);
			} else {
				this.setPage(oldPage - 1);
			}
		}
		if (nextButton.enabled
				&& this.getPage() == WirelessRedstone.maxEtherFrequencies
						/ (pageWidth * pageHeight)) {
			nextButton.enabled = false;
		} else
			nextButton.enabled = true;
		if (prevButton.enabled && this.getPage() == 0) {
			prevButton.enabled = false;
		} else
			prevButton.enabled = true;
	}
	
	@Override
	protected String getBackgroundImage() {
		return "/gui/wifi_xlarge.png";
	}

	private void drawFrequencies(int i, int j) {
		int x, y;
		int c = (pageWidth * pageHeight) * this.getPage();
		for (int n = 0; n < pageHeight; n++) {
			for (int m = 0; m < pageWidth; m++) {
				x = i + (nodeSize * m) + m;
				y = j + (nodeSize * n) + n;
				if (c <= WirelessRedstone.maxEtherFrequencies && c >= 0) {
					if (RedstoneEther.getInstance().getFreqState(
							ModLoader.getMinecraftInstance().theWorld,
							Integer.toString(c)))
						drawRect(x, y, x + nodeSize, y + nodeSize, 0xff00ff00);
					else {
						drawRect(x, y, x + nodeSize, y + nodeSize, 0xffff0000);
					}
				}
				c++;
			}
		}
	}

	@Override
	public void onGuiClosed() {
		thr.running = false;
		// this.sniffer.killSniffer();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j, float f) {
		drawFrequencies(4, 24);
		fontRenderer
				.drawString(
						"Wireless Sniffer",
						(xSize / 2)
								- (fontRenderer
										.getStringWidth("Wireless Sniffer") / 2),
						6, 0x404040);
		String drawPage = "Page [" + (this.getPage() + 1) + "]";
		fontRenderer.drawString(drawPage,
				(xSize / 2) - (fontRenderer.getStringWidth(drawPage) / 2),
				(height / 2) + 62, 0x00000000);
	}

	@Override
	protected Object getFreq() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setFreq(String freq) {
		// TODO Auto-generated method stub
		
	}
}