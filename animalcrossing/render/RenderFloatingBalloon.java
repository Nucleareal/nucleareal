package net.minecraft.src.nucleareal.animalcrossing.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.nucleareal.animalcrossing.model.ModelFloatingBalloon;
import net.minecraft.util.ResourceLocation;

public class RenderFloatingBalloon extends RenderLiving
{
	private ModelFloatingBalloon Model;

	public RenderFloatingBalloon(ModelFloatingBalloon Model)
	{
		super(Model, 1F);
		this.Model = Model;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation("nc:textures/entity/FloatingBalloon.png");
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
		super.doRenderLiving(par1EntityLiving, par2+.5D, par4-.5D, par6+.5D, par8, par9);
    }
}
