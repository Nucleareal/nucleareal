package net.minecraft.src.nucleareal.animalcrossing.inukichi.sell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.src.nucleareal.animalcrossing.untispacingchest.AnimalCrossingChest;

public class ContainerSellItems extends Container
{
	private IInventory sellInv;

	public ContainerSellItems(IInventory sellInv)
	{
		this.sellInv = sellInv;

		int slotindex = 0;

		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				addSlotToContainer(new Slot(sellInv, slotindex++, 26 + x*18, 36 + y*18));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}
}
