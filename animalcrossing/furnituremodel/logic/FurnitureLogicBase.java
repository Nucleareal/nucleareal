package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class FurnitureLogicBase implements IBlockProxy
{
	private float[][] defaultRotations;

	public FurnitureLogicBase()
	{
		init();
	}

	protected int getBlockID()
	{
		return AnimalCrossing.Furniture.blockID;
	}

	protected Block getBlock()
	{
		return Block.blocksList[getBlockID()];
	}

	@Override
	public EnumRarity getRarity()
	{
		return EnumRarity.common;
	}

	@Override
	public boolean canStayInThisPosition(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return 0;
	}

	@Override
	public int getRenderCount()
	{
		return 1;
	}

	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz)
	{
		return false;
	}

	protected void init()
	{
		defaultRotations = createYAxisRotations(0F, 0F, 1F, 1F);
	}

	@Override
	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z)
	{
		block.setBlockBounds(defaultRotations[0][0], 0F, defaultRotations[0][1], defaultRotations[0][2], 1F, defaultRotations[0][3]);
	}

	@Override
	public float getSubtractYParam()
	{
		return 0F;
	}

	protected int getMaxInputStrength(IBlockAccess world, int x, int y, int z)
    {
		World world0 = UtilMinecraft.getWorldAndPlayer("").getV1();
		return getMaxInputStrength(world0, x, y, z);
    }

	protected int getMaxInputStrength(World world, int x, int y, int z)
    {
		int max = 0;
		for(int i = 0; i < 4; i++)
		{
			max = Math.max(max, getInputStrength(world, x, y, z, i));
		}
		return max;
    }

    protected int getInputStrength(World world, int x, int y, int z, int meta)
    {
        int i1 = getDirection(meta);
        int j1 = x + Direction.offsetX[i1];
        int k1 = z + Direction.offsetZ[i1];
        int l1 = world.getIndirectPowerLevelTo(j1, y, k1, Direction.directionToFacing[i1]);
        return l1 >= 15 ? l1 : Math.max(l1, Block.blocksList[world.getBlockId(j1, y, k1)] instanceof BlockRedstoneWire ? world.getBlockMetadata(j1, y, k1) : 0);
    }

    public static int getDirection(int par0)
    {
        return par0 & 3;
    }

    protected TileEntityFurniture getTile(World world, int x, int y, int z)
	{
		return getTile((IBlockAccess)world, x, y, z);
	}

    protected TileEntityFurniture getTile(IBlockAccess world, int x, int y, int z)
    {
    	return (TileEntityFurniture)world.getBlockTileEntity(x, y, z);
    }

	public void onNeighborBlockChange(World world, int x, int y, int z, int side)
	{
		if(!canStayInThisPosition(world, x, y, z))
		{
			world.destroyBlock(x, y, z, true);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity, int layer)
	{
		setBlockBounds(getBlock(), world, x, y, z);
	}

	@Override
	public int getCollisionBoxesCount()
	{
		return 1;
	}

	private static final double rate = Math.PI / 2D;

	protected float[][] createYAxisRotations(float xbegin, float zbegin, float xend, float zend)
	{
		float[][] Rotations = new float[4][];
		xbegin	-= .5D;
		zbegin	-= .5D;
		xend	-= .5D;
		zend	-= .5D;
		for(int direction = 0; direction < 4; direction++)
		{
			double dirrate = rate*direction;
			Rotations[direction] = new float[4];
			Rotations[direction][0] = (float)( xbegin*Math.cos(dirrate) - zbegin*Math.sin(dirrate) ) + .5F;
			Rotations[direction][1] = (float)( xbegin*Math.sin(dirrate) + zbegin*Math.cos(dirrate) ) + .5F;
			Rotations[direction][2] = (float)( xend*Math.cos(dirrate) - zend*Math.sin(dirrate) ) + .5F;
			Rotations[direction][3] = (float)( xend*Math.sin(dirrate) + zend*Math.cos(dirrate) ) + .5F;

			if(Rotations[direction][0] > Rotations[direction][2])
			{
				float tmp = Rotations[direction][0];
				Rotations[direction][0] = Rotations[direction][2];
				Rotations[direction][2] = tmp;
			}
			if(Rotations[direction][1] > Rotations[direction][3])
			{
				float tmp = Rotations[direction][1];
				Rotations[direction][1] = Rotations[direction][3];
				Rotations[direction][3] = tmp;
			}
		}
		return Rotations;
	}
}
