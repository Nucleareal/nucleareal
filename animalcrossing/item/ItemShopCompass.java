package net.minecraft.src.nucleareal.animalcrossing.item;

import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.util.Icon;

public class ItemShopCompass extends Item
{
	public ItemShopCompass(int par1)
	{
		super(par1);
		setMaxStackSize(1);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
		itemIcon = register.registerIcon(getIconString());
    }
}
