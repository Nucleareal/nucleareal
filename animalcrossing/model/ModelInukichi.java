package net.minecraft.src.nucleareal.animalcrossing.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInukichi extends ModelBiped
{
	// fields
	ModelRenderer body2;
	ModelRenderer Mimi1;
	ModelRenderer Mimi2;

	public ModelInukichi()
	{
		textureWidth = 64;
		textureHeight = 32;

		bipedHeadwear = new ModelRenderer(this, 0, 0);
		bipedHeadwear.addBox(0F, 0F, 0F, 0, 0, 0);

		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-3F, -6F, -2F, 6, 6, 4);
		bipedHead.setRotationPoint(0F, 0F, 0F);
		bipedHead.setTextureSize(256, 256);
		bipedHead.mirror = true;
		setRotation(bipedHead, 0F, 0F, 0F);
		bipedBody = new ModelRenderer(this, 21, 0);
		bipedBody.addBox(-4F, 0F, -4F, 8, 6, 7);
		bipedBody.setRotationPoint(0F, 0F, 0F);
		bipedBody.setTextureSize(256, 256);
		bipedBody.mirror = true;
		setRotation(bipedBody, 0F, 0F, 0F);
		bipedRightArm = new ModelRenderer(this, 0, 18);
		bipedRightArm.addBox(-1F, -2F, -2F, 2, 8, 2);
		bipedRightArm.setRotationPoint(-5F, 2F, 0F);
		bipedRightArm.setTextureSize(256, 256);
		bipedRightArm.mirror = true;
		setRotation(bipedRightArm, 0F, 0F, 0F);
		bipedLeftArm = new ModelRenderer(this, 0, 18);
		bipedLeftArm.addBox(-1F, -2F, -2F, 2, 8, 2);
		bipedLeftArm.setRotationPoint(5F, 2F, 0F);
		bipedLeftArm.setTextureSize(256, 256);
		bipedLeftArm.mirror = true;
		setRotation(bipedLeftArm, 0F, 0F, 0F);
		bipedRightLeg = new ModelRenderer(this, 9, 18);
		bipedRightLeg.addBox(-2F, 3F, -2F, 2, 8, 2);
		bipedRightLeg.setRotationPoint(-1F, 15F, 0F);
		bipedRightLeg.setTextureSize(256, 256);
		bipedRightLeg.mirror = true;
		setRotation(bipedRightLeg, 0F, 0F, 0F);
		bipedLeftLeg = new ModelRenderer(this, 9, 18);
		bipedLeftLeg.addBox(-2F, 3F, -2F, 2, 8, 2);
		bipedLeftLeg.setRotationPoint(3F, 15F, 0F);
		bipedLeftLeg.setTextureSize(256, 256);
		bipedLeftLeg.mirror = true;
		setRotation(bipedLeftLeg, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 18, 14);
		body2.addBox(-3F, 6F, -3F, 6, 9, 6);
		body2.setRotationPoint(0F, 0F, 0F);
		body2.setTextureSize(256, 256);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		Mimi1 = new ModelRenderer(this, 16, 14);
		Mimi1.addBox(-3F, -8F, -1F, 2, 2, 1);
		Mimi1.setRotationPoint(0F, 0F, 0F);
		Mimi1.setTextureSize(256, 256);
		Mimi1.mirror = true;
		setRotation(Mimi1, 0F, 0F, 0F);
		Mimi2 = new ModelRenderer(this, 16, 14);
		Mimi2.addBox(1F, -8F, -1F, 2, 2, 1);
		Mimi2.setRotationPoint(0F, 0F, 0F);
		Mimi2.setTextureSize(256, 256);
		Mimi2.mirror = true;
		setRotation(Mimi2, 0F, 0F, 0F);

		bipedHead.addChild(Mimi1);
		bipedHead.addChild(Mimi2);
		bipedBody.addChild(body2);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		/*bipedHead.render(f5);
		bipedBody.render(f5);
		bipedRightArm.render(f5);
		bipedLeftArm.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);*/
		/*body2.render(f5);
		Mimi1.render(f5);
		Mimi2.render(f5);*/
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
