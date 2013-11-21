package net.minecraft.src.nucleareal.animalcrossing.client;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.CommonProxy;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFallingTreePart;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFishingFloat;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingChest;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityInukichi;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityPachinkoBullet;
import net.minecraft.src.nucleareal.animalcrossing.maplesisters.EntityMapleAkaha;
import net.minecraft.src.nucleareal.animalcrossing.maplesisters.EntityMapleKiyo;
import net.minecraft.src.nucleareal.animalcrossing.model.ModelFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.model.ModelInukichi;
import net.minecraft.src.nucleareal.animalcrossing.model.ModelMapleAkaha;
import net.minecraft.src.nucleareal.animalcrossing.model.ModelMapleKiyo;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderFishingFloat;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderFloatingChest;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderInukichi;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderMapleAkaha;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderMapleKiyo;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderShopCompass;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderTreePart;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingTreePart.class, new RenderTreePart());
		RenderingRegistry.registerEntityRenderingHandler(EntityFloatingBalloon.class, new RenderFloatingBalloon(new ModelFloatingBalloon()));
		RenderingRegistry.registerEntityRenderingHandler(EntityFloatingChest.class, new RenderFloatingChest(new ModelChest()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPachinkoBullet.class, new RenderSnowball(AnimalCrossing.PachinkoGold));
		RenderingRegistry.registerEntityRenderingHandler(EntityFishingFloat.class, new RenderFishingFloat());
		RenderingRegistry.registerEntityRenderingHandler(EntityInukichi.class, new RenderInukichi(new ModelInukichi(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMapleAkaha.class, new RenderMapleAkaha(new ModelMapleAkaha(), .5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMapleKiyo.class, new RenderMapleKiyo(new ModelMapleKiyo(), .5F));

		MinecraftForgeClient.registerItemRenderer(AnimalCrossing.ShopCompass.itemID, new RenderShopCompass());
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory m)
	{
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}