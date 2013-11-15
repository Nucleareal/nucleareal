package net.minecraft.src.nucleareal.animalcrossing;

public enum FishType
{
	Huna,
	Koi,
	Medaka,
	Nigoi,
	Nishikigoi,
	Ugui,
	Unknown0,
	Unknown1,
	Unknown2,
	Unknown3,
	Unknown4,
	Unknown5,
	Unknown6,
	Unknown7,
	Unknown8,
	Unknown9,
	UnknownA,
	;

	public static int size()
	{
		return instance.length;
	}

	public static FishType of(int i)
	{
		return instance[i];
	}

	private static FishType[] instance;

	static
	{
		instance = values();
	}

	public static int getRegisterLimit()
	{
		return 128;
	}
}
