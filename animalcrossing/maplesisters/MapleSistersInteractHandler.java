package net.minecraft.src.nucleareal.animalcrossing.maplesisters;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemFish;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class MapleSistersInteractHandler
{
	@ForgeSubscribe
	public void onInteract(EntityInteractEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack ist = player.getCurrentEquippedItem();

		boolean isAkaha = event.target instanceof EntityMapleAkaha;
		boolean isKiyo = event.target instanceof EntityMapleKiyo;

		if(event.target instanceof EntityMapleSistersBase) onInteractEither(event, (EntityMapleSistersBase)event.target, player, ist);
		if(isAkaha) onInteractAkaha(event, (EntityMapleAkaha)event.target, player, ist);
		if(isKiyo) onInteractKiyo(event, (EntityMapleKiyo)event.target, player, ist);
	}

	private void onInteractEither(EntityInteractEvent event, EntityMapleSistersBase target, EntityPlayer player, ItemStack ist)
	{
	}

	private void onInteractAkaha(EntityInteractEvent event, EntityMapleAkaha target, EntityPlayer player, ItemStack ist)
	{
		if(ist != null && ist.getItem() instanceof ItemFish)
		{
			ist.stackSize--;
			target.func_110196_bT();
		}
	}

	private void onInteractKiyo(EntityInteractEvent event, EntityMapleKiyo target, EntityPlayer player, ItemStack ist)
	{
		if(ist != null && ist.getItem() instanceof ItemFish)
		{
			ist.stackSize--;
			target.func_110196_bT();
		}
	}
}
