package net.minecraft.src.nucleareal;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public abstract class RecipeBase implements IRecipe
{
	public abstract void registerAllRecipes();

	protected static ObjectPair<Boolean, ItemStack> Failed = new ObjectPair<Boolean, ItemStack>(Boolean.valueOf(false), null);

	@Override
	public boolean matches(InventoryCrafting ic, World world)
	{
		return getResult(ic).getV1().booleanValue();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting ic)
	{
		return getResult(ic).getV2();
	}

	public abstract ObjectPair<Boolean, ItemStack> getResult(InventoryCrafting ic);

	@Override
	public int getRecipeSize()
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}
}
