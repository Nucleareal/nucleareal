package net.minecraft.src.nucleareal.animalcrossing.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderFallingSand;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFallingTreePart;
import net.minecraft.src.nucleareal.animalcrossing.model.ModelBlock;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderTreePart extends RenderFallingSand
{
	private final RenderBlocks Renderer = new RenderBlocks();
	private final ModelBlock mb = new ModelBlock();

	public RenderTreePart()
	{
		System.out.println("Render Constructed");
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderFallingSand((EntityFallingTreePart)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	public void doRenderFallingSand(EntityFallingSand entity, double x, double y, double z, float par8, float par9)
    {
		doRenderFallingTreePart((EntityFallingTreePart)entity, x, y, z, par8, par9, true);
    }

	public void doRenderFallingTreePart(EntityFallingTreePart entity, double x, double y, double z, float par8, float par9, boolean isClient)
    {
        World world = entity.getWorld();

        if(isClient)
        {
        	doRenderFallingTreePart(entity, x, y, z, par8, par9, false);
        }
        else
        {
        	world = UtilMinecraft.getWorldAndPlayer(ModLoader.getMinecraftInstance().thePlayer.username).getV1();
        }

        Block block = Block.blocksList[entity.varielblockID];

        if(block == null)
        {
        	block = Block.wood;
        	entity.varielblockID = block.blockID;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);

        GL11.glRotatef(entity.rotationYaw, 0F, 0F, 1F);

        bindEntityTexture(entity);
        GL11.glDisable(GL11.GL_LIGHTING);
        Tessellator tessellator;

        int color = block.colorMultiplier(world, (int)x, (int)y, (int)z);

        float cr = (float)(color >> 16 & 255) / 255.0F;
        float cg = (float)(color >> 8 & 255) / 255.0F;
        float cb = (float)(color & 255) / 255.0F;
        GL11.glColor4f(cr, cg, cb, 1F);

        Renderer.setRenderBoundsFromBlock(block);
        renderBlockSandFalling(block, world, MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ), entity.varielMetadata,
        		cr, cg, cb);
        //mb.render(entity, (float)x, (float)y, (float)z, par8, par9, 1F);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	public void renderBlockSandFalling(Block block, World par2World, int par3, int par4, int par5, int par6, float r, float g, float b)
    {
        float f0 = 0.5F;
        float f1 = 1.0F;
        float f2 = 0.8F;
        float f3 = 0.6F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(block.getMixedBrightnessForBlock(par2World, par3, par4, par5));
        float f4 = 1.0F;
        float f5 = 1.0F;

        if (f5 < f4)
        {
            f5 = f4;
        }

        tessellator.setColorOpaque_F(f0 * f5 * r, f0 * f5 * g, f0 * f5 * b);
        Renderer.renderFaceYNeg(block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(block, 0, par6));
        f5 = 1.0F;

        if (f5 < f4)
        {
            f5 = f4;
        }

        tessellator.setColorOpaque_F(f1 * f5 * r, f1 * f5 * g, f1 * f5 * b);
        Renderer.renderFaceYPos(block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(block, 1, par6));
        f5 = 1.0F;

        if (f5 < f4)
        {
            f5 = f4;
        }

        tessellator.setColorOpaque_F(f2 * f5 * r, f2 * f5 * g, f2 * f5 * b);
        Renderer.renderFaceZNeg(block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(block, 2, par6));
        f5 = 1.0F;

        if (f5 < f4)
        {
            f5 = f4;
        }

        tessellator.setColorOpaque_F(f2 * f5 * r, f2 * f5 * g, f2 * f5 * b);
        Renderer.renderFaceZPos(block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(block, 3, par6));
        f5 = 1.0F;

        if (f5 < f4)
        {
            f5 = f4;
        }

        tessellator.setColorOpaque_F(f3 * f5 * r, f3 * f5 * g, f3 * f5 * b);
        Renderer.renderFaceXNeg(block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(block, 4, par6));
        f5 = 1.0F;

        if (f5 < f4)
        {
            f5 = f4;
        }

        tessellator.setColorOpaque_F(f3 * f5 * r, f3 * f5 * g, f3 * f5 * b);
        Renderer.renderFaceXPos(block, -0.5D, -0.5D, -0.5D, getBlockIconFromSideAndMetadata(block, 5, par6));
        tessellator.draw();
    }

	public Icon getBlockIconFromSideAndMetadata(Block par1Block, int par2, int par3)
    {
        return this.getIconSafe(par1Block.getIcon(par2, par3));
    }

    public Icon getIconSafe(Icon par1Icon)
    {
        if (par1Icon == null)
        {
            par1Icon = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
        }
        return (Icon)par1Icon;
    }
}
