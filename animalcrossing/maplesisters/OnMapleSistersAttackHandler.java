package net.minecraft.src.nucleareal.animalcrossing.maplesisters;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.src.nucleareal.UtilWorld.EntityHandler;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class OnMapleSistersAttackHandler implements EntityHandler
{
	long handleTime = 0L;
	private EntityPlayer player;

	@ForgeSubscribe
	public void onLivingAttack(LivingAttackEvent event)
	{
		if(ModLoader.getMinecraftInstance().thePlayer == null) return;

		player = UtilMinecraft.getWorldAndPlayer("").getV2();

		DamageSource source = event.source;
		if(event.entityLiving instanceof EntityMapleSistersBase)
		{
			if("player".equals(source.damageType))
			{
				UtilWorld.foreachEntities(event.entityLiving.worldObj, player, 8D, this);
			}
			else
			{
				if(source.damageType.contains("explosion"))
				{
					UtilMaple.addSisterMessage(player, "DamageByExplosion", MapleSisters.of((EntityMapleSistersBase)event.entityLiving));
				}
				event.setCanceled(true);
			}
		}
	}

	@Override
	public boolean onEntity(Entity e)
	{
		if(e instanceof EntityTameable)
		{
			EntityTameable et = (EntityTameable)e;
			//et.setAttackTarget(player);
		}
		return false;
	}
}
