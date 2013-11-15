package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.FishType;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFish extends ItemFood
{
	public ItemFish(int id)
	{
		super(id, 3, false);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	@SideOnly(Side.CLIENT)
	private Icon[] Icons;

	public void getSubItems(int itemID, CreativeTabs tab, List list)
    {
        for (int j = 0; j < FishType.size(); ++j)
        {
            list.add(new ItemStack(itemID, 1, j));
        }
        for(int j = 0; j < FishType.size(); ++j)
        {
        	list.add(new ItemStack(itemID, 1, FishType.getRegisterLimit()+j));
        }
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister registerer)
    {
		Icons = new Icon[FishType.size()];
		for(int i = 0; i < FishType.size(); i++)
		{
			Icons[i] = registerer.registerIcon(getIconString()+"_"+FishType.of(i).name());
		}
    }

	public int getMetadata(int i)
    {
        return i;
    }

	@Override
	public void addInformation(ItemStack ist, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(ist, player, list, par4);
	}

    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        if (RoseColor.RoseColorNames == null)
        {
            return super.getUnlocalizedName(par1ItemStack);
        }
        else
        {
            int i = par1ItemStack.getItemDamage();
            return i >= 0 && i < (FishType.size() + FishType.getRegisterLimit()) ?
            		super.getUnlocalizedName(par1ItemStack) + "." + FishType.of(i%FishType.getRegisterLimit()).name() +
            		(i >= FishType.getRegisterLimit() ? "Cooked" : "Raw")
            		:
            		super.getUnlocalizedName(par1ItemStack);
        }
    }

	@SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return Icons[par1%FishType.getRegisterLimit()];
    }
}
