package net.minecraft.src.nucleareal.animalcrossing.recipe;

import java.util.ArrayList;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.RecipeBase;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemColorableFlower;
import net.minecraft.world.World;

public class RecipeRoseDyeSpecial extends RecipeBase
{
	@Override
	public void registerAllRecipes()
	{
	}

	@Override
	public ObjectPair<Boolean, ItemStack> getResult(InventoryCrafting ic)
	{
		int flowerCount = 0;
		int potionCount = 0;
		int damage = -1;

		for (int i = 0; i < ic.getSizeInventory(); ++i)
		{
			ItemStack ist = ic.getStackInSlot(i);

			if (ist != null)
			{
				if (ist.getItem() instanceof ItemPotion)
				{
					potionCount++;
				}
				else
				if (ist.getItem() instanceof ItemColorableFlower && (damage == ist.getItemDamage() || damage < 0))
				{
					flowerCount++;
					damage = ist.getItemDamage();
				}
				else
				{
					return Failed;
				}
			}
		}

		if(2 == flowerCount && 1 == potionCount)
		{
			return new ObjectPair<Boolean, ItemStack>(true, new ItemStack(AnimalCrossing.RoseDyeItem, 1, damage));
		}
		return Failed;
	}

}
