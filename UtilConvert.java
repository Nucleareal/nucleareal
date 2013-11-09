package net.minecraft.src.nucleareal;

public class UtilConvert
{
	public static float[] toRGBF(int color)
	{
		int[] ic = toRGB(color);
		float r = ic[0] / 255F;
		float g = ic[1] / 255F;
		float b = ic[2] / 255F;
		return new float[] { r, g, b };
	}

	public static int[] toRGB(int color)
	{
		int r = (color >> 16) & 0xFF;
		int g = (color >>  8) & 0xFF;
		int b = (color >>  0) & 0xFF;
		return new int[] { r, g, b };
	}
}
