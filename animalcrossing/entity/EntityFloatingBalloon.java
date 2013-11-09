package net.minecraft.src.nucleareal.animalcrossing.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.Achievements;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFloatingBalloon extends EntityLiving
{
	private double startMotionX;
	private double startMotionZ;
	private EntityFloatingChest Chest;

	public EntityFloatingBalloon(World world)
	{
		super(world);
	}

	public EntityFloatingBalloon(World world, double posX, double posY, double posZ, EntityFloatingChest Chest)
	{
		super(world);
		setHealth(1F/20F);

		this.Chest = Chest;

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
		UtilMinecraft.getWorldAndPlayer("").getV2().triggerAchievement(Achievements.shootedBalloon);
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
}
