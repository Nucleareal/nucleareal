package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockColorableFlower;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;

public class ItemColorableFlower extends ItemColored
{
	private BlockColorableFlower block;
	private Icon MainIcon;
	private Icon MultiIcon;

	public ItemColorableFlower(int i, Block block)
	{
		this(i, (BlockColorableFlower)block);
	}

	public ItemColorableFlower(int par1, BlockColorableFlower block)
	{
		super(par1, true);
		this.block = block;
	}

	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack ist, int layer)
    {
		if(layer == 1)
    	{
    		return RoseColor.of(ist.getItemDamage()).getColor();
    	}
    	else
    	{
    		return 0xFFFFFF;
    	}
    }

	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack ist)
    {
        return RoseColor.of(ist.getItemDamage()).hasEffect();
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack ist)
    {
        return	ist.getItemDamage() >= RoseColor.Gold.ordinal() ? EnumRarity.epic :
        		ist.getItemDamage() >= RoseColor.Blue.ordinal() ? EnumRarity.rare :
        		super.getRarity(ist);
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamageForRenderPass(int par1, int par2)
    {
        if(par2 == 1)
        {
        	return MultiIcon;
        }
        else
        {
        	return MainIcon;
        }
    }

    public int getMetadata(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return MainIcon;
    }

    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < RoseColor.getAllColors().size(); i++)
        {
        	par3List.add(new ItemStack(this, 1, i));
        }
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
            return i >= 0 && i < RoseColor.RoseColorNames.length ? super.getUnlocalizedName(par1ItemStack) + "." + RoseColor.RoseColorNames[i] : super.getUnlocalizedName(par1ItemStack);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        MainIcon = par1IconRegister.registerIcon(getIconString()+"3");
        MultiIcon = par1IconRegister.registerIcon(getIconString()+"4");
    }
}
