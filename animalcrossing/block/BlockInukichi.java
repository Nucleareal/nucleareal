package net.minecraft.src.nucleareal.animalcrossing.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiShopStructure;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInukichi extends Block
{
	public BlockInukichi(int i)
	{
		super(i, Material.iron);
	}

	@SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        return getIcon(side, par1IBlockAccess.getBlockMetadata(x, y, z));
    }

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack ist)
	{
		if(world.getBlockMetadata(x, y, z) == 0)
		{
			InukichiShopStructure.generate(world, x, y, z);
		}
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
    {
		super.updateTick(world, x, y, z, rand);
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
    	switch(meta)
    	{
    		//defaults
    		default: return Block.stone.getIcon(side, 0);

    		//Ticker
    		case 0: return Block.bedrock.getIcon(side, 0);

    		//Wood
    		case 1: case 2: case 3: case 4:
    		return Block.wood.getIcon(side, (meta-1)*4);

    		//Planks
    		case 5:
    		return Block.planks.getIcon(side, 0);

    		//GlowStone
    		case 6:
    		return Block.glowStone.getIcon(side, 0);

    		//not removable
    		case 12:
    		//Removable walls
    		case 13: case 14: case 15: return Block.cobblestone.getIcon(side, 0);
    	}
    }

    public static String getDisplayName(int meta)
    {
    	switch(meta)
    	{
    		default: return "NoName";

    		case 0: return "Ticker";

    		case 1: case 2: case 3: case 4: return "Wood";

    		case 5: return "Planks";

    		case 6: return "GlowStone";

    		case 12: return "Wall";

    		case 13: case 14: case 15: return "Level"+(3-(meta-13))+" Wall";
    	}
    }

	@Override
	public int quantityDropped(Random par1Random)
    {
		return 0;
    }

	@Override
    public int damageDropped(int damage)
    {
    	return damage;
    }

	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 16; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }

    @Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
		return world.getBlockMetadata(x, y, z) == 6 ? 15 : 0;
    }

	public static int getStairMetadata()
	{
		return 12;
	}
}
