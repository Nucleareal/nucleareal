package net.minecraft.src.nucleareal.animalcrossing;

import java.util.EnumSet;

import cpw.mods.fml.relauncher.Side;

public enum NuggetValue
{
	Gold,
	Silver,
	Copper,
	Iron,
	Diamond,
	Uranium,
	Redstone;

	public static String[] NamesjaJP = new String[]
			{ "金", "銀", "銅", "鉄", "ダイヤ", "ウラン", "赤石" };

	public static EnumSet<NuggetValue> getAllElements()
	{
		return EnumSet.of(Gold, Silver, Copper, Iron, Diamond, Uranium, Redstone);
	}

	public static NuggetValue of(int i)
	{
		return getAllElements().toArray(new NuggetValue[0])[i];
	}

}
