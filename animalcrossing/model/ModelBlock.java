package net.minecraft.src.nucleareal.animalcrossing.model;

import javax.swing.Renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelBlock extends ModelBase
{
	public ModelRenderer Box;

	public ModelBlock()
	{
		this(0.0F);
	}

	public ModelBlock(float par1)
	{
		this(par1, 0.0F);
	}

	public ModelBlock(float par1, float par2)
	{
		Box = new ModelRenderer(this, 0, 0);
		Box.addBox(-.5F, -.5F, -.5F, 16, 16, 16, par1);
		Box.setRotationPoint(.5F, .5F, .5F);
	}

	public void render(Entity entity, float x, float y, float z, float par5, float par6, float par7)
	{
		setRotationAngles(x, y, z, par5, par6, par7, entity);

		float rate = 1.F;

		Box.render(par7);
	}
}
