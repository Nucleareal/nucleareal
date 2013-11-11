package net.minecraft.src.nucleareal.animalcrossing.entity;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityFishingFloat extends EntityFishHook
{
	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private boolean inGround;
	public int shake;
	public EntityPlayer angler;
	private int ticksInGround;
	private int ticksInAir;
	private int ticksCatchable;
	public Entity bobber;
	private int fishPosRotationIncrements;
	private double fishX;
	private double fishY;
	private double fishZ;
	private double fishYaw;
	private double fishPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;
	private int meta;

	public EntityFishingFloat(World world)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		setSize(0.25F, 0.25F);
		ignoreFrustumCheck = true;
	}
	@SideOnly(Side.CLIENT)
	public EntityFishingFloat(World world, double par2, double par4, double par6, EntityPlayer player)
	{
		this(world);
		setPosition(par2, par4, par6);
		ignoreFrustumCheck = true;
		angler = player;
		super.angler = player;
		player.fishEntity = this;
	}
	public EntityFishingFloat(World world, EntityPlayer player, int meta)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		ignoreFrustumCheck = true;
		angler = player;
		angler.fishEntity = this;
		setSize(0.25F, 0.25F);
		setLocationAndAngles(player.posX, player.posY + 1.62D - (double) player.yOffset, player.posZ, player.rotationYaw, player.rotationPitch);
		posX -= (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		posY -= 0.10000000149011612D;
		posZ -= (double) (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		float f = 0.4F;
		motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
		motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
		motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI) * f);
		calculateVelocity(motionX, motionY, motionZ, 1.5F, 1.0F);
		this.meta = meta;
	}

	protected void entityInit()
	{
	}

	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
	{
		double d1 = boundingBox.getAverageEdgeLength() * 4.0D;
		d1 *= 64.0D;
		return par1 < d1 * d1;
	}

	public void calculateVelocity(double par1, double par3, double par5, float par7, float par8)
	{
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5
				* par5);
		par1 /= (double) f2;
		par3 /= (double) f2;
		par5 /= (double) f2;
		par1 += rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par3 += rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par5 += rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par1 *= (double) par7;
		par3 *= (double) par7;
		par5 *= (double) par7;
		motionX = par1;
		motionY = par3;
		motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		prevRotationYaw = rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(par3, (double) f3) * 180.0D / Math.PI);
		ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	{
		fishX = par1;
		fishY = par3;
		fishZ = par5;
		fishYaw = (double) par7;
		fishPitch = (double) par8;
		fishPosRotationIncrements = par9;
		motionX = velocityX;
		motionY = velocityY;
		motionZ = velocityZ;
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5)
	{
		velocityX = motionX = par1;
		velocityY = motionY = par3;
		velocityZ = motionZ = par5;
	}

	public void onUpdate()
	{
		if(super.angler == null)
		{
			super.angler = this.angler;
		}

		synchronized(worldObj)
		{
			boolean isRemote = worldObj.isRemote;
			worldObj.isRemote = true;
			super.onUpdate();
			worldObj.isRemote = isRemote;
		}

		if (fishPosRotationIncrements > 0)
		{
			double d0 = posX + (fishX - posX) / (double) fishPosRotationIncrements;
			double d1 = posY + (fishY - posY) / (double) fishPosRotationIncrements;
			double d2 = posZ + (fishZ - posZ) / (double) fishPosRotationIncrements;
			double d3 = MathHelper.wrapAngleTo180_double(fishYaw - (double) rotationYaw);
			rotationYaw = (float) ((double) rotationYaw + d3 / (double) fishPosRotationIncrements);
			rotationPitch = (float) ((double) rotationPitch + (fishPitch - (double) rotationPitch) / (double) fishPosRotationIncrements);
			--fishPosRotationIncrements;
			setPosition(d0, d1, d2);
			setRotation(rotationYaw, rotationPitch);
		}
		else
		{
			if (!worldObj.isRemote)
			{
				ItemStack itemstack = angler.getCurrentEquippedItem();
				if (angler.isDead || !angler.isEntityAlive()
						|| itemstack == null
						|| getDistanceSqToEntity(angler) > 4096.0D)
				{
					setDead();
					angler.fishEntity = null;
					return;
				}
				if (bobber != null)
				{
					if (!bobber.isDead)
					{
						posX = bobber.posX;
						posY = bobber.boundingBox.minY + (double) bobber.height * 0.8D;
						posZ = bobber.posZ;
						return;
					}
					bobber = null;
				}
			}
			if (shake > 0)
			{
				--shake;
			}
			if (inGround)
			{
				int i = worldObj.getBlockId(xTile, yTile, zTile);
				if (i == inTile)
				{
					++ticksInGround;
					if (ticksInGround == 1200)
					{
						setDead();
					}
					return;
				}
				inGround = false;
				motionX *= (double) (rand.nextFloat() * 0.2F);
				motionY *= (double) (rand.nextFloat() * 0.2F);
				motionZ *= (double) (rand.nextFloat() * 0.2F);
				ticksInGround = 0;
				ticksInAir = 0;
			}
			else
			{
				++ticksInAir;
			}
			Vec3 vec3 = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
			Vec3 vec31 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);
			MovingObjectPosition movingobjectposition = worldObj.clip(vec3, vec31);
			vec3 = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
			vec31 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);
			if (movingobjectposition != null)
			{
				vec31 = worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}
			Entity entity = null;
			List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand( 1.0D, 1.0D, 1.0D));
			double d4 = 0.0D;
			double d5;
			for (int j = 0; j < list.size(); ++j)
			{
				Entity entity1 = (Entity) list.get(j);
				if (entity1.canBeCollidedWith() && (entity1 != angler || ticksInAir >= 5))
				{
					float f = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.boundingBox.expand( (double) f, (double) f, (double) f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
					if (movingobjectposition1 != null)
					{
						d5 = vec3.distanceTo(movingobjectposition1.hitVec);
						if (d5 < d4 || d4 == 0.0D)
						{
							entity = entity1;
							d4 = d5;
						}
					}
				}
			}
			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}
			if (movingobjectposition != null)
			{
				if (movingobjectposition.entityHit != null)
				{
					if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, angler), 0.0F))
					{
						bobber = movingobjectposition.entityHit;
					}
				}
				else
				{
					inGround = true;
				}
			}
			if (!inGround)
			{
				moveEntity(motionX, motionY, motionZ);
				float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
				rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
				for (rotationPitch = (float) (Math.atan2(motionY, (double) f1) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
				{
					;
				}
				while (rotationPitch - prevRotationPitch >= 180.0F)
				{
					prevRotationPitch += 360.0F;
				}
				while (rotationYaw - prevRotationYaw < -180.0F)
				{
					prevRotationYaw -= 360.0F;
				}
				while (rotationYaw - prevRotationYaw >= 180.0F)
				{
					prevRotationYaw += 360.0F;
				}
				rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
				rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
				float f2 = 0.92F;
				if (onGround || isCollidedHorizontally)
				{
					f2 = 0.5F;
				}
				byte b0 = 5;
				double d6 = 0.0D;
				for (int k = 0; k < b0; ++k)
				{
					double d7 = boundingBox.minY+ (boundingBox.maxY - boundingBox.minY)* (double) (k + 0) / (double) b0 - 0.125D + 0.125D;
					double d8 = boundingBox.minY+ (boundingBox.maxY - boundingBox.minY)* (double) (k + 1) / (double) b0 - 0.125D + 0.125D;
					AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getAABBPool().getAABB(boundingBox.minX, d7, boundingBox.minZ,boundingBox.maxX, d8, boundingBox.maxZ);
					if (worldObj.isAABBInMaterial(axisalignedbb1,Material.water))
					{
						d6 += 1.0D / (double) b0;
					}
				}
				if (d6 > 0.0D)
				{
					if (ticksCatchable > 0)
					{
						--ticksCatchable;
					}
					else
					{
						short short1 = 500;
						if (worldObj.canLightningStrikeAt(MathHelper.floor_double(posX),MathHelper.floor_double(posY) + 1,MathHelper.floor_double(posZ)))
						{
							short1 = 300;
						}

						if (rand.nextInt(short1) == 0)
						{
							ticksCatchable = rand.nextInt(30) + 10;
							motionY -= 0.20000000298023224D;
							playSound("random.splash",0.25F,1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.4F);
							float f3 = (float) MathHelper.floor_double(boundingBox.minY);
							int l;
							float f4;
							float f5;
							for (l = 0; (float) l < 1.0F + width * 20.0F; ++l)
							{
								f5 = (rand.nextFloat() * 2.0F - 1.0F) * width;
								f4 = (rand.nextFloat() * 2.0F - 1.0F) * width;
								worldObj.spawnParticle("bubble",posX + (double) f5,(double) (f3 + 1.0F),posZ + (double) f4,motionX, motionY- (double) (rand.nextFloat() * 0.2F), motionZ);
							}
							for (l = 0; (float) l < 1.0F + width * 20.0F; ++l)
							{
								f5 = (rand.nextFloat() * 2.0F - 1.0F) * width;
								f4 = (rand.nextFloat() * 2.0F - 1.0F) * width;
								worldObj.spawnParticle("splash", posX + (double) f5, (double) (f3 + 1.0F), posZ + (double) f4, motionX, motionY, motionZ);
							}
						}
					}
				}
				if (ticksCatchable > 0)
				{
					motionY -= (double) (rand.nextFloat() * rand.nextFloat() * rand.nextFloat()) * 0.2D;
				}
				d5 = d6 * 2.0D - 1.0D;
				motionY += 0.03999999910593033D * d5;
				if (d6 > 0.0D)
				{
					f2 = (float) ((double) f2 * 0.9D);
					motionY *= 0.8D;
				}
				motionX *= (double) f2;
				motionY *= (double) f2;
				motionZ *= (double) f2;
				setPosition(posX, posY, posZ);
			}
		}
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setShort("xTile", (short) xTile);
		par1NBTTagCompound.setShort("yTile", (short) yTile);
		par1NBTTagCompound.setShort("zTile", (short) zTile);
		par1NBTTagCompound.setByte("inTile", (byte) inTile);
		par1NBTTagCompound.setByte("shake", (byte) shake);
		par1NBTTagCompound.setByte("inGround", (byte) (inGround ? 1 : 0));
		par1NBTTagCompound.setInteger("esameta", meta);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		xTile = par1NBTTagCompound.getShort("xTile");
		yTile = par1NBTTagCompound.getShort("yTile");
		zTile = par1NBTTagCompound.getShort("zTile");
		inTile = par1NBTTagCompound.getByte("inTile") & 255;
		shake = par1NBTTagCompound.getByte("shake") & 255;
		inGround = par1NBTTagCompound.getByte("inGround") == 1;
		meta = par1NBTTagCompound.getInteger("esameta");
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	public int catchFish()
	{
		if (worldObj.isRemote)
		{
			return 0;
		}
		else
		{
			byte b0 = 0;
			if (bobber != null)
			{
				double d0 = angler.posX - posX;
				double d1 = angler.posY - posY;
				double d2 = angler.posZ - posZ;
				double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
				double d4 = 0.1D;
				bobber.motionX += d0 * d4;
				bobber.motionY += d1 * d4 + (double) MathHelper.sqrt_double(d3) * 0.08D;
				bobber.motionZ += d2 * d4;
				b0 = 3;
			}
			else if (ticksCatchable > 0)
			{
				EntityItem entityitem = new EntityItem(worldObj, posX, posY, posZ, getItemStack(worldObj, posX, posY, posZ));
				double d5 = angler.posX - posX;
				double d6 = angler.posY - posY;
				double d7 = angler.posZ - posZ;
				double d8 = (double) MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				double d9 = 0.1D;
				entityitem.motionX = d5 * d9;
				entityitem.motionY = d6 * d9 + (double) MathHelper.sqrt_double(d8) * 0.08D;
				entityitem.motionZ = d7 * d9;
				worldObj.spawnEntityInWorld(entityitem);
				angler.addStat(StatList.fishCaughtStat, 1);
				angler.worldObj.spawnEntityInWorld(new EntityXPOrb(angler.worldObj, angler.posX, angler.posY + 0.5D, angler.posZ + 0.5D, rand.nextInt(6) + 1));
				b0 = 1;
			}
			if (inGround)
			{
				b0 = 2;
			}
			setDead();
			angler.fishEntity = null;
			return b0;
		}
	}

	private ItemStack getItemStack(World world, double posX, double posY, double posZ)
	{
		BiomeGenBase biome = world.getBiomeGenForCoords((int)Math.floor(posX), (int)Math.floor(posZ));
		System.out.println("Biome:"+biome.biomeName);
		return new ItemStack(Item.fishRaw);
	}

	public void setDead()
	{
		super.setDead();
		if (angler != null)
		{
			angler.fishEntity = null;
		}
	}
}
