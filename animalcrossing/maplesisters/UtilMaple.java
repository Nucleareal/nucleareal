package net.minecraft.src.nucleareal.animalcrossing.maplesisters;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class UtilMaple
{
	public static void addSisterMessage(EntityPlayer player, String msg, MapleSisters person)
	{
		player.addChatMessage("<"+LanguageRegistry.instance().getStringLocalization(person.name()+".Name")+"> "
				+LanguageRegistry.instance().getStringLocalization(msg));
	}
}
