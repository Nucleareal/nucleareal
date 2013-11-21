package net.minecraft.src.nucleareal.animalcrossing.inukichi;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.MoneyManager;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityInukichi;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemFish;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class InukichiInteractHandler
{
	@ForgeSubscribe
	public void onInteract(EntityInteractEvent event)
	{
		Entity entity = event.target;
		EntityPlayer player = event.entityPlayer;

		if(entity.worldObj.isRemote) return;

		ItemStack ist;
		if(entity instanceof EntityInukichi)
		{
			int x = (int)Math.floor(player.posX);
			int y = (int)Math.floor(player.posY);
			int z = (int)Math.floor(player.posZ);
			player.openGui(AnimalCrossing.instance, AnimalCrossing.InukichiSellGuiID, player.worldObj, x, y, z);
		}
	}
}
