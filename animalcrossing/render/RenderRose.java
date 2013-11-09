package net.minecraft.src.nucleareal.animalcrossing.render;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockColorableFlower;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderRose implements ISimpleBlockRenderingHandler
{
	private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	public RenderRose()
	{
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		return;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId != getRenderId() || !(block instanceof BlockColorableFlower)) return false;

		BlockColorableFlower blockf = (BlockColorableFlower) block;

		int meta = world.getBlockMetadata(x, y, z);

		Random rand = new Random(new Position(x,y,z).getVisualityValue().hashCode());

		for(int pc = 0; pc < 3; pc++)
		{
			for(int i = 0; i < 2; i++)
			{
				BlockColorableFlower.setRenderLayer(i);
				doRender(world, x, y, z, meta, blockf, renderer, rand, i, pc);
			}
		}
		return true;
	}

	private void doRender(IBlockAccess world, int x, int y, int z, int meta, BlockColorableFlower blockf, RenderBlocks renderer, Random rand, int layer, int pc)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(blockf.getMixedBrightnessForBlock(world, x, y, z));
		float f = 1.0F;
		int l = blockf.getMultiplierColor(layer, meta);

		boolean isWither = blockf.blockID != AnimalCrossing.Rose.blockID;

		boolean hasBlink = RoseColor.of(meta).hasBlink();

		double rrrr = 4D;
		float rate = (float)( (1D - (1D / rrrr)) + rand.nextDouble() / (rrrr / 2D) );

		float f1 = (float) (l >> 16 & 255) / 255.0F;
		float f2 = (float) (l >> 8 & 255) / 255.0F;
		float f3 = (float) (l & 255) / 255.0F;

		if(isWither)
		{
			f1 *= 0.375F;
			f2 *= 0.25F;
			f3 *= 0.25F;
		}
		if (EntityRenderer.anaglyphEnable)
		{
			float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f4;
			f2 = f5;
			f3 = f6;
		}

		if(hasBlink) f *= rate;
		tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
		double d0 = (double) x + (addtions[pc][0] * .35D);
		double d1 = (double) y - (addtions[pc][2]);
		double d2 = (double) z + (addtions[pc][1] * .35D);
		renderer.drawCrossedSquares(blockf, world.getBlockMetadata(x, y, z), d0, d1, d2, 1.0F);
	}

	private void bindTexture(ResourceLocation location)
	{
		ModLoader.getMinecraftInstance().getTextureManager().bindTexture(location);
	}

	public static double ct = (Math.PI/2D);
	public static double rate = (Math.PI * 2D) / 3D;
	public static double[][] addtions =
		{
			{ Math.cos(ct+rate*0), Math.sin(ct+rate*0), .0D },
			{ Math.cos(ct+rate*1), Math.sin(ct+rate*1), .1D },
			{ Math.cos(ct+rate*2), Math.sin(ct+rate*2), .2D },
		};

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return AnimalCrossing.RoseRenderID;
	}
}
