package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.Bait;
import net.minecraft.src.nucleareal.animalcrossing.NuggetValue;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFishingBait extends ItemFood
{
	private Icon[] Icons;

	public ItemFishingBait(int id, int healAmount, boolean isMeat)
	{
		super(id, healAmount, isMeat);
		setMaxStackSize(64);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	public void getSubItems(int itemID, CreativeTabs tab, List list)
    {
        for (int j = 0; j < Bait.size(); ++j)
        {
            list.add(new ItemStack(itemID, 1, j));
        }
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister registerer)
    {
		Icons = new Icon[Bait.size()];
		for(int i = 0; i < Bait.size(); i++)
		{
			Icons[i] = registerer.registerIcon(getIconString()+"_"+Bait.of(i).name());
		}
    }

	public int getMetadata(int i)
    {
        return i;
    }

	@SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return Icons[par1];
    }
}
