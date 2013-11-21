package net.minecraft.src.nucleareal.animalcrossing.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemInukichiDoor extends ItemBlock
{
	private Block inukichi;

	public ItemInukichiDoor(int i, Block block)
	{
		super(i);
		inukichi = block;
	}

	@Override
	public boolean onItemUse(ItemStack ist, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if (side != 1)
        {
            return false;
        }
        else
        {
            ++y;
            Block block = inukichi;

            if (player.canPlayerEdit(x, y, z, side, ist) && player.canPlayerEdit(x, y + 1, z, side, ist))
            {
                if (!block.canPlaceBlockAt(world, x, y, z))
                {
                    return false;
                }
                else
                {
                    int dir = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    placeDoorBlock(world, x, y, z, dir, block);
                    --ist.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static void placeDoorBlock(World world, int x, int y, int z, int side, Block block)
    {
        byte b0 = 0;
        byte b1 = 0;

        switch(side)
        {
        	case 0: b1 =  1; break;
        	case 1: b0 = -1; break;
        	case 2: b1 = -1; break;
        	case 3: b0 =  1; break;
        }

        int i1 = (world.isBlockNormalCube(x - b0, y, z - b1) ? 1 : 0) + (world.isBlockNormalCube(x - b0, y + 1, z - b1) ? 1 : 0);
        int j1 = (world.isBlockNormalCube(x + b0, y, z + b1) ? 1 : 0) + (world.isBlockNormalCube(x + b0, y + 1, z + b1) ? 1 : 0);
        boolean flag = world.getBlockId(x - b0, y, z - b1) == block.blockID || world.getBlockId(x - b0, y + 1, z - b1) == block.blockID;
        boolean flag1 = world.getBlockId(x + b0, y, z + b1) == block.blockID || world.getBlockId(x + b0, y + 1, z + b1) == block.blockID;
        boolean flag2 = false;

        if (flag && !flag1)
        {
            flag2 = true;
        }
        else if (j1 > i1)
        {
            flag2 = true;
        }

        world.setBlock(x, y, z, block.blockID, side, 2);
        world.setBlock(x, y + 1, z, block.blockID, 8 | (flag2 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block.blockID);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block.blockID);
    }
}
