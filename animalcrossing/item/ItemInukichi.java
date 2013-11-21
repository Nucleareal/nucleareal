package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.util.Icon;

public class ItemInukichi extends ItemBlock
{
	public ItemInukichi(int i, Block block)
	{
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	public int getMetadata(int par1)
    {
        return par1;
    }

    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < 16; i++)
        {
        	par3List.add(new ItemStack(this, 1, i));
        }
    }

    public String getUnlocalizedName(ItemStack ist)
    {
    	return super.getUnlocalizedName(ist) + "." + ist.getItemDamage();
    }
}
