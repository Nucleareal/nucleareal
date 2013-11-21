package net.minecraft.src.nucleareal.animalcrossing.maplesisters.mydesign;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class MyDesignEditGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		System.out.println(String.format("M Server %d %d", ID, AnimalCrossing.FurnitureChestGuiID));
		if(ID == AnimalCrossing.MyDesignEditGuiID)
		{
			return null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		System.out.println(String.format("M Client %d %d", ID, AnimalCrossing.FurnitureChestGuiID));
		if(ID == AnimalCrossing.MyDesignEditGuiID)
		{
			return new GuiMyDesignEdit(world, player);
		}
		return null;
	}

}
