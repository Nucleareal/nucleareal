package net.minecraft.src.nucleareal.animalcrossing;

public enum Bait
{
	Normal,
	;

	public static String[] Names_enUS = new String[]
			{ "Normal" };
	public static String[] Names_jaJP = new String[]
			{ "普通の" };

	public static int size()
	{
		return instance.length;
	}

	public static Bait of(int i)
	{
		return instance[i];
	}

	private static Bait[] instance;
	static
	{
		instance = new Bait[values().length];
		instance = values();
	}
}
