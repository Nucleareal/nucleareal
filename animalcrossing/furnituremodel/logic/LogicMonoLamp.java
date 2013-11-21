package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class LogicMonoLamp extends FurnitureLogicBase
{
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		if(getTile(world, x, y, z).getLighting())
		{
			return 15;
		}
		if(AnimalCrossing.isLampPoweredBySignal)
        {
			return getMaxInputStrength(world, x, y, z);
        }
		return 0;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz)
	{
		TileFurniture tile = getTile(world, x, y, z);

		tile.changeLighting();

		world.playSoundEffect(x+.5D, y+.5D, z+.5D, "random.click", 0.3F, tile.getLighting() ? 0.6F : 0.5F);

		return true;
	}

	public boolean canStayInThisPosition(World world, int x, int y, int z)
	{
		return world.isBlockSolidOnSide(x, y-1, z, ForgeDirection.UP);
	}

	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z)
	{
		block.setBlockBounds(1F/8F, 0F, 1F/8F, 7F/8F, 1F, 7F/8F);
	}
}
