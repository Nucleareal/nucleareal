package net.minecraft.src.nucleareal.animalcrossing.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockMyDesign;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderMyDesign implements ISimpleBlockRenderingHandler
{
	ResourceLocation defaultTexture = new ResourceLocation("nc:textures/blocks/MyDesign_Default.png");

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		int meta = world.getBlockMetadata(x, y, z);
		BlockMyDesign blockm = (BlockMyDesign) AnimalCrossing.MyDesign;

		doRender(world, x, y, z, meta, blockm, renderer);

		return true;
	}

	private void doRender(IBlockAccess world, int x, int y, int z, int meta, BlockMyDesign blockm, RenderBlocks render)
	{
		bindTexture(defaultTexture);

		render.renderStandardBlock(AnimalCrossing.MyDesign, x, y, z);

		bindTexture(TextureMap.locationBlocksTexture);
	}

	private void bindTexture(ResourceLocation location)
	{
		ModLoader.getMinecraftInstance().getTextureManager().bindTexture(location);
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return AnimalCrossing.MyDesignRenderID;
	}
}
