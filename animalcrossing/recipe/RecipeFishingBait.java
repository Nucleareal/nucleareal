package net.minecraft.src.nucleareal.animalcrossing.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.Bait;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeFishingBait
{
	private Item bait;

	public RecipeFishingBait(Item bait)
	{
		this.bait = bait;
	}

	public void registerAllRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(bait, 4, Bait.Normal.ordinal()), new Object[]
		{
			" S ",
			"BWB",
			'W', new ItemStack(Item.wheat),
			'B', new ItemStack(Item.bread),
			'S', new ItemStack(Item.slimeBall),
		});
	}

}
