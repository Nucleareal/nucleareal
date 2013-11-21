package net.minecraft.src.nucleareal.animalcrossing;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ACWorldLastHandler
{
	@ForgeSubscribe
	public void onWorldLast(RenderWorldLastEvent event)
	{
		if(TickHandler.isMoving) return;

		Minecraft mc = Minecraft.getMinecraft();

		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
        FontRenderer fontrenderer = mc.fontRenderer;
        mc.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        fontrenderer.drawStringWithShadow("Boll:"+MoneyManager.get().getMoney(), 4, 4, 0xFFFFFF);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

		//Minecraft.getMinecraft().fontRenderer.drawString("Money:"+MoneyManager.get().getMoney(), 0, 0, 0xFFFFFF);
	}
}
