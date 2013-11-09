package net.minecraft.src.nucleareal.animalcrossing.recipe;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.ObjectTriple;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.NuggetToRose;
import net.minecraft.src.nucleareal.animalcrossing.NuggetValue;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeNugget
{
	public RecipeNugget()
	{
	}

	public void RegisterAll()
	{
		Set<String> set = AnimalCrossing.OreDictMap.keySet();
		for(String key : set)
		{
			if(AnimalCrossing.OreDictMap.containsKey(key))
			{
				ObjectTriple<ArrayList<ItemStack>, ArrayList<ItemStack>, Integer> triple = AnimalCrossing.OreDictMap.get(key);
				for(ItemStack nugget : triple.getV1())
				{
					int RoseNum = NuggetToRose.get(NuggetValue.of(triple.getV3().intValue())).ordinal();
					GameRegistry.addShapelessRecipe(nugget, new Object[]
					{
						new ItemStack(AnimalCrossing.RoseItem, 1, RoseNum),
					});
					for(ItemStack ingot : triple.getV2())
					{
						GameRegistry.addShapedRecipe(ingot, new Object[] {
							"XXX", "XXX", "XXX",
							'X', nugget,
						});

						GameRegistry.addShapedRecipe(new ItemStack(AnimalCrossing.RoseItem, 1, RoseNum), new Object[] {
							"X",
							"Y",
							'X', ingot,
							'Y', new ItemStack(AnimalCrossing.RoseItem, 1, RoseColor.Gold.ordinal())
						});
					}
				}
			}
		}
	}
}
