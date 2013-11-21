package net.minecraft.src.nucleareal.animalcrossing.inukichi;

import static net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue.GlowStone;
import static net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue.L1Wall;
import static net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue.L2Wall;
import static net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue.L3Wall;
import static net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue.Planks;
import static net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue.Wall;
import static net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue.WoodVertical;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;
import net.minecraftforge.common.FakePlayerFactory;

public class InukichiShopStructure
{
	private static List<AreaData> putValues;

	private static List<AreaData> Lv1;
	private static List<AreaData> Lv2;
	private static List<AreaData> Lv3;

	static
	{
		putValues = new LinkedList<AreaData>();
		Lv1 = new LinkedList<AreaData>();
		Lv2 = new LinkedList<AreaData>();
		Lv3 = new LinkedList<AreaData>();

		//Side Walls
		putValues.add(new AreaData( 0, 0,  0, 14, 8,  1, Wall, false));
		putValues.add(new AreaData( 0, 0,  1,  1, 8, 13, Wall, false));
		putValues.add(new AreaData( 1, 0, 13, 13, 8,  1, Wall, false));
		putValues.add(new AreaData(13, 0,  1,  1, 8, 12, Wall, false));

		//Top Wall
		//TODO

		//Lv1Wall
		Lv1.add(new AreaData( 4, 0,  1,  1, 4, 5, L1Wall, false));
		Lv1.add(new AreaData( 9, 0,  1,  1, 4, 5, L1Wall, false));
		Lv1.add(new AreaData( 5, 0,  5,  4, 4, 1, L1Wall, false));

		//Lv2Wall
		Lv2.add(new AreaData( 2, 0,  1,  1, 4, 9, L2Wall, false));
		Lv2.add(new AreaData(11, 0,  1,  1, 4, 9, L2Wall, false));
		Lv2.add(new AreaData( 3, 0,  9,  8, 4, 1, L2Wall, false));

		//Lv3Wall
		Lv3.add(new AreaData( 3, 3, 12, 10, 1, 1, L3Wall, false));

		for(AreaData data : Lv1) putValues.add(data);
		for(AreaData data : Lv2) putValues.add(data);
		for(AreaData data : Lv3) putValues.add(data);

		//floor
		putValues.add(new AreaData( 1, -1, 1, 12, 1,12, Planks, false));

		//gatefloor
		putValues.add(new AreaData( 6, -1,-2,  2, 1, 3, Wall, false));

		//GlowStones
		putValues.add(new AreaData(1 , -1,  1,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(1 , -1, 12,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(12, -1,  1,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(12, -1, 12,  1, 1, 1, GlowStone, false));

		putValues.add(new AreaData( 5, -1,  4,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData( 8, -1,  4,  1, 1, 1, GlowStone, false));

		putValues.add(new AreaData( 3, -1,  8,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(10, -1,  8,  1, 1, 1, GlowStone, false));

		putValues.add(new AreaData( 6, -1, 10,  2, 1, 1, GlowStone, false));

		//Stairs
		putValues.add(new AreaData(  5,  0, 12, 1, 1, 1, Wall, true));
		putValues.add(new AreaData(  4,  1, 12, 1, 1, 1, Wall, true));
		putValues.add(new AreaData(  3,  2, 12, 1, 1, 1, Wall, true));
		putValues.add(new AreaData(  2,  3, 12, 1, 1, 1, Wall, true));

		//middlefloor
		putValues.add(new AreaData( 1, 3, 1, 12, 1, 11, Planks, false));

		//2F GlowStones
		putValues.add(new AreaData(1 ,  3,  1,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(1 ,  3, 12,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(12,  3,  1,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(12,  3, 12,  1, 1, 1, GlowStone, false));

		putValues.add(new AreaData( 5,  3,  4,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData( 8,  3,  4,  1, 1, 1, GlowStone, false));

		putValues.add(new AreaData( 3,  3,  8,  1, 1, 1, GlowStone, false));
		putValues.add(new AreaData(10,  3,  8,  1, 1, 1, GlowStone, false));

		putValues.add(new AreaData( 6,  3, 10,  2, 1, 1, GlowStone, false));

		//top
		putValues.add(new AreaData( 1, 7, 1, 12, 1, 12, Planks, false));

		//WoodenGate
		putValues.add(new AreaData( 5, 0,  0,  4, 3,  1, WoodVertical, false));
	}

	public static void generate(World world, int x, int y, int z)
	{
		for(AreaData data : putValues)
		{
			doGenerate(world, x, y, z, data);
		}

		FakePlayerFactory factory = new FakePlayerFactory();
		FakePlayer player = factory.getMinecraft(world);

		world.setBlockToAir(x+6, y, z);
		world.setBlockToAir(x+7, y, z);
		world.setBlockToAir(x+6, y+1, z);
		world.setBlockToAir(x+7, y+1, z);
		ItemStack ist = new ItemStack(AnimalCrossing.InukichiDoor);
		ist.copy().getItem().onItemUse(ist, player, world, x+7, y-1, z, 1, 0F, 0F, 0F);
		ist.copy().getItem().onItemUse(ist, player, world, x+6, y-1, z, 1, 0F, 0F, 0F);

		world.setBlock(x, y, z, AnimalCrossing.Inukichi.blockID, 0, 2);
	}

	private static void doGenerate(World world, int x, int y, int z, AreaData data)
	{
		int blockID = data.isStair ? AnimalCrossing.InukichiStair.blockID : AnimalCrossing.Inukichi.blockID;
		int metadata = data.isStair ? 1 : data.meta;

		for(int i = data.x; i < (data.x + data.sx); i++)
		{
			for(int j = data.y; j < (data.y + data.sy); j++)
			{
				for(int k = data.z; k < (data.z + data.sz); k++)
				{
					world.setBlock(x+i, y+j, z+k, blockID, metadata, 2);
				}
			}
		}
	}

	public static void removeLevel(World world, int x, int y, int z, InukichiValue level)
	{
		switch(level)
		{
			default: return;
			case L1Wall: setToAir(world, x, y, z, Lv1, level);
			case L2Wall: setToAir(world, x, y, z, Lv2, level);
			case L3Wall: setToAir(world, x, y, z, Lv3, level);
		}
	}

	private static void setToAir(World world, int x, int y, int z, List<AreaData> rlv, InukichiValue level)
	{
		for(AreaData data : rlv)
		{
			doSetToAir(world, x, y, z, data, level);
		}
	}

	private static void doSetToAir(World world, int x, int y, int z, AreaData data, InukichiValue level)
	{
		for(int i = data.x; i < (data.x + data.sx); i++)
		{
			for(int j = data.y; j < (data.y + data.sy); j++)
			{
				for(int k = data.z; k < (data.z + data.sz); k++)
				{
					if(world.getBlockId(x+i, y+j, z+k) == AnimalCrossing.Inukichi.blockID && world.getBlockMetadata(x+i, y+j, z+k) == level.getMeta())
					{
						world.setBlockToAir(x+i, y+j, z+k);
					}
				}
			}
		}
	}
}
