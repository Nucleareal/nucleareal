package net.minecraft.src.nucleareal.animalcrossing.furnituremodel;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.ModelNuclearealBase;

public class ModelMonoLowTable extends ModelNuclearealBase
{
	// fields
	ModelRenderer Top1;
	ModelRenderer Top2;
	ModelRenderer Top3;
	ModelRenderer Top4;
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Base3;
	ModelRenderer Base4;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;

	public ModelMonoLowTable()
	{
		textureWidth = 256;
		textureHeight = 256;

		Top1 = new ModelRenderer(this, 0, 0);
		Top1.addBox(0F, 0F, 0F, 32, 1, 1);
		Top1.setRotationPoint(0F, 4F, 0F);
		Top1.setTextureSize(256, 256);
		Top1.mirror = true;
		setRotation(Top1, 0F, 0F, 0F);
		Top2 = new ModelRenderer(this, 0, 0);
		Top2.addBox(0F, 0F, 0F, 32, 1, 1);
		Top2.setRotationPoint(0F, 4F, 15F);
		Top2.setTextureSize(256, 256);
		Top2.mirror = true;
		setRotation(Top2, 0F, 0F, 0F);
		Top3 = new ModelRenderer(this, 0, 2);
		Top3.addBox(0F, 0F, 0F, 1, 1, 16);
		Top3.setRotationPoint(0F, 4F, 0F);
		Top3.setTextureSize(256, 256);
		Top3.mirror = true;
		setRotation(Top3, 0F, 0F, 0F);
		Top4 = new ModelRenderer(this, 0, 2);
		Top4.addBox(0F, 0F, 0F, 1, 1, 16);
		Top4.setRotationPoint(31F, 4F, 0F);
		Top4.setTextureSize(256, 256);
		Top4.mirror = true;
		setRotation(Top4, 0F, 0F, 0F);
		Base1 = new ModelRenderer(this, 0, 19);
		Base1.addBox(0F, 0F, 0F, 32, 2, 3);
		Base1.setRotationPoint(0F, 5F, 0F);
		Base1.setTextureSize(256, 256);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 0, 19);
		Base2.addBox(0F, 0F, 0F, 32, 2, 3);
		Base2.setRotationPoint(0F, 5F, 13F);
		Base2.setTextureSize(256, 256);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Base3 = new ModelRenderer(this, 0, 24);
		Base3.addBox(0F, 0F, 0F, 3, 2, 16);
		Base3.setRotationPoint(0F, 5F, 0F);
		Base3.setTextureSize(256, 256);
		Base3.mirror = true;
		setRotation(Base3, 0F, 0F, 0F);
		Base4 = new ModelRenderer(this, 0, 24);
		Base4.addBox(0F, 0F, 0F, 3, 2, 16);
		Base4.setRotationPoint(29F, 5F, 0F);
		Base4.setTextureSize(256, 256);
		Base4.mirror = true;
		setRotation(Base4, 0F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 66, 0);
		Leg1.addBox(0F, 0F, 0F, 2, 9, 2);
		Leg1.setRotationPoint(1F, 7F, 1F);
		Leg1.setTextureSize(256, 256);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 66, 0);
		Leg2.addBox(0F, 0F, 0F, 2, 9, 2);
		Leg2.setRotationPoint(29F, 7F, 1F);
		Leg2.setTextureSize(256, 256);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 66, 0);
		Leg3.addBox(0F, 0F, 0F, 2, 9, 2);
		Leg3.setRotationPoint(1F, 7F, 13F);
		Leg3.setTextureSize(256, 256);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 66, 0);
		Leg4.addBox(0F, 0F, 0F, 2, 9, 2);
		Leg4.setRotationPoint(29F, 7F, 13F);
		Leg4.setTextureSize(256, 256);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Top1.render(f5);
		Top2.render(f5);
		Top3.render(f5);
		Top4.render(f5);
		Base1.render(f5);
		Base2.render(f5);
		Base3.render(f5);
		Base4.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
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
		return Arrays.asList(Top1, Top2, Top3, Top4, Base1, Base2, Base3, Base4, Leg1, Leg2, Leg3, Leg4);
	}

}
