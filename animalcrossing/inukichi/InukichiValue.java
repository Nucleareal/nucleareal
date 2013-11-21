package net.minecraft.src.nucleareal.animalcrossing.inukichi;

public enum InukichiValue
{
	Ticker(0),
	WoodVertical(1),
	WoodHorizonalX(2),
	WoodHorizonalZ(3),
	WoodNone(4),
	Planks(5),
	GlowStone(6),
	Wall(12),
	L1Wall(15),
	L2Wall(14),
	L3Wall(13),
	;

	private int meta;

	private InukichiValue(int meta)
	{
		this.meta = meta;
	}

	public int getMeta()
	{
		return meta;
	}
}
