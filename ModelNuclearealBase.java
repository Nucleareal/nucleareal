package net.minecraft.src.nucleareal;

import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;

public abstract class ModelNuclearealBase extends ModelBase
{
	protected abstract List<ModelRenderer> getAllModelPartsAsList();

	public void renderAll(TileFurniture tile, float f)
	{
		List<ModelRenderer> array = getAllModelPartsAsList();
		onPreRender(tile);
		for(ModelRenderer model : array)
		{
			model.render(f);
		}
		onPostRender(tile);
	}

	protected void onPreRender(TileFurniture tile)
	{
	}

	protected void onPostRender(TileFurniture tile)
	{
	}
}
