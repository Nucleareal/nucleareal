package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import net.minecraft.block.Block;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class LogicMonoWallClock extends FurnitureLogicBase
{
	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z)
	{
		TileFurniture tile = getTile(world, x, y, z);
		switch(tile.getRotation())
		{
			case 0: block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F/8F); break;
			case 1: block.setBlockBounds(7F/8F, 0F, 0F, 1F, 1F, 1F); break;
			case 2: block.setBlockBounds(0F, 0F, 7F/8F, 1F, 1F, 1F); break;
			case 3: block.setBlockBounds(0F, 0F, 0F, 1F/8F, 1F, 1F); break;
		}
	}
	public boolean canStayInThisPosition(World world, int x, int y, int z)
	{
		TileFurniture tile = getTile(world, x, y, z);
		switch(tile.getRotation())
		{
			case 0: return !world.isAirBlock(x, y, z-1); //ZPos
			case 1: return !world.isAirBlock(x+1, y, z); //XNeg
			case 2: return !world.isAirBlock(x, y, z+1); //ZNeg
			case 3: return !world.isAirBlock(x-1, y, z); //XPos
		}
		return true;
	}
}
