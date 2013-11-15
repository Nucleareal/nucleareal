package net.minecraft.src.nucleareal.ghyast;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.src.nucleareal.NuclearealBase;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Ghyast", name = "Ghyast Mod", version = "1.0.0Final")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class Ghyast extends NuclearealBase
{
	@Mod.Instance("Ghyast")
	public static Ghyast instance;

	public static final boolean DEBUG = true;
	public static final String SPX = DEBUG ? "net.minecraft.src." : "";
	public static final String CSD = SPX + "nucleareal.ghyast.client.ClientProxy";
	public static final String SSD = SPX + "nucleareal.ghyast.CommonProxy";

	@SidedProxy(clientSide = CSD, serverSide = SSD)
	public static CommonProxy proxy;

	@Override
	protected String Version()
	{
		return "1.0.0_Alice";
	}

	@Mod.Init
	public void init(FMLInitializationEvent ev)
	{
		EntityRegistry.registerGlobalEntityID(EntityGhyast.class, "Ghyast", 1200, 0, 0);
		EntityRegistry.registerModEntity(EntityGhyast.class, "Ghyast", 1200, this, 250, 5, true);

		proxy.registerRenderers();

		LanguageRegistry.instance().addStringLocalization("entity.Ghyast.name", "en_US", "Ghyast");
	}
}
