package net.minecraft.src.wifi.network;

import java.util.Arrays;

import net.minecraft.src.ModLoader;
import net.minecraft.src.wifi.LoggerRedstoneWireless;

public class PacketPayload
{
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
	public static int[] concat(int[] first, int[] second) {
		  int[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
	public static float[] concat(float[] first, float[] second) {
		  float[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
	public static boolean[] concat(boolean[] first, boolean[] second) {
		  boolean[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
	private int[] intPayload;
	private float[] floatPayload;
	private String[] stringPayload;
	private boolean[] boolPayload;

	public PacketPayload() {}
	
	public int getIntSize()
	{
		if (this.intPayload != null)
			return this.intPayload.length;
		//LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getIntSize(): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return 0;
	}
	
	public int getFloatSize()
	{
		if (this.floatPayload != null)
			return this.floatPayload.length;
		//LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getIntSize(): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return 0;
	}
	
	public int getStringSize()
	{
		if (this.stringPayload != null)
			return this.stringPayload.length;
		//LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getIntSize(): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return 0;
	}
	
	public int getBoolSize()
	{
		if (this.boolPayload != null)
			return this.boolPayload.length;
		//LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getIntSize(): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return 0;
	}
	
	public void setIntPayload(int index, int newInt) {
		if (this.intPayload != null && index < this.getFloatSize())
			this.intPayload[index] = newInt;
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getIntPayload("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
	}
	
	public void setFloatPayload(int index, float newFloat) {
		if (this.floatPayload != null && index < this.getFloatSize())
			this.floatPayload[index] = newFloat;
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getFloat("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
	}
	
	public void setStringPayload(int index, String newString) {
		if (this.stringPayload != null && index < this.getStringSize())
			this.stringPayload[index] = newString;
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getString("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
	}
	
	public void setBoolPayload(int index, boolean newBool) {
		if (this.boolPayload != null && index < this.getBoolSize())
			this.boolPayload[index] = newBool;
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getBool("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
	}
	
	public int getIntPayload(int index) {
		if (this.intPayload != null && index < this.getIntSize())
			return this.intPayload[index];
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getIntPayload("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return 0;
	}
	
	public float getFloatPayload(int index) {
		if (this.floatPayload != null && index < this.getFloatSize())
			return this.floatPayload[index];
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getFloat("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return 0;
	}
	
	public String getStringPayload(int index) {
		if (this.stringPayload != null && index < this.getStringSize() && this.stringPayload[index] != null)
			return this.stringPayload[index];
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getString("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return "null";
	}
	
	public boolean getBoolPayload(int index) {
		if (this.boolPayload != null && index < this.getBoolSize())
			return this.boolPayload[index];
		LoggerRedstoneWireless.getInstance(LoggerRedstoneWireless.filterClassName(this.getClass().toString())).write("getBool("+index+"): null or OOB!", LoggerRedstoneWireless.LogLevel.WARNING);
		return false;
	}
	
	public PacketPayload(int intSize, int floatSize, int stringSize, int boolSize) {
		if (intSize > 0) intPayload = new int[intSize];
		if (floatSize > 0) floatPayload = new float[floatSize];
		if (stringSize > 0) stringPayload = new String[stringSize];
		if (boolSize > 0) boolPayload = new boolean[boolSize];
	}

	public void append(PacketPayload other) {
		if(other == null)
			return;

		if(other.intPayload.length > 0)
			this.intPayload = concat(this.intPayload, other.intPayload);
		if(other.floatPayload.length > 0)
			this.floatPayload = concat(this.floatPayload, other.floatPayload);
		if(other.stringPayload.length > 0)
			this.stringPayload = concat(this.stringPayload, other.stringPayload);
		if(other.boolPayload.length > 0)
			this.boolPayload = concat(this.boolPayload, other.boolPayload);
	}

	public void append(int[] other) {
		if(other == null || other.length < 0)
			return;

		this.intPayload = concat(this.intPayload, other);
	}

	public void splitTail(IndexInPayload index) {
		PacketPayload payload = new PacketPayload(
				intPayload.length - index.intIndex,
				floatPayload.length - index.floatIndex,
				stringPayload.length - index.stringIndex,
				boolPayload.length - index.boolIndex);

		if(intPayload.length > 0)
			System.arraycopy(intPayload, index.intIndex, payload.intPayload, 0, payload.intPayload.length);
		if(floatPayload.length > 0)
			System.arraycopy(floatPayload, index.floatIndex, payload.floatPayload, 0, payload.floatPayload.length);
		if(stringPayload.length > 0)
			System.arraycopy(stringPayload, index.stringIndex, payload.stringPayload, 0, payload.stringPayload.length);
		if(boolPayload.length > 0)
			System.arraycopy(boolPayload, index.boolIndex, payload.boolPayload, 0, payload.boolPayload.length);
	}
}
