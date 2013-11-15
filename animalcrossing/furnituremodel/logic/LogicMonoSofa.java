package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;

public class LogicMonoSofa extends FurnitureLogicBase
{
	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z)
	{
		TileEntityFurniture tile = getTile(world, x, y, z);
		switch(tile.getRotation())
		{
			case 0: block.setBlockBounds(0F, 0F, 0F, 2F, 1F, 1F); break;
			case 1: block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 2F); break;
			case 2: block.setBlockBounds(-1F, 0F, 0F, 1F, 1F, 1F); break;
			case 3: block.setBlockBounds(0F, 0F, -1F, 1F, 1F, 1F); break;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity, int layer)
	{
		TileEntityFurniture tile = getTile(world, x, y, z);
		switch(tile.getRotation())
		{
			case 0:
				switch(layer)
				{
					case 0: getBlock().setBlockBounds(-1F, 1F/4F, 0F, 1F, 3F/8F, 13F/16F); return;
					case 1: getBlock().setBlockBounds(-1F, 3F/8F, 0F, 1F,    1F,  3F/16F); return;
				}
			case 1:
				switch(layer)
				{
					case 0: getBlock().setBlockBounds(3F/16F, 1F/4F,  0F, 1F, 3F/8F, 2F); return;
					case 1: getBlock().setBlockBounds(13F/16F, 3F/8F, 0F, 1F,    1F, 2F); return;
				}
			case 2:
				switch(layer)
				{
					case 0: getBlock().setBlockBounds(0F, 1F/4F,  3F/16F, 2F, 3F/8F, 1F); return;
					case 1: getBlock().setBlockBounds(0F, 3F/8F, 13F/16F, 2F,    1F, 1F); return;
				}
			case 3:
				switch(layer)
				{
					case 0: getBlock().setBlockBounds(0F, 1F/4F, -1F, 13F/16F, 3F/8F, 1F); return;
					case 1: getBlock().setBlockBounds(0F, 3F/8F, -1F,  3F/16F,    1F, 1F); return;
				}
		}

	}

	@Override
	public int getCollisionBoxesCount()
	{
		return 2;
	}
}
