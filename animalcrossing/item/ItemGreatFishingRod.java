package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.UtilInventory;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.Bait;
import net.minecraft.src.nucleareal.animalcrossing.FishingRodMaterial;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFishingFloat;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGreatFishingRod extends ItemFishingRod
{
	private Icon CastIcon;
	private Icon UncastIcon;
	private FishingRodMaterial material;

	public ItemGreatFishingRod(int i, FishingRodMaterial material)
	{
		super(i);
		this.setMaxStackSize(1);
		this.setCreativeTab(AnimalCrossing.AnimalCrossingTab);
		this.material = material;
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	public ItemStack onItemRightClick(ItemStack ist, World world, EntityPlayer player)
	{
		if(ist.getItemDamage() == ist.getMaxDamage()) return ist;

		int meta = -1;
		if(AnimalCrossing.FishingRodUsesBait && isUsing && !player.capabilities.isCreativeMode)
		{
			for(int i = 0; i < Bait.size(); i++)
			{
				if(UtilInventory.useItemStack(player, new ItemStack(AnimalCrossing.FishingBait, 1, i)))
				{
					meta = i;
					break;
				}
			}
			if(meta == -1) return ist;
		}

		if (player.fishEntity != null)
		{
			int i = player.fishEntity.catchFish();
			player.swingItem();
			isUsing = false;
		}
		else
		{
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityFishingFloat(world, player, meta));
			}
			player.swingItem();
			isUsing = true;
		}
		return ist;
	}

	private static boolean isUsing = false;

	public FishingRodMaterial getMaterial()
	{
		return material;
	}

	public boolean isItemTool(ItemStack par1ItemStack)
	{
		return true;
	}

	public int getItemEnchantability()
	{
		return getMaterial().getEnchantability();
	}

	@Override
	public void addInformation(ItemStack ist, EntityPlayer player, List list, boolean par4)
	{
		/*if (ist.getItemDamage() == ist.getMaxDamage())
		{
			list.add("Empty");
		}
		else if (ist.getItemDamage() != 0)
		{
			list.add("Left: " + (ist.getMaxDamage() - ist.getItemDamage()));
		}*/
		super.addInformation(ist, player, list, par4);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		UncastIcon	= par1IconRegister.registerIcon(this.getIconString() + "_Uncast");
		CastIcon	= par1IconRegister.registerIcon(this.getIconString() + "_Cast");
	}

	@SideOnly(Side.CLIENT)
	public Icon func_94597_g()
	{
		return CastIcon;
	}

	@SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return UncastIcon;
    }

	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
		if(player.fishEntity != null || (usingItem != null && usingItem.getItem() instanceof ItemFishingRod) || isUsing)
		{
			return func_94597_g();
		}
		return super.getIcon(stack, renderPass, player, usingItem, useRemaining);
    }
}
