package net.minecraft.src.nucleareal;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class UtilMinecraft
{
	public static ObjectPair<World, EntityPlayer> getWorldAndPlayer(String PlayerName)
	{
		Minecraft minecraft = ModLoader.getMinecraftInstance();
		World world = minecraft.theWorld;
		EntityPlayer player = minecraft.thePlayer;

		if(null == PlayerName || PlayerName.isEmpty())
		{
			PlayerName = player.username;
		}

		if(null == minecraft.getIntegratedServer())
		{
			System.out.println("Error: Client Mode");
		}
		else if(null == minecraft.getIntegratedServer().worldServers || minecraft.getIntegratedServer().worldServers.length <= 0)
		{
			System.out.println("Error: No Worlds");
		}
		else
		{
			player = minecraft.getIntegratedServer().getConfigurationManager().getPlayerForUsername(PlayerName);
			if(player != null) world = minecraft.getIntegratedServer().worldServerForDimension(player.dimension);
		}
		return new ObjectPair<World, EntityPlayer>(world, player);
	}

	public static Minecraft get()
	{
		return ModLoader.getMinecraftInstance();
	}
}
