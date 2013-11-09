package net.minecraft.src.nucleareal.animalcrossing.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPachinkoBullet extends EntityThrowable
{
	public EntityPachinkoBullet(World world)
	{
		super(world);
	}

	public EntityPachinkoBullet(World world, EntityLivingBase base)
	{
		super(world, base);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition.entityHit != null)
        {
            byte b0 = 1;
            if (movingObjectPosition.entityHit instanceof EntityGolem)
            {
                b0 = 3;
            }
            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b0);
        }
        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("iconcrack_"+AnimalCrossing.PachinkoGold.itemID+"_"+0, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
	}
}
