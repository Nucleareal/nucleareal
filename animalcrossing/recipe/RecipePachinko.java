package net.minecraft.src.nucleareal.animalcrossing.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.RecipeBase;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemPachinko;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipePachinko extends RecipeBase
{
	private ItemPachinko Pachinko;

	public RecipePachinko(ItemPachinko p)
	{
		this.Pachinko = p;
	}

	@Override
	public void registerAllRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(Pachinko, 1, Pachinko.getMaxDamage()), new Object[]{
			"/-/",
			" o ",
			" | ",
			'/', Item.ingotGold,
			'|', Item.redstone,
			'-', Item.leather,
			'o', Item.slimeBall,
		});
		GameRegistry.addShapedRecipe(new ItemStack(Pachinko, 1, 0), new Object[]{
			"oIL",
			"IFI",
			"LIo",
			'I', Item.ingotGold,
			'o', Item.slimeBall,
			'F', Item.gunpowder,
			'L', Item.leather,
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Pachinko, 1, 0), new Object[]{
			Item.goldNugget, Item.goldNugget, Item.goldNugget, Item.goldNugget,
			Item.slimeBall,
			Item.gunpowder,
			Item.leather,
		});
	}

	@Override
	public ObjectPair<Boolean, ItemStack> getResult(InventoryCrafting ic)
	{
		int bulletCount = 0;
		int pachinkoCount = 0;
		ItemStack ist0 = null;

		for (int i = 0; i < ic.getSizeInventory(); ++i)
		{
			ItemStack ist = ic.getStackInSlot(i);
			if (ist != null)
			{
				if (ist.getItem() instanceof ItemPachinko && ist.getItem().getClass().equals(Pachinko.getClass()))
				{
					if(ist.getItemDamage() == 0) bulletCount++;
					else
					{
						ist0 = ist;
						pachinkoCount++;
					}
				}
				else
				{
					return Failed;
				}
			}
		}

		if(pachinkoCount == 1 && bulletCount > 0 && ist0.getItemDamage() - bulletCount >= 1)
		{
			ItemStack nps = new ItemStack(ist0.getItem(), 1, ist0.getItemDamage() - bulletCount);

			NBTBase tag = ist0.getTagCompound() == null ? new NBTTagCompound() : ist0.getTagCompound().copy();

			nps.setTagCompound((NBTTagCompound)tag);

			return new ObjectPair<Boolean, ItemStack>(Boolean.valueOf(true), nps);
		}
		else
		{
			return Failed;
		}
	}
}
