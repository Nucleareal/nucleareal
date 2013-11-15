package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public interface IBlockProxy
{
	public EnumRarity getRarity();

	public boolean canStayInThisPosition(World world, int x, int y, int z);

	public int getLightValue(IBlockAccess world, int x, int y, int z);

	public int getRenderCount();

	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side);

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz);

	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z);

	public float getSubtractYParam();

	public void onNeighborBlockChange(World world, int x, int y, int z, int side);

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity);

	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity, int layer);

	public int getCollisionBoxesCount();
}
