package net.minecraft.src.nucleareal.animalcrossing.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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

    @Override
    public boolean onItemUse(ItemStack ist, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
    {
    	int damage = ist.getItemDamage();

    	boolean ret = super.onItemUse(ist, player, world, x, y, z, side, fx, fy, fz);

        switch(side)
        {
        	case 0: y--; break;
        	case 1: y++; break;
        	case 2: z--; break;
        	case 3: z++; break;
        	case 4: x--; break;
        	case 5: x++; break;
        	default: break;
        }

    	if(ret)
    	{
    		world.removeBlockTileEntity(x, y, z);

    		TileEntity tile = ((BlockFurniture)AnimalCrossing.Furniture).createTileEntity(world, damage);
    		world.setBlockTileEntity(x, y, z, tile);

    		int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

    		int rotate = (l + 2) % 4;

    		((TileFurniture)tile).setRotation(rotate);
    		((TileFurniture)tile).setFurnitureIndex(damage);
    		((TileFurniture)tile).setLighting(false);
    	}
    	return ret;
    }
}
