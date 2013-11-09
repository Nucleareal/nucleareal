package net.minecraft.src.nucleareal.animalcrossing.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.UtilPosition;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFallingTreePart extends EntityFallingSand
{
	private static double fallingRate = 36000D;
	private static double fallingPlus = (Math.PI * 2.0) / fallingRate;
	private static double fallingAccel = fallingPlus / 16D;

	private Position fallTo;
	private List<ItemStack> istList;

	public int varielblockID;
	public int varielMetadata;

	public EntityFallingTreePart(World world)
	{
		super(world);
		this.shouldDropItem = true;
	}

	public EntityFallingTreePart(World world, double x, double y, double z, int blockID, Position fallTo, List<ItemStack> istList)
	{
		this(world, x, y, z, blockID, 0, fallTo, istList);
	}

	public EntityFallingTreePart(World world, double x, double y, double z, int blockID, int meta, Position fallTo, List<ItemStack> istList)
	{
		super(world, x, y, z, Block.wood.blockID, 1);
		//super(world, x, y, z, blockID, meta);
		System.out.println(String.format("Construct With ID:%d meta:%d", blockID, meta));
		this.fallTo = fallTo;
		this.istList = istList;
		varielMetadata = meta;
		varielblockID = blockID;
	}

	protected void entityInit()
	{
		if(istList == null)
		{
			istList = new ArrayList<ItemStack>();
		}
		if(fallTo == null)
		{
			fallTo = new Position(0D, 0D, 0D);
		}
		varielblockID = Block.wood.blockID;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		if (this.blockID == 0 || varielblockID == 0)
		{
			this.setDead();
		}
		else
		{
			//System.out.println(String.format("Updating ID:%d meta:%d", blockID, metadata));

			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
			// this.motionY -= 0.03999999910593033D;

			if(this.boundingBox == null)
			{
				setPosition(posX, posY, posZ);
			}

			double t = Math.atan2((posY - fallTo.getY()), (posX - fallTo.getX())) - (Math.PI/2);
			double distance = posY - fallTo.getY();
			double rate = (distance * 2D) / 30D;
			motionX = Math.cos(t) * rate;
			motionY = Math.sin(t) * rate;

			motionX *= 0.9800000190734863D;
			motionY *= 0.9800000190734863D;
			motionZ *= 0.9800000190734863D;

			++fallTime;

			rotationYaw -= Math.PI;

			moveEntity(this.motionX, this.motionY, this.motionZ);

			// TODO MotionRateUpdate


			if (!worldObj.isRemote)
			{
				int i = MathHelper.floor_double(this.posX);
				int j = MathHelper.floor_double(this.posY);
				int k = MathHelper.floor_double(this.posZ);

				/*
				 * if (this.fallTime == 1) { if (this.worldObj.getBlockId(i, j,
				 * k) != this.blockID) { this.setDead(); return; }
				 *
				 * this.worldObj.setBlockToAir(i, j, k); }
				 */

				if (onGround || Math.abs(posY - fallTo.getY()) <= 0.05D || fallTime > 256)
				{
					motionX *= 0.699999988079071D;
					motionZ *= 0.699999988079071D;
					motionY *= -0.5D;

					setDead();

					for (ItemStack ist : istList)
					{
						entityDropItem(ist, 0.0F);
					}
				}
			}
		}
	}

	@Override
	public void moveEntity(double dx, double dy, double dz)
	{
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		posX += dx;
		posY += dy;
		posZ += dz;
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	@Override
	protected void fall(float par1)
	{
		double distance = posY - fallTo.getY();
		double damage = Math.ceil(distance - 3D);
		if(damage > 0D)
		{
			int i_d = (int)damage;
			 ArrayList arraylist = new ArrayList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(2D, 2D, 2D)));
             DamageSource damagesource = DamageSource.fallingBlock;
             Iterator iterator = arraylist.iterator();

             while (iterator.hasNext())
             {
                 Entity entity = (Entity)iterator.next();
                 entity.attackEntityFrom(damagesource, (float)Math.min(i_d, 20F));
             }
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setDouble("FallToX", this.fallTo.getX());
		par1NBTTagCompound.setDouble("FallToY", this.fallTo.getY());
		par1NBTTagCompound.setDouble("FallToZ", this.fallTo.getZ());

		par1NBTTagCompound.setInteger("VarielMeta", varielMetadata);
		par1NBTTagCompound.setInteger("VarielBlock", varielblockID);

		for(int i = 0; i < istList.size(); i++)
		{
			NBTTagCompound tag = new NBTTagCompound();
			par1NBTTagCompound.setCompoundTag("ist"+i, tag);
			istList.get(i).writeToNBT(tag);
		}

		if (this.fallingBlockTileEntityData != null)
		{
			par1NBTTagCompound.setCompoundTag("TileEntityData",
					this.fallingBlockTileEntityData);
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		double fx = 0D;
		double fy = 64D;
		double fz = 0D;
		if(par1NBTTagCompound.hasKey("FallToX")) { fx = par1NBTTagCompound.getDouble("FallToX"); }
		if(par1NBTTagCompound.hasKey("FallToY")) { fy = par1NBTTagCompound.getDouble("FallToY"); }
		if(par1NBTTagCompound.hasKey("FallToZ")) { fz = par1NBTTagCompound.getDouble("FallToZ"); }
		fallTo = new Position(fx, fy, fz);

		if (this.blockID == 0)
		{
			this.blockID = Block.wood.blockID;
		}

		if(par1NBTTagCompound.hasKey("VarielMeta"))
		{
			varielMetadata = par1NBTTagCompound.getInteger("VarielMeta");
		}
		if(par1NBTTagCompound.hasKey("VarielBlock"))
		{
			varielblockID = par1NBTTagCompound.getInteger("VarielBlock");
		}

		istList = new ArrayList<ItemStack>();
		for(int i = 0; ; i++)
		{
			if(par1NBTTagCompound.hasKey("ist"+i))
			{
				ItemStack ist = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("ist"+i));
				istList.add(ist);
			}
			else
			{
				break;
			}
		}
	}
}
