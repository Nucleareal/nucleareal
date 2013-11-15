package net.minecraft.src.nucleareal.ghyast;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderGhyast extends Render
{
	private static final ResourceLocation ghyastTextures = new ResourceLocation("textures/entity/chest/normal.png");
	private static final ModelChest chest = new ModelChest();
	private static final float size = 32F;

	public RenderGhyast()
	{
		super();
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return ghyastTextures;
	}

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
	{
		EntityGhyast chest0 = (EntityGhyast)entity;

		bindTexture(getEntityTexture(entity));
		GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)d0, (float)d1 + 1.0F, (float)d2 + 1.0F);
        GL11.glScalef(size, -size, -size);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        //GL11.glRotatef(chest0.rotation, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float fj;
        fj = 0.75F;
        fj = 1.0F - fj * fj * fj;
        chest.chestLid.rotateAngleX = -(fj * (float)Math.PI / 2.0F);
        chest.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
