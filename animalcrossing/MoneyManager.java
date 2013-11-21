package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.world.World;

public class MoneyManager
{
	private MoneyManager()
	{
	}
	private static final MoneyManager instance = new MoneyManager();
	public static MoneyManager get()
	{
		return instance;
	}

	//

	private int money;
	private EntityPlayer player;
	private World world;

	private void preLoad(EntityPlayer player)
	{
		world = player.worldObj;
		this.player = player;
	}

	public void load(EntityPlayer player)
	{
		preLoad(player);

		money = AnimalCrossing.NBT.getWorldOriginalIntegerValue(world, "AC:Money");
	}

	public void save(EntityPlayer player)
	{
		preLoad(player);

		AnimalCrossing.NBT.setWorldOriginalIntegerValue(world, "AC:Money", money);
	}

	public int getMoney()
	{
		return money;
	}

	public void addMoney(int amount)
	{
		money += amount;

		ShopLevelController.get().addUseMoneyAmount(amount);
	}

	public boolean useMoney(int amount)
	{
		if(money - amount < 0) return false;

		money -= amount;

		ShopLevelController.get().addUseMoneyAmount(amount);

		return true;
	}
}
