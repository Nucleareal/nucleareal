package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.src.nucleareal.animalcrossing.untispacingchest.AnimalCrossingChest;
import net.minecraft.world.World;

public abstract class FurnitureLogicChest extends FurnitureLogicBase
{
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz)
	{
		if(!AnimalCrossing.isChestForceEnabled) return false;

		if(!canOpenChest(world, x, y, z, i, player, fx, fy, fz)) return false;

		TileFurniture tile = getTile(world, x, y, z);
		if(!world.isRemote)
		{
			player.openGui(AnimalCrossing.instance, AnimalCrossing.FurnitureChestGuiID, world, x, y, z);
		}
		return true;
	}

	protected abstract boolean canOpenChest(World world, int x, int y, int z, int i, EntityPlayer player, float fx, float fy, float fz);
}
