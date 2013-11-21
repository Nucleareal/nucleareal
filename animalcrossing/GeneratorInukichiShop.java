package net.minecraft.src.nucleareal.animalcrossing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityInukichi;
import net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiShopStructure;
import net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiValue;
import net.minecraft.src.nucleareal.animalcrossing.maplesisters.EntityMapleAkaha;
import net.minecraft.src.nucleareal.animalcrossing.maplesisters.EntityMapleKiyo;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class GeneratorInukichiShop implements IWorldGenerator
{
	private List<Integer> canGenerateBiomeList;

	{
		canGenerateBiomeList = new ArrayList<Integer>();
		canGenerateBiomeList.add(BiomeGenBase.plains.biomeID);
		canGenerateBiomeList.add(BiomeGenBase.desert.biomeID);
		canGenerateBiomeList.add(BiomeGenBase.icePlains.biomeID);
		canGenerateBiomeList.add(BiomeGenBase.forest.biomeID);
		canGenerateBiomeList.add(BiomeGenBase.river.biomeID);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(isGenerated || random.nextInt(512) != 0) return;

		BiomeGenBase base = world.getBiomeGenForCoordsBody(chunkX << 4, chunkZ << 4);

		if(canGenerateBiomeList.contains(Integer.valueOf(base.biomeID)))
		{
			generateShop(world, chunkX << 4, world.getActualHeight()-1, chunkZ << 4, random);
		}
	}

	private void generateShop(World world, int x, int y, int z, Random random)
	{
		int i;

		for(i = 0;
				world.isAirBlock(x, y-i, z) && world.isAirBlock(x+13, y-i, z) && world.isAirBlock(x, y-i, z+13) && world.isAirBlock(x+13, y-i, z+13);
				i++);

		i--;
		y -= i;

		isGenerated = true;
		generatedX = x;
		generatedX = y;
		generatedX = z;
		addWorldOriginalPosition(world, x, y, z);

		InukichiShopStructure.generate(world, x, y, z);

		EntityInukichi entity = new EntityInukichi(world);
		entity.setPosition(x+7, y, z+4);
		world.spawnEntityInWorld(entity);

		EntityMapleAkaha entityAkaha = new EntityMapleAkaha(world);
		entityAkaha.setPosition(x+6, y+4, z+4);
		world.spawnEntityInWorld(entityAkaha);
		EntityMapleKiyo entityKiyo = new EntityMapleKiyo(world);
		entityKiyo.setPosition(x+8, y+4, z+4);
		world.spawnEntityInWorld(entityKiyo);
	}

	private static final String ss = "ShopSpace";

	private void addWorldOriginalPosition(World world, int x, int y, int z)
	{
		AnimalCrossing.NBT.setWorldOriginalValue(world, ss, getPositionFormat(x, y, z));
	}

	private String getPositionFormat(int x, int y, int z)
	{
		return String.format("%d,%d,%d", x, y, z);
	}

	public static void onWorldChanged(EntityPlayer player)
	{
		isGenerated = false;

		String value = AnimalCrossing.NBT.getWorldOriginalValue(player.worldObj, ss);
		if(value != null)
		{
			isGenerated = true;
			String[] values = value.split(",");

			try
			{
				generatedX = Integer.valueOf(values[0]);
				generatedY = Integer.valueOf(values[1]);
				generatedZ = Integer.valueOf(values[2]);
			}
			catch(Exception e)
			{
				isGenerated = false;
			}
		}
	}

	public static void removeWall(int level)
	{
		World world = UtilMinecraft.getWorldAndPlayer("").getV1();
		switch(level)
		{
			default: break;
			case 1: InukichiShopStructure.removeLevel(world, generatedX, generatedY, generatedZ, InukichiValue.L1Wall); break;
			case 2: InukichiShopStructure.removeLevel(world, generatedX, generatedY, generatedZ, InukichiValue.L2Wall); break;
			case 3: InukichiShopStructure.removeLevel(world, generatedX, generatedY, generatedZ, InukichiValue.L3Wall); break;
		}
	}

	public static synchronized boolean getIsGenerated()
	{
		return isGenerated;
	}

	private static volatile boolean isGenerated;
	public static int generatedX;
	public static int generatedY;
	public static int generatedZ;
}
