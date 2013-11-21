package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class OnLivingAttackHandler
{
	long handleTime = 0L;

	@ForgeSubscribe
	public void onLivingAttack(LivingAttackEvent event)
	{
		if(ModLoader.getMinecraftInstance().thePlayer == null) return;

		EntityPlayer player = UtilMinecraft.getWorldAndPlayer("").getV2();
		ItemStack current = player.getCurrentEquippedItem();

		if(current == null || current.getItem() == null || event.entityLiving.getClass().equals(EntityVillager.class)) return;

		String cz = current.getItem().getClass().getSimpleName();
		DamageSource source = event.source;
		if("player".equals(source.damageType) && AnimalCrossing.isNoDamageWithoutWeapon && ! isSword(current))
		{
			if(System.currentTimeMillis() - handleTime > 100L)
			{
				World world = player.worldObj;

				double dx = event.entity.posX - player.posX;
				double dz = event.entity.posZ - player.posZ;

				ModLoader.getMinecraftInstance().thePlayer.knockBack(event.entity, 8F, dx, dz);
				player.knockBack(event.entity, 8F, dx, dz);

				player.addChatMessage("It's not weapon!");

				world.playAuxSFX(1022, (int)player.posX, (int)player.posY, (int)player.posZ, 0);

				handleTime = System.currentTimeMillis();
			}
			event.setCanceled(true);
		}
	}

	private boolean isSword(ItemStack ist)
	{
		return AnimalCrossing.isSword(ist.itemID, ist.getItemDamage(), ist.getItem().getClass().getSimpleName());
	}
}
