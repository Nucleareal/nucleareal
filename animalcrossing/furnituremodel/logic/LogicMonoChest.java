package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class LogicMonoChest extends FurnitureLogicChest
{
	private float[][] rotates;
	{
		rotates = createYAxisRotations(1.5F, 0F, 1.5F, 0F);
	}

	@Override
	protected boolean canOpenChest(World world, int x, int y, int z, int side, EntityPlayer player, float fx, float fy, float fz)
	{
		if(side == 0 || side == 1) return false;

		int rotate = ((TileFurniture)world.getBlockTileEntity(x, y, z)).getRotation();

		switch(rotate)
		{
			case 0: z++; if(side != 3) return false; break; //Z+
			case 1: x--; if(side != 4) return false; break; //X-
			case 2: z--; if(side != 2) return false; break; //Z-
			case 3: x++; if(side != 5) return false; break; //X+
		}

		if(world.isBlockNormalCube(x, y, z)) return false;

		return true;
	}
}
