package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemWateringCan;
import net.minecraft.world.World;

public class CommonProxy
{
	public void registerRenderers()
	{
	}

	public void onCrafting(EntityPlayer player, ItemStack item, IInventory m)
	{
		int waterbincount = 0;

		boolean isCreatingWateringCan = false;

		for(int i = 0; i < m.getSizeInventory(); i++)
		{
			ItemStack ist = m.getStackInSlot(i);
			if(ist != null)
			{
				if(ist.getItem() instanceof ItemPotion)
				{
					waterbincount++;
				}
				if(ist.getItem() instanceof ItemWateringCan)
				{
					isCreatingWateringCan = true;
				}
			}
		}

		if(isCreatingWateringCan)
		{
			player.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle, waterbincount));
		}
	}

	public World getClientWorld()
	{
		return null;
	}
}