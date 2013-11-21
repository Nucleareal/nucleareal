package net.minecraft.src.nucleareal.animalcrossing.maplesisters;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.nucleareal.animalcrossing.ShopLevelController;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityMapleSistersBase extends EntityTameable
{
	public EntityMapleSistersBase(World world)
	{
		super(world);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, this.aiSit);
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		setTamed(false);
		setSize(.4F, 1.6F);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		EntityAgeable result = null;
		try
		{
			result = getClass().getConstructor(World.class).newInstance(new Object[]{ worldObj });
		}
		catch(Exception e)
		{
		}
		return result;
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
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.wolf.step", 0.15F, 1.0F);
	}

	@Override
	protected String getLivingSound()
    {
		if(!ShopLevelController.get().isMapleUnlocked()) return "";

        return rand.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow";
    }

	@Override
    protected String getHurtSound()
    {
        return "mob.cat.hitt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.cat.hitt";
    }

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected boolean canTriggerWalking()
    {
        return true;
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

	@Override
	public void heal(float par1)
    {
		setHealth(getMaxHealth());
    }

	@Override
	public void setDead()
	{
		heal(0F);
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
    {
		heal(0F);
    }

	@Override
	protected void onDeathUpdate()
    {
		heal(0F);
    }

	@Override
	protected void damageEntity(DamageSource src, float par2)
    {
		heal(0F);
    }
}
