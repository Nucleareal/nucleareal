package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiShopStructure;
import net.minecraft.world.World;

public class ShopLevelController
{
	private ShopLevelController()
	{
	}
	private static final ShopLevelController instance = new ShopLevelController();
	public static ShopLevelController get()
	{
		return instance;
	}

	private EntityPlayer player;
	private World world;
	private int level;
	private int useMoneyAmount;

	private void preLoad(EntityPlayer player)
	{
		world = player.worldObj;
		this.player = player;
	}

	public void load(EntityPlayer player)
	{
		preLoad(player);

		level = 0;
		useMoneyAmount = 0;

		level = AnimalCrossing.NBT.getWorldOriginalIntegerValue(world, "ShopLevel");
		useMoneyAmount = AnimalCrossing.NBT.getWorldOriginalIntegerValue(world, "MoneyUsed");
	}

	public void save(EntityPlayer player)
	{
		preLoad(player);

		AnimalCrossing.NBT.setWorldOriginalIntegerValue(world, "ShopLevel", level);
		AnimalCrossing.NBT.setWorldOriginalIntegerValue(world, "MoneyUsed", useMoneyAmount);
	}

	public void addUseMoneyAmount(int value)
	{
		useMoneyAmount += Math.abs(value);

		check();
	}

	private void check()
	{
		switch(level)
		{
			case 0: if(useMoneyAmount >= 160000) removeNextWall(); break;
			case 1: if(useMoneyAmount >= 160000*4) removeNextWall(); break;
			case 2: if(useMoneyAmount >= 160000*16) removeNextWall(); break;
		}
	}

	public void removeNextWall()
	{
		level++;
		GeneratorInukichiShop.removeWall(level);
	}

	public int getLevel()
	{
		return level;
	}

	public boolean isMapleUnlocked()
	{
		return level >= 3;
	}

	public boolean isNoMoreUpgrade()
	{
		return isMapleUnlocked();
	}
}
