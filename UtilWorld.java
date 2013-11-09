package net.minecraft.src.nucleareal;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class UtilWorld
{
	public static int getBlockID(World world, Position pos)
	{
		return world.getBlockId((int)pos.getX(), (int)pos.getY(), (int)pos.getZ());
	}

	public static int getBlockMetadata(World world, Position pos)
	{
		return world.getBlockMetadata((int)pos.getX(), (int)pos.getY(), (int)pos.getZ());
	}

	public static void loggingIsServerWolrd(World world)
	{
		System.out.println(getIsServerWolrd(world));
	}

	public static Material getBlockMaterial(World world, Position pos)
	{
		return world.getBlockMaterial((int)pos.getX(), (int)pos.getY(), (int)pos.getZ());
	}

	public interface EntityHandler
	{
		public boolean onEntity(Entity e);
	}

	public static void foreachEntities(World world, EntityPlayer player, double range, EntityHandler handler)
	{
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));
		for(Entity e : list)
		{
			if(handler.onEntity(e)) break;
		}
	}

	public static boolean isAirBlock(World world, Position pos)
	{
		return world.isAirBlock((int)pos.getX(), (int)pos.getY(), (int)pos.getZ());
	}

	public static boolean isSameBlockIDs(World world, Position now, Position next)
	{
		return getBlockID(world, now) == getBlockID(world, next);
	}

	public static void setBlockWithMetadata(World world, Position pos, int blockID, int meta)
	{
		world.setBlock((int)pos.getX(), (int)pos.getY(), (int)pos.getZ(), blockID, meta, 0x7);
	}

	public static boolean canPlaceBlockAt(Block block, World world, Position pos)
	{
		return block.canPlaceBlockAt(world, (int)pos.getX(), (int)pos.getY(), (int)pos.getZ());
	}

	public static String getIsServerWolrd(World world)
	{
		return world.isRemote ? "Client World" : "Server World";
	}
}
