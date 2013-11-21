package net.minecraft.src.nucleareal.animalcrossing.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityInukichi extends EntityTameable
{
	public EntityInukichi(World world)
	{
		super(world);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, this.aiSit);
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		setTamed(false);
		setSize(.5F, 1.6F);
	}

	@Override
	public void heal(float par1)
    {
		setHealth(getMaxHealth());
    }

	@Override
	public void entityInit()
	{
		super.entityInit();

		setTamed(true);
		setPathToEntity((PathEntity) null);
		setAttackTarget((EntityLivingBase) null);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return null;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void updateAITick()
	{
		super.updateAITick();
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.wolf.step", 0.15F, 1.0F);
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.wolf.bark";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.wolf.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.wolf.death";
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected int getDropItemId()
	{
		return -1;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public float getEyeHeight()
	{
		return height * 0.8F;
	}

	@Override
	public void fall(float f)
	{
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	public boolean canBreatheUnderwater()
    {
        return true;
    }

	public boolean canBeCollidedWith()
    {
		return onGround;
    }
}
