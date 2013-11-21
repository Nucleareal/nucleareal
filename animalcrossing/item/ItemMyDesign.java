package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.FishType;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;

public class ItemMyDesign extends ItemBlockWithMetadata
{
	public ItemMyDesign(int par1, Block par2Block)
	{
		super(par1, par2Block);
	}

	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < 16; i++)
        {
        	par3List.add(new ItemStack(this, 1, i));
        }
    }

	@Override
	public void addInformation(ItemStack ist, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(ist, player, list, par4);
	}

    public String getUnlocalizedName(ItemStack ist)
    {
    	return super.getUnlocalizedName(ist) + "." + ist.getItemDamage();
    }
}
