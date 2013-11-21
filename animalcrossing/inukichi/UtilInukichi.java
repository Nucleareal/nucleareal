package net.minecraft.src.nucleareal.animalcrossing.inukichi;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.world.World;

public class UtilInukichi
{
	public static void addMessageByBuy(EntityPlayer player, World world, int x, int y, int z, EnumFurniture of)
	{
		if(world.isRemote) return;

		int value = of.getValueBuy();

		String name = LanguageRegistry.instance().getStringLocalization("Inukichi.Name");

		player.addChatMessage("<"+name+"> Do you buy "+of.getFurnitureName()+" ? It's "+value+" Boll.");

		InukichiBuySellController.get().triggerBuy(player, value, of.ordinal(), world, x, y, z);
	}
}
