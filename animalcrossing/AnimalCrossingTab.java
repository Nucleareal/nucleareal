package net.minecraft.src.nucleareal.animalcrossing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class AnimalCrossingTab extends CreativeTabs
{
	public AnimalCrossingTab(String label)
	{
		super(label);
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack()
    {
        return new ItemStack(AnimalCrossing.PachinkoGold,1,1);
    }
}
