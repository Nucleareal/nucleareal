package net.minecraft.src.nucleareal.animalcrossing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.nucleareal.INumberCreator;
import net.minecraft.src.nucleareal.UtilList;

public class UtilRose
{
	public static RoseColor getInRandomRose(List<RoseColor> nextList, Random rand)
	{
		INumberCreator<RoseColor> getter = new INumberCreator<RoseColor>()
		{
			@Override
			public int getValue(RoseColor source)
			{
				return source.getRarity();
			}
		};

		int minRarity = UtilList.getMinValue(nextList, getter);
		int maxRarity = UtilList.getMaxValue(nextList, getter);

		RoseColor c = nextList.get(0);

		for(int i = minRarity; i <= maxRarity; i++)
		{
			List<RoseColor> list = new ArrayList<RoseColor>();
			for(RoseColor e : nextList)
			{
				if(e.getRarity() == i) list.add(e);
			}
			boolean flag = rand.nextInt(8) != 0; //True: Downer   False: Upper
			flag |= i == maxRarity;

			if(flag && list.size() > 0)
			{
				c = list.get(rand.nextInt(list.size()));
				break;
			}
		}

		return c;
	}

	public static String getPairString(RoseColor C1, RoseColor C2)
	{
		return C1.toString() + "|" + C2.toString();
	}

}
