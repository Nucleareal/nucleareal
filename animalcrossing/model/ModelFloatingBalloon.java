package net.minecraft.src.nucleareal.animalcrossing.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFloatingBalloon extends ModelBase
{
	// fields
	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer Head3;
	ModelRenderer String;

	public ModelFloatingBalloon()
	{
		textureWidth = 256;
		textureHeight = 256;

		Head1 = new ModelRenderer(this, 0, 0);
		Head1.addBox(-3F, -6F, -3F, 6, 12, 6);
		Head1.setRotationPoint(0F, 4F, 0F);
		Head1.setTextureSize(256, 256);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);
		Head2 = new ModelRenderer(this, 24, 0);
		Head2.addBox(-4F, -5F, -4F, 8, 10, 8);
		Head2.setRotationPoint(0F, 4F, 0F);
		Head2.setTextureSize(256, 256);
		Head2.mirror = true;
		setRotation(Head2, 0F, 0F, 0F);
		Head3 = new ModelRenderer(this, 56, 0);
		Head3.addBox(-5F, -3F, -5F, 10, 6, 10);
		Head3.setRotationPoint(0F, 4F, 0F);
		Head3.setTextureSize(256, 256);
		Head3.mirror = true;
		setRotation(Head3, 0F, 0F, 0F);
		String = new ModelRenderer(this, 96, 0);
		String.addBox(-0.5F, 0F, -0.5F, 1, 14, 1);
		String.setRotationPoint(0F, 10F, 0F);
		String.setTextureSize(256, 256);
		String.mirror = true;
		setRotation(String, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		Head1.render(f5);
		Head2.render(f5);
		Head3.render(f5);
		String.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void renderAll()
	{
		Head1.render(0.0625F);
		Head2.render(0.0625F);
		Head3.render(0.0625F);
		String.render(0.0625F);
	}
}
