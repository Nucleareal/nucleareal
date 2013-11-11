package net.minecraft.src.nucleareal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockWood;
import net.minecraft.world.World;

public class UtilBlock
{
	public static boolean isEqualBlocks(World world, Position pos0, Position pos1)
	{
		if( UtilWorld.getBlockID(world, pos0) != UtilWorld.getBlockID(world, pos1) )
		{
			return false;
		}
		if( UtilWorld.getBlockMetadata(world, pos0) != UtilWorld.getBlockMetadata(world, pos1) )
		{
			return false;
		}
		return true;
	}

	public static boolean isEqualWoodBlocks(World world, Position pos0, Position pos1)
	{
		if( UtilWorld.getBlockID(world, pos0) != UtilWorld.getBlockID(world, pos1) )
		{
			return false;
		}
		Block block = Block.blocksList[UtilWorld.getBlockID(world, pos0)];
		if(block instanceof BlockLog)
		{
			BlockLog wood = (BlockLog)block;
			return	wood.limitToValidMetadata(UtilWorld.getBlockMetadata(world, pos0))
						==
					wood.limitToValidMetadata(UtilWorld.getBlockMetadata(world, pos1));
		}
		return UtilWorld.getBlockMetadata(world, pos0) == UtilWorld.getBlockMetadata(world, pos1);
	}
}
