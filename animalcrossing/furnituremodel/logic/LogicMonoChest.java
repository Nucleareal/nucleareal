package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class LogicMonoChest extends FurnitureLogicBase
{
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz)
	{
		TileEntityFurniture tile = getTile(world, x, y, z);

		if(!world.isRemote)
		{
			player.openGui(AnimalCrossing.instance, AnimalCrossing.FurnitureChestGuiID, world, x, y, z);
		}

		return true;
	}
}
