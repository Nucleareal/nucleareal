package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.StatsComponent;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.PachinkoMaterial;
import net.minecraft.src.nucleareal.animalcrossing.WateringCanMaterial;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockColorableFlower;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityPachinkoBullet;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemPachinko extends ItemBow
{
	private PachinkoMaterial pMaterial;
	@SideOnly(Side.CLIENT)
    private Icon[] Icons;
	@SideOnly(Side.CLIENT)
	private Icon bulletIcon;

	public ItemPachinko(int par1, PachinkoMaterial material)
	{
		super(par1);
        pMaterial = material;
		setMaxDamage(pMaterial.getMaxUse() + 1);
		setMaxStackSize(1);
		setNoRepair();
		setHasSubtypes(false);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	public PachinkoMaterial getMaterial()
	{
		return pMaterial;
	}

	public String getUnlocalizedName(ItemStack ist)
    {
		 int i = ist.getItemDamage();
         return i == 0 ? super.getUnlocalizedName(ist) + ".part" : super.getUnlocalizedName(ist) + ".real";
    }

	public Icon getIconFromDamage(int damage)
    {
		return damage == 0 ? bulletIcon : itemIcon;
    }

	public int getItemEnchantability()
    {
        return getMaterial().getEnchantability();
    }

	@Override
	public void addInformation(ItemStack ist, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(ist, player, list, par4);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

	public void getSubItems(int itemID, CreativeTabs tabs, List list)
    {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, pMaterial.getMaxUse() + 1));
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister registerer)
    {
		bulletIcon = registerer.registerIcon(getIconString()+ "_Bullet");
		itemIcon = registerer.registerIcon(this.getIconString() + "_Normal");
        Icons = new Icon[3];
        for (int i = 0; i < Icons.length; ++i)
        {
        	Icons[i] = registerer.registerIcon(this.getIconString() + "_" +i);
        }
    }

	public Icon getItemIconForUseDuration(int par1)
    {
        return Icons[par1];
    }

	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack using, int useRemaining)
    {
		if (stack.getItem() instanceof ItemBow && using != null)
        {
            int j = stack.getMaxItemUseDuration() - useRemaining;

            if (j > 12)
            {
                return getItemIconForUseDuration(2);
            }
            if (j > 6)
            {
                return getItemIconForUseDuration(1);
            }
            if (j > 0)
            {
                return getItemIconForUseDuration(0);
            }
        }
		return getIcon(stack, renderPass);
    }



	/*public int getMetadata(int i)
    {
        return i;
    }*/

	/*public ItemStack onItemRightClick(ItemStack ist, World world, EntityPlayer player)
    {
    }*/

	public void onPlayerStoppedUsing(ItemStack ist, World world, EntityPlayer player, int par4)
    {
		int damage = ist.getItemDamage();
        if(damage == ist.getMaxDamage() || damage == 0) return;

        int j = this.getMaxItemUseDuration(ist) - par4;

        j += 6;

        boolean isInfinityArrows = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, ist) > 0;

        if (true)
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if ((double)f < 0.1D)
            {
                return;
            }
            if (f > 1.0F)
            {
                f = 1.0F;
            }

            for(int i = 0; i < 3; i++)
            {
	            EntityPachinkoBullet bullet = new EntityPachinkoBullet(world, player);

	            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, ist) > 0)
	            {
	            	bullet.setFire(100);
	            }

	            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	            if (!world.isRemote)
	            {
	                world.spawnEntityInWorld(bullet);
	            }
            }
            if(AnimalCrossing.PachinkoUsesBullet)
            {
            	ist.damageItem(1, player);
            }
        }
    }

	public ItemStack onItemRightClick(ItemStack ist, World world, EntityPlayer player)
    {
		int damage = ist.getItemDamage();
        if(damage == ist.getMaxDamage() || damage == 0) return ist;

        if (true)
        {
            player.setItemInUse(ist, getMaxItemUseDuration(ist));
        }

        return ist;
    }
}
