package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import net.minecraft.block.Block;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class LogicMonoLowTable extends FurnitureLogicBase
{
	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z)
	{
		TileFurniture tile = getTile(world, x, y, z);
		switch(tile.getRotation())
		{
			case 0: block.setBlockBounds(0F, 0F, 0F, 2F, 3F/4F, 1F); break;
			case 1: block.setBlockBounds(0F, 0F, 0F, 1F, 3F/4F, 2F); break;
			case 2: block.setBlockBounds(-1F, 0F, 0F, 1F, 3F/4F, 1F); break;
			case 3: block.setBlockBounds(0F, 0F, -1F, 1F, 3F/4F, 1F); break;
		}
	}
	public boolean isBlockSolidOnSide(World world, int x, int y, int z,ForgeDirection side)
	{
		if(side == ForgeDirection.UP) return true;
		return super.isBlockSolidOnSide(world, x, y, z, side);
	}

	@Override
	public float getSubtractYParam()
	{
		return 1F/4F;
	}
}
