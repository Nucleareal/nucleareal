package net.minecraft.src.nucleareal.animalcrossing.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFurniture extends BlockContainer
{
	public BlockFurniture(int id)
	{
		super(id, Material.wood);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	private EnumFurniture of(IBlockAccess world, int x, int y, int z)
	{
		TileEntityFurniture f = getTile(world, x, y, z);
		return EnumFurniture.of(f == null ? 0 : f.getFurnitureIndex());
	}

	private TileEntityFurniture getTile(IBlockAccess world, int x, int y, int z)
	{
		TileEntity e = world.getBlockTileEntity(x, y, z);
		return (TileEntityFurniture) e;
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if (world.getBlockTileEntity(x, y, z) == null)
		{
			world.setBlockTileEntity(x, y, z,
					createTileEntity(world, world.getBlockMetadata(x, y, z)));
		}
	}

	private EnumFurniture of(World world, int x, int y, int z)
	{
		return of((IBlockAccess) world, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5)
	{
		return this.getIcon(par5,
				par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		super.updateTick(world, x, y, z, rand);
	}

	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		super.randomDisplayTick(world, x, y, z, rand);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return of(world, x, y, z).canStayInThisPosition(world, x, y, z);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return of(world, x, y, z).getLightValue(world, x, y, z);
	}

	public int getRenderType()
	{
		return AnimalCrossing.FurnitureRenderID;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y,
			int z)
	{
		of(world, x, y, z).setBlockBounds(this, world, x, y, z);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
			int y, int z)
	{
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return null;
	}

	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityFurniture(metadata);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return getTile(world, x, y, z).getFurnitureIndex();
	}

	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 0;
	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack ist)
	{
		int l = MathHelper
				.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TileEntityFurniture tile = getTile(world, x, y, z);
		int rl = l == 0 ? 2 : l == 1 ? 3 : l == 2 ? 0 : 1;
		tile.setTileRotation(rl);
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z,
			ForgeDirection side)
	{
		return of(world, x, y, z).isBlockSolidOnSide(world, x, y, z, side);
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int i, float fx, float fy, float fz)
	{
		return of(world, x, y, z).onBlockActivated(world, x, y, z, player, i,
				fx, fy, fz);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int side)
	{
		of(world, x, y, z).onNeighborBlockChange(world, x, y, z, side);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return super.canPlaceBlockAt(world, x, y, z)
				&& canBlockStay(world, x, y, z);
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z,
			Entity entity)
	{
		of(world, x, y, z).onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	public void addCollisionBoxesToList(World world, int x, int y, int z,
			AxisAlignedBB axis, List list, Entity entity)
	{
		EnumFurniture e = of(world, x, y, z);

		for (int i = 0; i < e.getCollisionBoxesCount(); i++)
		{
			of(world, x, y, z).addCollisionBoxesToList(world, x, y, z, axis,
					list, entity, i);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int a, int b)
	{
		TileEntityFurniture tile = getTile(world, x, y, z);
		if (tile != null)
		{
			for (int i = 0; i < tile.getSizeInventory(); i++)
			{
				ItemStack ist = tile.getStackInSlot(i);
				if (ist != null)
				{
					float fx = world.rand.nextFloat() * .8F + .1F;
					float fy = world.rand.nextFloat() * .8F + .1F;
					float fz = world.rand.nextFloat() * .8F + .1F;
					EntityItem ei = new EntityItem(world, x+fx, y+fy, z+fz, ist.copy());
					if (ist.hasTagCompound())
						ei.getEntityItem().setTagCompound((NBTTagCompound) ist.getTagCompound().copy());
					float fr = .05F;
					ei.motionX = world.rand.nextGaussian() * fr;
					ei.motionY = world.rand.nextGaussian() * fr + .2F;
					ei.motionZ = world.rand.nextGaussian() * fr;
					world.spawnEntityInWorld(ei);
				}
			}
			EntityItem ei = new EntityItem(world, x+.5D, y+.5D, z+.5D, new ItemStack(this, 1, tile.getFurnitureIndex()));
			float fr = .05F;
			ei.motionX = world.rand.nextGaussian() * fr;
			ei.motionX = world.rand.nextGaussian() * fr + .2F;
			ei.motionX = world.rand.nextGaussian() * fr;
			world.spawnEntityInWorld(ei);

			world.func_96440_m(x, y, z, a);
		}
		super.breakBlock(world, x, y, z, a, b);
	}
}
