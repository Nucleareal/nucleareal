package net.minecraft.src.nucleareal.animalcrossing.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.inukichi.TextureShopCompass;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderShopCompass implements IItemRenderer
{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	private TextureShopCompass texture;
	private RenderItem render;

	{
		texture = new TextureShopCompass("nc:I_compass");
		render = new RenderItem();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

		Minecraft mc = Minecraft.getMinecraft();

		World world = mc.theWorld;
		EntityPlayer player = mc.thePlayer;

		if(world == null || player == null) return;

		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        texturemanager.bindTexture(TextureMap.locationItemsTexture);

        double d0 = texture.currentAngle;
        double d1 = texture.angleDelta;
        texture.currentAngle = 0.0D;
        texture.angleDelta = 0.0D;
        texture.updateCompass(world, player.posX, player.posZ, player.rotationYaw, false, true);
        texture.currentAngle = d0;
        texture.angleDelta = d1;

        if (texture.getFrameCount() > 0)
        {
            texture.updateAnimation();
        }

        GL11.glPopMatrix();
	}

}
