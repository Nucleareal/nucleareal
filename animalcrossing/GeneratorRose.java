package net.minecraft.src.nucleareal.animalcrossing;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.src.nucleareal.Direction;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.UtilDirection;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class GeneratorRose implements IWorldGenerator
{
	private List<Integer> canGenerateBiomeList;

	{
		canGenerateBiomeList = new ArrayList<Integer>();
		canGenerateBiomeList.add(BiomeGenBase.forest.biomeID);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(random.nextInt(12) != 0) return;

		BiomeGenBase base = world.getBiomeGenForCoordsBody(chunkX << 4, chunkZ << 4);

		if(canGenerateBiomeList.contains(Integer.valueOf(base.biomeID)))
		{
			generateFlowers(world, chunkX << 4, world.getActualHeight()-1, chunkZ << 4, random);
		}
	}

	private void generateFlowers(World world, int x, int y, int z, Random random)
	{
		int genCount = 2 + random.nextInt(5);

		for(int i = 0; i < genCount; i++)
		{
			int rx = x + random.nextInt(16);
			int rz = z + random.nextInt(16);

			for(int ry = y; ry >= 32; ry--)
			{
				if(canTryToPlaceBlock(world, AnimalCrossing.Rose, rx, ry, rz, random))
				{
					Position now = new Position(rx, ry, rz);
					EnumSet<Direction> set = UtilDirection.getHorizonalAroundMultiDirections();
					for(Direction dir : set)
					{
						if(random.nextInt(2) == 0)
						{
							int[] arr = now.moveWith(dir).getConvertToIntArray();
							canTryToPlaceBlock(world, AnimalCrossing.Rose, arr[0], arr[1], arr[2], random);
						}
					}
					break;
				}
			}
		}
	}

	private boolean canTryToPlaceBlock(World world, Block block, int x, int y, int z, Random rand)
	{
		RoseColor[] arr = RoseColor.getCanGenerateTypes().toArray(new RoseColor[0]);

		return	block.canPlaceBlockAt(world, x, y, z) &&
				world.setBlock(x, y, z, block.blockID, arr[rand.nextInt(arr.length)].ordinal(), 0x7);
	}

}
