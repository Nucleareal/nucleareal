package net.minecraft.src.nucleareal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class UtilInventory
{
	public static boolean useItemStack(EntityPlayer player, ItemStack istu)
	{
		InventoryPlayer inv = player.inventory;
		for(int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack ist = inv.getStackInSlot(i);
			if(ist != null && ist.getItem().equals(istu.getItem()) && ist.getItemDamage() == istu.getItemDamage() && ist.stackSize > 0)
			{
				ist.stackSize--;
				return true;
			}
		}
		return false;
	}
}
