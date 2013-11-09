package net.minecraft.src.nucleareal.animalcrossing;

public class NuggetToRose
{
	public static RoseColor get(NuggetValue nugget)
	{
		switch(nugget)
		{
			default: return null;
			case Gold: return RoseColor.Gold;
			case Copper: return RoseColor.Copper;
			case Diamond: return RoseColor.Diamond;
			case Iron: return RoseColor.Iron;
			case Redstone: return RoseColor.RedStone;
			case Silver: return RoseColor.Silver;
			case Uranium: return RoseColor.Uranium;
		}
	}
}
