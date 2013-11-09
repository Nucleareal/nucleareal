package net.minecraft.src.nucleareal.animalcrossing.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.RecipeBase;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemWateringCan;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeWateringCan extends RecipeBase
{
	private static String[] BoxRecipe = new String[]
			{ "X X", "XXX", "   " };
	private static String[] GenRecipe = new String[]
			{ "X X", "XXY", "   " };

	private ItemWateringCan WateringCan;

	public RecipeWateringCan(ItemWateringCan WateringCan)
	{
		this.WateringCan = WateringCan;
	}

	@Override
	public void registerAllRecipes()
	{
		Item ingot = WateringCan.getMaterial().getRecipeItem();
		ItemStack Box = new ItemStack(WateringCan, 1, 0);
		ItemStack Gen = new ItemStack(WateringCan, 1, WateringCan.getMaxDamage());

		GameRegistry.addRecipe(Box, new Object[]{
			BoxRecipe[0], BoxRecipe[1], BoxRecipe[2],
			'X', new ItemStack(ingot),
		});
		GameRegistry.addRecipe(Gen, new Object[]{
			GenRecipe[0], GenRecipe[1], GenRecipe[2],
			'X', new ItemStack(ingot),
			'Y', Box,
		});
	}

	@Override
	public ObjectPair<Boolean, ItemStack> getResult(InventoryCrafting ic)
	{
		int WaterBottleCount = 0;
		int WateringCanCount = 0;
		int ItemDamage = 1;
		ItemWateringCan can = null;
		boolean isWaterBucket = false;

		for (int i = 0; i < ic.getSizeInventory(); ++i)
		{
			ItemStack ist = ic.getStackInSlot(i);

			if (ist != null)
			{
				if (ist.getItem() instanceof ItemPotion)
				{
					WaterBottleCount++;
				} else
				if(ist.getItem().equals(Item.bucketWater))
				{
					isWaterBucket = true;
				} else
				if (ist.getItem() instanceof ItemWateringCan && ist.getItem().getClass().equals(WateringCan.getClass()))
				{
					WateringCanCount++;
					ItemDamage = ist.getItemDamage();
					can = (ItemWateringCan)ist.getItem();
				} else
				{
					return Failed;
				}
			}
		}
		if(isWaterBucket)
		{
			if(WaterBottleCount > 0) return Failed;

			WaterBottleCount = ItemDamage - 1;
		}

		int RegenMax = 1;
		if(can != null && WaterBottleCount > 0)
		{
			return new ObjectPair<Boolean, ItemStack>(
					Boolean.valueOf(1 == WateringCanCount && RegenMax <= (ItemDamage - WaterBottleCount)),
					new ItemStack(can, 1, ItemDamage - WaterBottleCount)
					);
		}
		else
		{
			return Failed;
		}
	}

}
