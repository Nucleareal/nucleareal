package net.minecraft.src.nucleareal.animalcrossing.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMyDesign extends BlockCarpet
{
	public BlockMyDesign(int i)
	{
		super(i);
        setCreativeTab(AnimalCrossing.AnimalCrossingTab);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
    	blockIcon = register.registerIcon(getTextureName());
    }

    public Icon getIcon(int side, int metadata)
    {
        return blockIcon;
    }

    public int getRenderType()
    {
        return AnimalCrossing.MyDesignRenderID;
    }
}
