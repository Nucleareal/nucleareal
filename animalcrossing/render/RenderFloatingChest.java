package net.minecraft.src.nucleareal.animalcrossing.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingChest;
import net.minecraft.util.ResourceLocation;

public class RenderFloatingChest extends Render
{
	private ModelChest chest;

	public RenderFloatingChest(ModelChest chest)
	{
		this.chest = chest;
	}

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
	{
		EntityFloatingChest chest0 = (EntityFloatingChest)entity;

		bindTexture(getEntityTexture(entity));
		GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)d0, (float)d1 + 1.0F, (float)d2 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        GL11.glRotatef(chest0.rotation, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float fj;
        fj = 1.0F;
        fj = 1.0F - fj * fj * fj;
        chest.chestLid.rotateAngleX = -(fj * (float)Math.PI / 2.0F);
        chest.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation("textures/entity/chest/normal.png");
	}
}
