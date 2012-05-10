package net.minecraft.src.wifi;

import net.minecraft.src.GuiButton;

public class GuiButtonWifi extends GuiButton
{
	protected String popupText;
	
	public GuiButtonWifi(int i, int j, int k, int l, int i1, String s, String popupText) {
		super(i, j, k, l, i1, s);
		this.popupText = popupText;
	}
	
	public GuiButtonWifi(int i, int j, int k, int l, int i1, String s) {
		super(i, j, k, l, i1, s);
	}

	protected boolean inBounds(int x, int y)
	{
		boolean flag = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
		return flag;
	}
}
