package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.util.Icon;

public class ItemFurniture extends ItemColored
{
	private BlockFurniture Furniture;
	private Icon FurnitureIcon;

	public ItemFurniture(int par1, Block block)
	{
		super(par1, true);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack ist)
    {
        return	EnumFurniture.of(ist.getItemDamage()).getRarity();
    }

    public int getMetadata(int par1)
    {
        return par1;
    }

    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < EnumFurniture.size(); i++)
        {
        	par3List.add(new ItemStack(this, 1, i));
        }
    }

    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName(par1ItemStack) + "." + par1ItemStack.getItemDamage();
    }
}
