package net.minecraft.src.nucleareal;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.Configuration;

public class ConfigurationCreator
{
	public static void create(FMLPreInitializationEvent ev, IConfigurationLoader method)
	{
		Configuration conf = new Configuration(ev.getSuggestedConfigurationFile());
		conf.load();
		method.onLoad(conf);
		conf.save();
	}
}
