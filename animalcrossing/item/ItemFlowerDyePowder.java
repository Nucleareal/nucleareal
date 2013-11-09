package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemFlowerDyePowder extends ItemDye
{
	private Icon DyeIcon;

	public ItemFlowerDyePowder(int i)
	{
		super(i);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage)
	{
		return DyeIcon;
	}

	public boolean onItemUse(ItemStack ist, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
	{
		Material material = world.getBlockMaterial(x, y, z);
		if(material.equals(Material.cloth) || material.equals(Material.materialCarpet))
		{
			int meta = RoseColor.convertRoseToCloth(ist.getItemDamage());
			if(meta < 0) return false;

			world.setBlock(x, y, z, world.getBlockId(x, y, z), meta, 0x7);
			--ist.stackSize;
			return true;
		}
		return false;
	}

	public static boolean func_96604_a(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4)
    {
		return false;
    }

	@SideOnly(Side.CLIENT)
    public static void func_96603_a(World par0World, int par1, int par2, int par3, int par4)
    {
    }

	public boolean itemInteractionForEntity(ItemStack ist, EntityPlayer player, EntityLivingBase toEntity)
    {
        if (toEntity instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)toEntity;
            int i = RoseColor.convertRoseToCloth(ist.getItemDamage());
            if(i < 0) return false;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != i)
            {
                entitysheep.setFleeceColor(i);
                --ist.stackSize;
            }
            return true;
        }
        return false;
    }

	public void getSubItems(int itemID, CreativeTabs tab, List list)
    {
        for (int j = 0; j < RoseColor.getAllColors().size(); ++j)
        {
            list.add(new ItemStack(itemID, 1, j));
        }
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister registerer)
    {
		DyeIcon = registerer.registerIcon(getIconString());
    }

	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack ist, int layer)
    {
		return RoseColor.of(ist.getItemDamage()).getColor();
    }

	public int getMetadata(int i)
    {
        return i;
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
}
