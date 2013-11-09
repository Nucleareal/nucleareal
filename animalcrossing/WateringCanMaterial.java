package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.item.Item;

public enum WateringCanMaterial
{
	Gold(8, Item.ingotGold);

	public static String[] Names_enUS = new String[]
		{ "Gold" };
	public static String[] Names_jaJP = new String[]
		{ "金" };

	private int MaxStock;
	private Item RecipeItem;

	private WateringCanMaterial(int MaxStock, Item GenItem)
	{
		this.MaxStock = MaxStock;
		RecipeItem = GenItem;
	}

	public int getMaxUse()
	{
		return MaxStock;
	}

	public Item getRecipeItem()
	{
		return RecipeItem;
	}
}
