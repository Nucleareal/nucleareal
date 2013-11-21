package net.minecraft.src.nucleareal.animalcrossing.untispacingchest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ACChestGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == AnimalCrossing.FurnitureChestGuiID)
		{
			return new ContainerACChest(player.inventory, AnimalCrossingChest
					.get().getActivePage());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == AnimalCrossing.FurnitureChestGuiID)
		{
			return new GuiACChest(player, AnimalCrossingChest.get()
					.getActivePage(), world, x, y, z);
		}
		return null;
	}

}
