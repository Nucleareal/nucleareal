package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemWateringCan;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreateWateringCanHandler implements ICraftingHandler
{
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory m)
	{
		AnimalCrossing.proxy.onCrafting(player, item, m);
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item)
	{
	}

}
