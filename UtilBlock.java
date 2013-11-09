package net.minecraft.src.nucleareal;

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
}
