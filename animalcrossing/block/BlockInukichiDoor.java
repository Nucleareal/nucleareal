package net.minecraft.src.nucleareal.animalcrossing.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInukichiDoor extends BlockDoor
{
	public BlockInukichiDoor(int i)
	{
		super(i, Material.iron);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz)
    {
		return false;
    }

	@Override
	public void onPoweredBlockChange(World world, int x, int y, int z, boolean par5)
    {
    }

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
		return (par1 & 8) != 0 ? 0 : blockID;
    }

	@Override
	public int getMobilityFlag()
    {
        return 2;
    }

	public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return blockID;
    }

	private Icon[][] icons;

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
		icons = new Icon[2][2];
		icons[0][0] = ir.registerIcon(getTextureName()+"_upper");
		icons[0][1] = new IconFlipped(icons[0][0], true, false);
		icons[1][0] = ir.registerIcon(getTextureName()+"_lower");
		icons[1][1] = new IconFlipped(icons[1][0], true, false);
    }

	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
    {
		switch(side)
		{
			case 0: case 1:
			{
				return icons[0][1];
			}
			default:
			{
				int fmeta = getFullMetadata(world, x, y, z);
				int and = fmeta & 3;
				boolean isOpen = (fmeta & 4) != 0;
				boolean isReversed = false;
				boolean isUpper = (fmeta & 8) != 0;

				if(isOpen)
				{
					if(and == 0 && side == 2) isReversed = !isReversed;
					if(and == 1 && side == 5) isReversed = !isReversed;
					if(and == 2 && side == 3) isReversed = !isReversed;
					if(and == 3 && side == 4) isReversed = !isReversed;
				}
				else
				{
					if(and == 0 && side == 5) isReversed = !isReversed;
					if(and == 1 && side == 3) isReversed = !isReversed;
					if(and == 2 && side == 4) isReversed = !isReversed;
					if(and == 3 && side == 2) isReversed = !isReversed;
					if ((fmeta & 16) != 0) isReversed = !isReversed;
				}
				return isUpper ? icons[0][isReversed ? 1 : 0] : icons[1][isReversed ? 1 : 0];
			}
		}
    }

	public Icon getIcon(int par1, int par2)
    {
		return icons[0][0];
    }
}
