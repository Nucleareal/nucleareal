package net.minecraft.src.nucleareal;

public class NullValue
{
	private NullValue()
	{
	}
	private static final NullValue Instance = new NullValue();
	public static NullValue get()
	{
		return Instance;
	}
}
