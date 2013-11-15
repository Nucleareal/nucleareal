package net.minecraft.src.nucleareal.animalcrossing.render;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.nucleareal.ModelNuclearealBase;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderFurniture extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
	{
		TileEntityFurniture tile = (TileEntityFurniture) tileentity;
		doRenderFurniture(tile, d0, d1, d2, f);
	}

	private void doRenderFurniture(TileEntityFurniture tile, double x, double y, double z, float f)
	{
		int meta = tile.getFurnitureIndex();
		EnumFurniture e = EnumFurniture.of(meta);
		ModelNuclearealBase model = e.getModel();

		float subY = 0F;
		int uid = tile.worldObj.getBlockId(tile.xCoord, tile.yCoord-1, tile.zCoord);
		if(uid == AnimalCrossing.Furniture.blockID)
		{
			EnumFurniture under = EnumFurniture.of(((TileEntityFurniture)tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord-1, tile.zCoord)).getMetadata());
			subY = under.getSubtractYParam();
		}

		for (int i = 0; i < e.getRenderCount(); i++)
		{
			this.bindTexture(e.getTexture());
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			GL11.glTranslatef((float) x, (float) y + 1.0F - subY, (float) z + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);

			float rotation = tile.getBlockRotation(i);

			GL11.glRotatef((float)rotation, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tile.onRender(i);

			model.renderAll(tile, 0.0625F);

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId != getRenderId() || world.getBlockMetadata(x, y, z) >= EnumFurniture.size())
			return false;

		// Nothing to do Because renderTileEntityAt do all Them :)

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
	}

	@Override
	public int getRenderId()
	{
		return AnimalCrossing.FurnitureRenderID;
	}
}
