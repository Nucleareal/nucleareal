package net.minecraft.src.nucleareal.animalcrossing.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderMapleKiyo extends RenderBiped
{
	private static final ResourceLocation texture = new ResourceLocation("nc:textures/entity/MapleKiyo.png");

	public RenderMapleKiyo(ModelBiped model, float f1)
	{
		super(model, f1);
	}
	public RenderMapleKiyo(ModelBiped model, float f1, float f2)
	{
		super(model, f1, f2);
	}

	@Override
	protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving)
    {
        return texture;
    }
}
