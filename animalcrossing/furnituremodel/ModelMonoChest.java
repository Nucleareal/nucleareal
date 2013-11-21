package net.minecraft.src.nucleareal.animalcrossing.furnituremodel;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.ModelNuclearealBase;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;

public class ModelMonoChest extends ModelNuclearealBase
{
	// fields
	ModelRenderer Head;
	ModelRenderer Bottom;
	ModelRenderer Dox1;
	ModelRenderer Dox2;
	ModelRenderer Oku;
	ModelRenderer Knock1;
	ModelRenderer Knock2;
	ModelRenderer Knock3;
	ModelRenderer Knock4;
	ModelRenderer Knock5;

	public ModelMonoChest()
	{
		textureWidth = 256;
		textureHeight = 256;

		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(0F, 0F, 0F, 16, 2, 16);
		Head.setRotationPoint(0F, 0F, 0F);
		Head.setTextureSize(256, 256);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Bottom = new ModelRenderer(this, 64, 0);
		Bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		Bottom.setRotationPoint(0F, 14F, 0F);
		Bottom.setTextureSize(256, 256);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		Dox1 = new ModelRenderer(this, 128, 0);
		Dox1.addBox(0F, 0F, 0F, 16, 4, 16);
		Dox1.setRotationPoint(0F, 2F, 0F);
		Dox1.setTextureSize(256, 256);
		Dox1.mirror = true;
		setRotation(Dox1, 0F, 0F, 0F);
		Dox2 = new ModelRenderer(this, 128, 0);
		Dox2.addBox(0F, 0F, 0F, 16, 4, 16);
		Dox2.setRotationPoint(0F, 10F, 0F);
		Dox2.setTextureSize(256, 256);
		Dox2.mirror = true;
		setRotation(Dox2, 0F, 0F, 0F);
		Oku = new ModelRenderer(this, 192, 0);
		Oku.addBox(0F, 0F, 0F, 16, 4, 15);
		Oku.setRotationPoint(0F, 6F, 1F);
		Oku.setTextureSize(256, 256);
		Oku.mirror = true;
		setRotation(Oku, 0F, 0F, 0F);
		Knock1 = new ModelRenderer(this, 0, 18);
		Knock1.addBox(0F, 0F, 0F, 16, 4, 1);
		Knock1.setRotationPoint(0F, 6F, 0F);
		Knock1.setTextureSize(256, 256);
		Knock1.mirror = true;
		setRotation(Knock1, 0F, 0F, 0F);
		Knock2 = new ModelRenderer(this, 34, 18);
		Knock2.addBox(0F, 0F, 0F, 1, 3, 14);
		Knock2.setRotationPoint(1F, 7F, 1F);
		Knock2.setTextureSize(256, 256);
		Knock2.mirror = true;
		setRotation(Knock2, 0F, 0F, 0F);
		Knock3 = new ModelRenderer(this, 34, 18);
		Knock3.addBox(0F, 0F, 0F, 1, 3, 14);
		Knock3.setRotationPoint(14F, 7F, 1F);
		Knock3.setTextureSize(256, 256);
		Knock3.mirror = true;
		setRotation(Knock3, 0F, 0F, 0F);
		Knock4 = new ModelRenderer(this, 64, 18);
		Knock4.addBox(0F, 0F, 0F, 14, 3, 1);
		Knock4.setRotationPoint(1F, 7F, 14F);
		Knock4.setTextureSize(256, 256);
		Knock4.mirror = true;
		setRotation(Knock4, 0F, 0F, 0F);
		Knock5 = new ModelRenderer(this, 0, 35);
		Knock5.addBox(0F, 0F, 0F, 14, 1, 14);
		Knock5.setRotationPoint(1F, 9F, 1F);
		Knock5.setTextureSize(256, 256);
		Knock5.mirror = true;
		setRotation(Knock5, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		Bottom.render(f5);
		Dox1.render(f5);
		Dox2.render(f5);
		Oku.render(f5);
		Knock1.render(f5);
		Knock2.render(f5);
		Knock3.render(f5);
		Knock4.render(f5);
		Knock5.render(f5);
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
		return Arrays.asList(Head, Bottom, Dox1, Dox2, Oku, Knock1, Knock2, Knock3, Knock4, Knock5);
	}

	@Override
	protected void onPreRender(TileFurniture tile)
	{
		double dp = tile.getOpeningOffset();
		Knock1.offsetZ = (float)dp;
		Knock2.offsetZ = (float)dp;
		Knock3.offsetZ = (float)dp;
		Knock4.offsetZ = (float)dp;
		Knock5.offsetZ = (float)dp;
	}
}
