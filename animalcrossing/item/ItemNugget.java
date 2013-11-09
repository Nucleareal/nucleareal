package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.NuggetValue;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.util.Icon;

public class ItemNugget extends Item
{
	private Icon[] Icons;

	public ItemNugget(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage)
	{
		return Icons[damage];
	}

	public void getSubItems(int itemID, CreativeTabs tab, List list)
    {
        for (int j = 0; j < NuggetValue.getAllElements().size(); ++j)
        {
            list.add(new ItemStack(itemID, 1, j));
        }
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister registerer)
    {
		Icons = new Icon[NuggetValue.getAllElements().size()];
		for(int i = 0; i < NuggetValue.getAllElements().size(); i++)
		{
			Icons[i] = registerer.registerIcon(getIconString()+NuggetValue.of(i).name());
		}
    }

	public int getMetadata(int i)
    {
        return i;
    }

    public String getUnlocalizedName(ItemStack ist)
    {
    	return super.getUnlocalizedName(ist) + "." + NuggetValue.of(ist.getItemDamage()).name();
    }
}
