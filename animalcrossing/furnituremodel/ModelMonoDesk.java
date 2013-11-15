package net.minecraft.src.nucleareal.animalcrossing.furnituremodel;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.ModelNuclearealBase;

public class ModelMonoDesk extends ModelNuclearealBase
{
	// fields
	ModelRenderer Head;
	ModelRenderer Leg1;
	ModelRenderer Leg2;

	public ModelMonoDesk()
	{
		textureWidth = 256;
		textureHeight = 256;

		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(0F, 0F, 0F, 32, 2, 16);
		Head.setRotationPoint(0F, 0F, 0F);
		Head.setTextureSize(256, 256);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 96, 0);
		Leg1.addBox(0F, 0F, 0F, 10, 14, 16);
		Leg1.setRotationPoint(0F, 2F, 0F);
		Leg1.setTextureSize(256, 256);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 96, 0);
		Leg2.addBox(0F, 0F, 0F, 10, 14, 16);
		Leg2.setRotationPoint(22F, 2F, 0F);
		Leg2.setTextureSize(256, 256);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	protected List<ModelRenderer> getAllModelPartsAsList()
	{
		return Arrays.asList(Head, Leg1, Leg2);
	}

}
