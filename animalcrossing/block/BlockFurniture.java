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
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.src.nucleareal.animalcrossing.inukichi.UtilInukichi;
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
		TileFurniture f = getTile(world, x, y, z);
		return EnumFurniture.of(f == null ? 0 : f.getFurnitureIndex());
	}

	public TileFurniture getTile(IBlockAccess world, int x, int y, int z)
	{
		TileEntity e = world.getBlockTileEntity(x, y, z);
		return (TileFurniture) e;
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if (world.getBlockTileEntity(x, y, z) == null)
		{
			world.setBlockTileEntity(x, y, z, createTileEntity(world, world.getBlockMetadata(x, y, z)));
			world.setBlockMetadataWithNotify(x, y, z, 15, 2);
		}
	}

	private EnumFurniture of(World world, int x, int y, int z)
	{
		return of((IBlockAccess) world, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return this.getIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4));
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
		if(isNotPlayerPlacedBy(world, x, y, z)) return true;
		return of(world, x, y, z).canStayInThisPosition(world, x, y, z);
	}

	private boolean isNotPlayerPlacedBy(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) != 0;
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

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		of(world, x, y, z).setBlockBounds(this, world, x, y, z);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileFurniture();
	}

	public TileEntity createTileEntity(World world, int metadata)
	{
		return createNewTileEntity(world);
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

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack ist)
	{
		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TileFurniture tile = getTile(world, x, y, z);
		int rl = l == 0 ? 2 : l == 1 ? 3 : l == 2 ? 0 : 1;
		tile.setFurnitureIndex(ist.getItemDamage());
		tile.setRotation(rl);

		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return of(world, x, y, z).isBlockSolidOnSide(world, x, y, z, side);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz)
	{
		if(isNotPlayerPlacedBy(world, x, y, z)) { checkPlayerBuyThis(world, x, y, z, player); return true; }
		return of(world, x, y, z).onBlockActivated(world, x, y, z, player, i, fx, fy, fz);
	}

	private void checkPlayerBuyThis(World world, int x, int y, int z, EntityPlayer player)
	{
		UtilInukichi.addMessageByBuy(player, world, x, y, z, of(world, x, y, z));
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int side)
	{
		of(world, x, y, z).onNeighborBlockChange(world, x, y, z, side);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if(isNotPlayerPlacedBy(world, x, y, z)) return true;
		return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		of(world, x, y, z).onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity)
	{
		EnumFurniture e = of(world, x, y, z);

		for (int i = 0; i < e.getCollisionBoxesCount(); i++)
		{
			of(world, x, y, z).addCollisionBoxesToList(world, x, y, z, axis, list, entity, i);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int a, int b)
	{
		TileFurniture tile = getTile(world, x, y, z);

		if(isNotPlayerPlacedBy(world, x, y, z)) { replaceBlock(world, x, y, z, tile); return; }

		if (tile != null && !UtilMinecraft.getWorldAndPlayer("").getV2().capabilities.isCreativeMode)
		{
			EntityItem ei = new EntityItem(world, x+.5D, y+.5D, z+.5D, new ItemStack(this, 1, tile.getFurnitureIndex()));
			float fr = .05F;
			ei.motionX = world.rand.nextGaussian() * fr;
			ei.motionX = world.rand.nextGaussian() * fr + .2F;
			ei.motionX = world.rand.nextGaussian() * fr;
			world.spawnEntityInWorld(ei);
		}

		world.removeBlockTileEntity(x, y, z);

		super.breakBlock(world, x, y, z, a, b);
	}

	private void replaceBlock(World world, int x, int y, int z, TileFurniture tile)
	{
		world.setBlock(x, y, z, AnimalCrossing.Furniture.blockID, 15, 7);
		world.setBlockTileEntity(x, y, z, new TileFurniture());

		getTile(world, x, y, z).setFurnitureIndex(tile.getFurnitureIndex());
		getTile(world, x, y, z).setRotation(tile.getRotation());
		getTile(world, x, y, z).setLighting(tile.getLighting());
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
    {
		if(world.getBlockMetadata(x, y, z) == 0) return blockHardness;
		return -1F;
    }

	private boolean isPlayerPlacedBy(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) == 0;
	}
}
