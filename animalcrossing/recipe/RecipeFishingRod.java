package net.minecraft.src.nucleareal.animalcrossing.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.RecipeBase;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemGreatFishingRod;

public class RecipeFishingRod extends RecipeBase
{
	private ItemFishingRod _item;

	public RecipeFishingRod(ItemGreatFishingRod cnv)
	{
		_item = cnv;
	}

	@Override
	public void registerAllRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(AnimalCrossing.FishingRodGold), new Object[]{
			"  /",
			" /|",
			"/oJ",
			'/', Item.ingotGold,
			'|', Item.silk,
			'J', Item.redstone,
			'o', Item.goldNugget,
		});
	}

	@Override
	public ObjectPair<Boolean, ItemStack> getResult(InventoryCrafting ic)
	{
		return Failed;
	}
}
