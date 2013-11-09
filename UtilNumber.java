package net.minecraft.src.nucleareal;

public class UtilNumber
{
	public static int getParsedInt(String s)
	{
		int i = Integer.MIN_VALUE;
		try
		{
			i = Integer.valueOf(s);
		}
		catch(Exception e)
		{
			System.out.println("Failed Parse "+s+" to Integer.");
		}
		return i;
	}
}
