package net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;

public class LogicMonoSofa extends FurnitureLogicBase
{
	private float[][] SelectingLines;
	private float[][][] Boxes;
	private float[][] beginAndEndYAxis;

	@Override
	public void init()
	{
		SelectingLines = createYAxisRotations(0F, 0F, 2F, 1F);
		Boxes = new float[getCollisionBoxesCount()][][];
		Boxes[0] = createYAxisRotations(-1F, 0F, 1F, 13F/16F);
		Boxes[1] = createYAxisRotations(-1F, 0F, 1F, 3F/16F);
		beginAndEndYAxis = new float[getCollisionBoxesCount()][2];
		beginAndEndYAxis[0][0] = 1F/4F;
		beginAndEndYAxis[0][1] = 3F/8F;
		beginAndEndYAxis[1][0] = 3F/8F;
		beginAndEndYAxis[1][1] = 1F;
	}

	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z)
	{
		TileEntityFurniture tile = getTile(world, x, y, z);

		int rotate = tile.getRotation();

		block.setBlockBounds(SelectingLines[rotate][0], 0F, SelectingLines[rotate][1], SelectingLines[rotate][2], 1F, SelectingLines[rotate][3]);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		//TODO The Entity sits the Sofa
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity, int layer)
	{
		TileEntityFurniture tile = getTile(world, x, y, z);

		int rotate = tile.getRotation();

		getBlock().setBlockBounds(
				Boxes[layer][rotate][0],
				beginAndEndYAxis[layer][0],
				Boxes[layer][rotate][1],
				Boxes[layer][rotate][2],
				beginAndEndYAxis[layer][1],
				Boxes[layer][rotate][3]);
	}

	@Override
	public int getCollisionBoxesCount()
	{
		return 2;
	}
}
