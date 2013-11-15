package net.minecraft.src.nucleareal.animalcrossing.furnituremodel;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.ModelNuclearealBase;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;

public class ModelMonoWallClock extends ModelNuclearealBase
{
	// fields
	ModelRenderer Base;
	ModelRenderer MinuteN;
	ModelRenderer HourN;

	public ModelMonoWallClock()
	{
		textureWidth = 256;
		textureHeight = 256;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 16, 16, 1);
		Base.setRotationPoint(0F, 0F, 15F);
		Base.setTextureSize(256, 256);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		MinuteN = new ModelRenderer(this, 34, 0);
		MinuteN.addBox(-0.5F, -7.5F, -1F, 1, 8, 1);
		MinuteN.setRotationPoint(8F, 8F, 15F);
		MinuteN.setTextureSize(256, 256);
		MinuteN.mirror = true;
		setRotation(MinuteN, 0F, 0F, 0F);
		HourN = new ModelRenderer(this, 38, 0);
		HourN.addBox(-0.5F, -3.5F, -1F, 1, 4, 1);
		HourN.setRotationPoint(8F, 8F, 15F);
		HourN.setTextureSize(256, 256);
		HourN.mirror = true;
		setRotation(HourN, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Base.render(f5);
		MinuteN.render(f5);
		HourN.render(f5);
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
		return Arrays.asList(Base, MinuteN, HourN);
	}

	@Override
	protected void onPreRender(TileEntityFurniture tile)
	{
		long l = tile.worldObj.getWorldInfo().getWorldTime() % 24000L;
		double hr = ((l / 1000L) / 24D	) * Math.PI * 2D;
		double mr = (int)((l % 1000L)*(60D / 1000D)) * (Math.PI * 2D / 60D);
		setRotation(MinuteN	, 0F, 0F, (float)mr);
		setRotation(HourN	, 0F, 0F, (float)hr);
		//1Day		= 24000
		//1Hour		= 24000 / 24
		//1Minute	= 24000 / 24 / 60
	}
}
