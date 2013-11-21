package net.minecraft.src.nucleareal.animalcrossing.inukichi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.src.nucleareal.animalcrossing.GeneratorInukichiShop;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureShopCompass extends TextureAtlasSprite
{
	public TextureShopCompass(String name)
	{
		super(name);
	}

	public double currentAngle;
	public double angleDelta;

	public void updateAnimation()
	{
		Minecraft minecraft = Minecraft.getMinecraft();

		if (minecraft.theWorld != null && minecraft.thePlayer != null)
		{
			updateCompass(minecraft.theWorld, minecraft.thePlayer.posX, minecraft.thePlayer.posZ, (double)minecraft.thePlayer.rotationYaw, false, false);
		}
		else
		{
			this.updateCompass((World)null, 0.0D, 0.0D, 0.0D, true, false);
		}
	}

	/**
	 * Updates the compass based on the given x,z coords and camera direction
	 */
	public void updateCompass(World par1World, double x, double z, double r, boolean par8, boolean par9)
	{
		if (!this.framesTextureData.isEmpty())
		{
			double d3 = 0.0D;

			if (par1World != null && !par8)
			{
				double d4 = (double) GeneratorInukichiShop.generatedX - x;
				double d5 = (double) GeneratorInukichiShop.generatedZ - z;

				r %= 360.0D;
				d3 = -((r - 90.0D) * Math.PI / 180.0D - Math.atan2(d5, d4));

				if (!par1World.provider.isSurfaceWorld() || !GeneratorInukichiShop.getIsGenerated())
				{
					d3 = Math.random() * Math.PI * 2.0D;
				}
			}

			if (par9)
			{
				this.currentAngle = d3;
			}
			else
			{
				double d6;

				for (d6 = d3 - this.currentAngle; d6 < -Math.PI; d6 += (Math.PI * 2D))
				{
					;
				}

				while (d6 >= Math.PI)
				{
					d6 -= (Math.PI * 2D);
				}

				if (d6 < -1.0D)
				{
					d6 = -1.0D;
				}

				if (d6 > 1.0D)
				{
					d6 = 1.0D;
				}

				this.angleDelta += d6 * 0.1D;
				this.angleDelta *= 0.8D;
				this.currentAngle += this.angleDelta;
			}

			int i;

			for (i = (int) ((this.currentAngle / (Math.PI * 2D) + 1.0D) * (double) this.framesTextureData
					.size()) % this.framesTextureData.size(); i < 0; i = (i + this.framesTextureData
					.size()) % this.framesTextureData.size())
			{
				;
			}

			if (i != this.frameCounter)
			{
				this.frameCounter = i;
				TextureUtil.uploadTextureSub(
						(int[]) this.framesTextureData.get(this.frameCounter),
						this.width, this.height, this.originX, this.originY,
						false, false);
			}
		}
	}
}
