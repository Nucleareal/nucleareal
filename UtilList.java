package net.minecraft.src.nucleareal;

import java.util.List;

import net.minecraft.src.nucleareal.animalcrossing.RoseColor;

public class UtilList
{
	public static <T> int getLimitOrder(List<T> nextList, INumberCreator<T> order, int sign)
	{
		int now = order.getValue(nextList.get(0)) * sign;
		for(T element : nextList)
		{
			int value = order.getValue(element) * sign;
			if(value < now)
			{
				now = value;
			}
		}
		return now * sign;
	}

	public static <T> int getMinValue(List<T> nextList, INumberCreator<T> order)
	{
		return getLimitOrder(nextList, order, +1);
	}

	public static <T> int getMaxValue(List<T> nextList, INumberCreator<T> order)
	{
		return getLimitOrder(nextList, order, -1);
	}
}
