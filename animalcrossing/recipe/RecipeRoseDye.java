package net.minecraft.src.nucleareal.animalcrossing.recipe;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRoseDye
{
	private Item Rose;
	private Item Dye;

	public RecipeRoseDye(Item Rose, Item Dye)
	{
		this.Rose = Rose;
		this.Dye = Dye;
	}

	public void RegisterAll()
	{
		RecipeRoseDyeSpecial sp = new RecipeRoseDyeSpecial();
		GameRegistry.addRecipe(sp);

		for(int i = 0; i < RoseColor.getAllColors().size(); i++)
		{
			int meta = RoseColor.convertRoseToCloth(i);
			if(meta >= 0)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(Block.cloth, 1, meta), new Object[]{
					new ItemStack(Block.cloth, 1),
					new ItemStack(Dye, 1, i),
				});
				GameRegistry.addShapelessRecipe(new ItemStack(Block.stainedClay, 8, meta), new Object[]{
					new ItemStack(Block.hardenedClay),
					new ItemStack(Dye, 1, i),
				});
			}
		}

		if(!Item.potion.hasContainerItem())
		{
			Item.potion.setContainerItem(Item.glassBottle);
		}

		if(AnimalCrossing.AddRecipeInkVial)
		{
			for(int i = 256; i < Item.itemsList.length; i++)
			{
				Item item = Item.itemsList[i];
				if(null != item && item.getItemDisplayName(new ItemStack(item)).contains("Ink Vial"))
				{
					FMLLog.log(Level.FINEST, "Ink Vial Recipe: Register Successful", "");
					GameRegistry.addShapelessRecipe(new ItemStack(item), new Object[]{
						new ItemStack(Item.potion),
						new ItemStack(Dye, 1, RoseColor.Black.ordinal()),
						new ItemStack(Dye, 1, RoseColor.Black.ordinal()),
					});
					break;
				}
			}
		}
	}
}
