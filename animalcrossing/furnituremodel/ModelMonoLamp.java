package net.minecraft.src.nucleareal.animalcrossing.furnituremodel;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.ModelNuclearealBase;

public class ModelMonoLamp extends ModelNuclearealBase
{
	// fields
	ModelRenderer Panel1;
	ModelRenderer Panel2;
	ModelRenderer Panel3;
	ModelRenderer Panel4;
	ModelRenderer Panel5;
	ModelRenderer Panel6;
	ModelRenderer Panel7;
	ModelRenderer Panel8;
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Peer;
	ModelRenderer Circle;

	public ModelMonoLamp()
	{
		textureWidth = 256;
		textureHeight = 256;

		Panel1 = new ModelRenderer(this, 1, 0);
		Panel1.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel1.setRotationPoint(8F, 0F, 8F);
		Panel1.setTextureSize(256, 256);
		Panel1.mirror = true;
		setRotation(Panel1, 0F, 0.3926991F, 0F);
		Panel2 = new ModelRenderer(this, 13, 0);
		Panel2.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel2.setRotationPoint(8F, 0F, 8F);
		Panel2.setTextureSize(256, 256);
		Panel2.mirror = true;
		setRotation(Panel2, 0F, 1.178097F, 0F);
		Panel3 = new ModelRenderer(this, 1, 0);
		Panel3.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel3.setRotationPoint(8F, 0F, 8F);
		Panel3.setTextureSize(256, 256);
		Panel3.mirror = true;
		setRotation(Panel3, 0F, 1.963495F, 0F);
		Panel4 = new ModelRenderer(this, 13, 0);
		Panel4.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel4.setRotationPoint(8F, 0F, 8F);
		Panel4.setTextureSize(256, 256);
		Panel4.mirror = true;
		setRotation(Panel4, 0F, 2.748893F, 0F);
		Panel5 = new ModelRenderer(this, 1, 0);
		Panel5.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel5.setRotationPoint(8F, 0F, 8F);
		Panel5.setTextureSize(256, 256);
		Panel5.mirror = true;
		setRotation(Panel5, 0F, 3.534292F, 0F);
		Panel6 = new ModelRenderer(this, 13, 0);
		Panel6.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel6.setRotationPoint(8F, 0F, 8F);
		Panel6.setTextureSize(256, 256);
		Panel6.mirror = true;
		setRotation(Panel6, 0F, 4.31969F, 0F);
		Panel7 = new ModelRenderer(this, 1, 0);
		Panel7.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel7.setRotationPoint(8F, 0F, 8F);
		Panel7.setTextureSize(256, 256);
		Panel7.mirror = true;
		setRotation(Panel7, 0F, 5.105088F, 0F);
		Panel8 = new ModelRenderer(this, 13, 0);
		Panel8.addBox(-2F, 0F, -5F, 4, 8, 1);
		Panel8.setRotationPoint(8F, 0F, 8F);
		Panel8.setTextureSize(256, 256);
		Panel8.mirror = true;
		setRotation(Panel8, 0F, 5.890486F, 0F);
		Base1 = new ModelRenderer(this, 24, 0);
		Base1.addBox(0F, 0F, 0F, 8, 1, 8);
		Base1.setRotationPoint(4F, 15F, 4F);
		Base1.setTextureSize(256, 256);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 56, 0);
		Base2.addBox(0F, 0F, 0F, 6, 1, 6);
		Base2.setRotationPoint(5F, 14F, 5F);
		Base2.setTextureSize(256, 256);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Peer = new ModelRenderer(this, 80, 0);
		Peer.addBox(0F, 0F, 0F, 2, 10, 2);
		Peer.setRotationPoint(7F, 4F, 7F);
		Peer.setTextureSize(256, 256);
		Peer.mirror = true;
		setRotation(Peer, 0F, 0F, 0F);
		Circle = new ModelRenderer(this, 88, 0);
		Circle.addBox(0F, 0F, 0F, 4, 4, 4);
		Circle.setRotationPoint(6F, 5F, 6F);
		Circle.setTextureSize(256, 256);
		Circle.mirror = true;
		setRotation(Circle, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Panel1.render(f5);
		Panel2.render(f5);
		Panel3.render(f5);
		Panel4.render(f5);
		Panel5.render(f5);
		Panel6.render(f5);
		Panel7.render(f5);
		Panel8.render(f5);
		Base1.render(f5);
		Base2.render(f5);
		Peer.render(f5);
		Circle.render(f5);
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
		return Arrays.asList(Panel1, Panel2, Panel3, Panel4, Panel5, Panel6, Panel7, Panel8,
				Base1, Base2, Peer, Circle);
	}

}
