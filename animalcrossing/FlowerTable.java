package net.minecraft.src.nucleareal.animalcrossing;

import static net.minecraft.src.nucleareal.animalcrossing.RoseColor.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.src.nucleareal.ReplaceablePair;

public class FlowerTable
{
	private FlowerTable()
	{
		RoseMap = new HashMap<String, List<RoseColor>>();
		RegisterRose(Red, Red, Black); RegisterRose(Red, White, Pink); RegisterRose(Red, Yellow, Orange);
		RegisterRose(Red, Pink); RegisterRose(Red, Orange); RegisterRose(Red, Purple);
		RegisterRose(Red, Black); RegisterRose(Red, Blue); RegisterRose(White, White, Purple);
		RegisterRose(White, Yellow); RegisterRose(White, Pink); RegisterRose(White, Orange);
		RegisterRose(White, Purple); RegisterRose(White, Black); RegisterRose(White, Blue);
		RegisterRose(Yellow, Yellow); RegisterRose(Yellow, Pink); RegisterRose(Yellow, Orange, Red);
		RegisterRose(Yellow, Purple); RegisterRose(Yellow, Black); RegisterRose(Yellow, Blue);
		RegisterRose(Pink, Pink); RegisterRose(Pink, Orange); RegisterRose(Pink, Purple);
		RegisterRose(Pink, Black); RegisterRose(Pink, Blue); RegisterRose(Orange, Orange);
		RegisterRose(Orange, Purple); RegisterRose(Orange, Black); RegisterRose(Orange, Blue);
		RegisterRose(Purple, Purple); RegisterRose(Purple, Black, Blue); RegisterRose(Purple, Blue);
		RegisterRose(Black, Black, Red); RegisterRose(Black, Blue, Purple); RegisterRose(Blue, Blue, Blue);
		RegisterRose(Gold, Gold, Black);

		RegisterRose(Silver, Silver);
		RegisterRose(Copper, Copper);
		RegisterRose(Diamond, Diamond);
		RegisterRose(RedStone, RedStone);
		RegisterRose(Uranium, Uranium);
		RegisterRose(Tin, Tin);
		RegisterRose(Iron, Iron);
	}
	private static FlowerTable instance = new FlowerTable();
	public static FlowerTable get()
	{
		return instance;
	}

	public HashMap<String, List<RoseColor>> RoseMap;

	private void RegisterRoseGold(RoseColor gold, RoseColor ... r2)
	{
		for(RoseColor c1 : r2)
		{
			String Pair = UtilRose.getPairString(gold, c1);
			List<RoseColor> NewColorList = new ArrayList<RoseColor>();
			NewColorList.add(c1);
			RoseMap.put(Pair, NewColorList);
		}
	}

	private void RegisterRose(RoseColor C1, RoseColor C2, RoseColor ... NewColors)
	{
		String Pair = UtilRose.getPairString(C1, C2);
		List<RoseColor> NewColorList = new ArrayList<RoseColor>();
		NewColorList.add(C1); if(C1.ordinal() != C2.ordinal()) NewColorList.add(C2);
		for(RoseColor NewColor : NewColors) { NewColorList.add(NewColor); }
		RoseMap.put(Pair, NewColorList);
	}

	public List<RoseColor> getGenList(RoseColor r1, RoseColor r2)
	{
		List<RoseColor> list = new ArrayList<RoseColor>();
		if(RoseMap.containsKey(UtilRose.getPairString(r1, r2)))
		{
			list = RoseMap.get(UtilRose.getPairString(r1, r2));
		}
		if(RoseMap.containsKey(UtilRose.getPairString(r2, r1)))
		{
			list = RoseMap.get(UtilRose.getPairString(r2, r1));
		}
		return list;
	}
}
