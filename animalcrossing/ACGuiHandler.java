package net.minecraft.src.nucleareal.animalcrossing;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ACGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(map.containsKey(Integer.valueOf(ID)))
		{
			return map.get(Integer.valueOf(ID)).getServerGuiElement(ID, player, world, x, y, z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(map.containsKey(Integer.valueOf(ID)))
		{
			return map.get(Integer.valueOf(ID)).getClientGuiElement(ID, player, world, x, y, z);
		}
		return null;
	}

	private static HashMap<Integer, IGuiHandler> map;
	static
	{
		map = new HashMap<Integer, IGuiHandler>();
	}

	public static void registerHandler(int id, IGuiHandler handler)
	{
		map.put(Integer.valueOf(id), handler);
	}
}
