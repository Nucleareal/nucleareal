package net.minecraft.src.nucleareal.animalcrossing.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.src.nucleareal.animalcrossing.Achievements;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.BalloonType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFloatingBalloon extends EntityLiving
{
	private double startMotionX;
	private double startMotionZ;
	private EntityFloatingChest Chest;
	private BalloonType type;

	public EntityFloatingBalloon(World world)
	{
		super(world);
	}

	public EntityFloatingBalloon(World world, double posX, double posY, double posZ, EntityFloatingChest Chest)
	{
		this(world, posX, posY, posZ, Chest, BalloonType.Normal);
	}

	public EntityFloatingBalloon(World world, double posX, double posY, double posZ, EntityFloatingChest Chest, BalloonType type)
	{
		super(world);
		setHealth(type.getLife()/20F);

		this.Chest = Chest;
		this.type = type;

		ridingEntity = Chest;
		Chest.riddenByEntity = this;
	}

	@Override
	public void moveEntity(double dx, double dy, double dz)
    {
    }

	@Override
	public void fall(float f)
	{
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
		AnimalCrossing.NBT.incleaseValue("ShootedHitCount");
		return super.attackEntityFrom(par1DamageSource, par2);
    }

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		rotationPitch = 0F;
		rotationYaw = 0F;
		rotationYawHead = 0F;

		if(this.ridingEntity == null)
		{
			setDead();
		}
	}

	public void setDead()
	{
		super.setDead();
	}

	public void onDeathUpdate()
	{
		if(deathTime == 0)
		{
			Achievements.triggerAchievement(Achievements.shootedBalloon);
		}
		super.onDeathUpdate();
	}
}
