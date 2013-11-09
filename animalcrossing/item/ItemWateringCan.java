package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.Achievements;
import net.minecraft.src.nucleareal.animalcrossing.WateringCanMaterial;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockColorableFlower;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWateringCan extends Item
{
	private WateringCanMaterial CanMaterial;
	private Icon WateringCan;
	private Icon Part;

	public ItemWateringCan(int i, WateringCanMaterial material)
	{
		super(i);
		CanMaterial = material;
		setMaxDamage(CanMaterial.getMaxUse() + 1);
		setMaxStackSize(1);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	@Override
	public void onUpdate(ItemStack ist, World world, Entity entity, int par4, boolean isCurrent)
	{
		if(isCurrent && world.isRaining())
		{
			NBTTagCompound nbt = ist.getTagCompound();
			if(null == nbt) { ist.setTagCompound(nbt = new NBTTagCompound()); }
			if(nbt.hasKey("RainyCount"))
			{
				int count = nbt.getInteger("RainyCount");
				if(count > 96)
				{
					nbt.removeTag("RainyCount");
					incrimentWater(world, ist, entity, par4);
				}
				else
				{
					nbt.setInteger("RainyCount", count+1);
				}
			}
			else
			{
				nbt.setInteger("RainyCount", 1);
			}
		}
	}

	@Override
	public void addInformation(ItemStack ist, EntityPlayer player, List list, boolean par4)
	{
		if(ist.getItemDamage() == ist.getMaxDamage())
		{
			list.add("Empty");
		}
		else
		if(ist.getItemDamage() != 0)
		{
			list.add("Left: "+(ist.getMaxDamage() - ist.getItemDamage()));
		}
		super.addInformation(ist, player, list, par4);
	}

	private void incrimentWater(World world, ItemStack ist, Entity entity, int par4)
	{
		if(ist.getItemDamage() > 1)
		{
			ist.damageItem(-1, (EntityLivingBase)entity);
		}
	}

	public boolean onItemUse(ItemStack ist, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
	{
		int damage = ist.getItemDamage();
		if(damage == 0 || damage == ist.getMaxDamage())
		{
			return false;
		}

		if(world.getBlockId(x, y, z) == AnimalCrossing.RoseWitherID)
		{
			int regencount = AnimalCrossing.NBT.incleaseValue("RegenRose");
			int regencountM = AnimalCrossing.NBT.incleaseValue("RegenRose:"+world.getBlockMetadata(x, y, z));
			player.triggerAchievement(Achievements.regenRose);
			if(regencount > 100)
			{
				player.triggerAchievement(Achievements.regenManyRose);
			}

			BlockColorableFlower flower = (BlockColorableFlower)(Block.blocksList[world.getBlockId(x, y, z)]);
			flower.respawn(world, x, y, z, AnimalCrossing.Rose.blockID);

			ist.damageItem(1, player);
			return true;
		}
		return false;
	}

	public Icon getIconFromDamage(int damage)
    {
		return damage == 0 ? Part : WateringCan;
    }

	//I_WateringGoldPart

	public WateringCanMaterial getMaterial()
	{
		return CanMaterial;
	}

	public String getUnlocalizedName(ItemStack ist)
    {
		 int i = ist.getItemDamage();
         return i == 0 ? super.getUnlocalizedName(ist) + ".part" : super.getUnlocalizedName(ist) + ".can";
    }

	public void getSubItems(int itemID, CreativeTabs tabs, List list)
    {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, CanMaterial.getMaxUse() + 1));
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister registerer)
    {
        super.registerIcons(registerer);
        WateringCan = registerer.registerIcon(getIconString());
        Part = registerer.registerIcon(getIconString()+"Part");
    }

	public int getMetadata(int i)
    {
        return i;
    }

	public ItemStack onItemRightClick(ItemStack ist, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, ist.getItemDamage() > 1);

        if (movingobjectposition == null)
        {
            return ist;
        }
        else
        {
            if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, i, j, k))
                {
                    return ist;
                }

                if (ist.getItemDamage() > 1)
                {
                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, ist))
                    {
                        return ist;
                    }

                    if (Material.water.equals(world.getBlockMaterial(i, j, k)) && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockToAir(i, j, k);

                        ItemStack newItem = new ItemStack(ist.getItem(), 1, 1);

                        return newItem;
                    }
                }
            }
            return ist;
        }
    }
}
