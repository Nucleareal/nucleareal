package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class LogicMonoTable extends FurnitureLogicBase
{
	public boolean isBlockSolidOnSide(World world, int x, int y, int z,ForgeDirection side)
	{
		if(side == ForgeDirection.UP) return true;
		return super.isBlockSolidOnSide(world, x, y, z, side);
	}
}
