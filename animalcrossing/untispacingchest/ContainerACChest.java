package net.minecraft.src.nucleareal.animalcrossing.untispacingchest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerACChest extends Container
{
	private static final int inline = 5;

	private static final int beginX = 51;
	private static final int beginY = 11;
	private static final int DistanceX = 30;
	private static final int DistanceY = 30;
	private static final int PLYbeginX = 35;
	private static final int PLYbeginY = 74;
	private static final int PLYDistanceX = 20;
	private static final int PLYDistanceY = 20;
	private static final int PLYShiftX = 10;

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}

	public ContainerACChest(IInventory playerContains, int page)
	{
		int slotindex = AnimalCrossingChest.get().getActivePage() * AnimalCrossingChest.get().getSizePage();
		for (int y = 0; y < AnimalCrossingChest.get().getYCount(); y++)
		{
			for (int x = 0; x < AnimalCrossingChest.get().getXCount(); x++)
			{
				addSlotToContainer(new SlotUntiSpacingChest(AnimalCrossingChest.get(), slotindex++, beginX + DistanceX * x, beginY + DistanceY * y));
			}
		}

		/*int slotindex = 0;
		for(int i = 0; i < AnimalCrossingChest.get().getSizeInventory(); i++)
		{
			if(i / AnimalCrossingChest.get().getSizePage() == page)
			{
				int ri = i % AnimalCrossingChest.get().getSizePage();
				int x = i % AnimalCrossingChest.get().getXCount();
				int y = i / AnimalCrossingChest.get().getXCount();
				addSlotToContainer(new Slot(AnimalCrossingChest.get(), slotindex++, beginX + DistanceX * x, beginY + DistanceY * y));
			}
			else
			{
				addSlotToContainer(new Slot(AnimalCrossingChest.get(), slotindex++, 9999, 9999));
			}
		}*/

		System.out.println("Container a Finished By "+slotindex);

		slotindex = 0;
		for (int y = 0; y < 4; y++)
		{
			int beginPlX = (y % 2) * PLYShiftX;
			for (int x = 0; x < 9; x++)
			{
				addSlotToContainer(new Slot(playerContains, slotindex++, beginPlX + PLYbeginX + PLYDistanceX * x, PLYbeginY + PLYDistanceY * y));
			}
		}
		System.out.println("Container p Finished By "+slotindex);

		System.out.println("Size:"+inventorySlots.size());
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
	{
		ItemStack ist = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack())
		{
			ItemStack content = slot.getStack();
			ist = content.copy();

			int invSize = AnimalCrossingChest.get().getSizeInventory();

			if (slotIndex < invSize)
			{
				if (!this.mergeItemStack(content, invSize, inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(content, 0, invSize, false))
			{
				return null;
			}

			if (content.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}
		return ist;
	}
}
