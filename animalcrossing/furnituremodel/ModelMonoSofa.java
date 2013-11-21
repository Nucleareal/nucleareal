package net.minecraft.src.nucleareal.animalcrossing.furnituremodel;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.ModelNuclearealBase;

public class ModelMonoSofa extends ModelNuclearealBase
{
	// fields
	ModelRenderer Hide;
	ModelRenderer Floor;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Arm1;
	ModelRenderer Arm2;

	public ModelMonoSofa()
	{
		textureWidth = 256;
		textureHeight = 256;

		Hide = new ModelRenderer(this, 0, 0);
		Hide.addBox(0F, 0F, 0F, 32, 10, 3);
		Hide.setRotationPoint(0F, 0F, 13F);
		Hide.setTextureSize(256, 256);
		Hide.mirror = true;
		setRotation(Hide, 0F, 0F, 0F);
		Floor = new ModelRenderer(this, 70, 0);
		Floor.addBox(0F, 0F, 0F, 32, 2, 13);
		Floor.setRotationPoint(0F, 10F, 3F);
		Floor.setTextureSize(256, 256);
		Floor.mirror = true;
		setRotation(Floor, 0F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 160, 0);
		Leg1.addBox(0F, 0F, 0F, 2, 4, 2);
		Leg1.setRotationPoint(1F, 12F, 13F);
		Leg1.setTextureSize(256, 256);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 160, 0);
		Leg2.addBox(0F, 0F, 0F, 2, 4, 2);
		Leg2.setRotationPoint(1F, 12F, 4F);
		Leg2.setTextureSize(256, 256);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 160, 0);
		Leg3.addBox(0F, 0F, 0F, 2, 4, 2);
		Leg3.setRotationPoint(29F, 12F, 13F);
		Leg3.setTextureSize(256, 256);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 160, 0);
		Leg4.addBox(0F, 0F, 0F, 2, 4, 2);
		Leg4.setRotationPoint(29F, 12F, 4F);
		Leg4.setTextureSize(256, 256);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Arm1 = new ModelRenderer(this, 168, 0);
		Arm1.addBox(0F, 0F, 0F, 2, 3, 10);
		Arm1.setRotationPoint(0F, 7F, 3F);
		Arm1.setTextureSize(256, 256);
		Arm1.mirror = true;
		setRotation(Arm1, 0F, 0F, 0F);
		Arm2 = new ModelRenderer(this, 168, 0);
		Arm2.addBox(0F, 0F, 0F, 2, 3, 10);
		Arm2.setRotationPoint(30F, 7F, 3F);
		Arm2.setTextureSize(256, 256);
		Arm2.mirror = true;
		setRotation(Arm2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Hide.render(f5);
		Floor.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Arm1.render(f5);
		Arm2.render(f5);
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
		return Arrays.asList(Hide, Floor, Leg1, Leg2, Leg3, Leg4, Arm1, Arm2);
	}

}
