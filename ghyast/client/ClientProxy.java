package net.minecraft.src.nucleareal.ghyast.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.ghyast.CommonProxy;
import net.minecraft.src.nucleareal.ghyast.EntityGhyast;
import net.minecraft.src.nucleareal.ghyast.RenderGhyast;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGhyast.class, new RenderGhyast());
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory m)
	{
	}
}