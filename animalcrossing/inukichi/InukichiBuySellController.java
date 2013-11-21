package net.minecraft.src.nucleareal.animalcrossing.inukichi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.world.World;

public class InukichiBuySellController
{
	private InukichiBuySellController()
	{
	}
	private static final InukichiBuySellController instance = new InukichiBuySellController();
	public static InukichiBuySellController get()
	{
		return instance;
	}



	private boolean isBuy;
	private EntityPlayer buyer;
	private int amount;
	private int damage;
	private World world;
	private int xC;
	private int yC;
	private int zC;

	public void triggerBuy(EntityPlayer player, int value, int buyof, World world, int x, int y, int z)
	{
		isBuy = true;
		buyer = player;
		amount = value;
		damage = buyof;
		xC = x;
		yC = y;
		zC = z;
		this.world = world;
	}

	public void reciveCompletedBuyCommand()
	{
		world.setBlockMetadataWithNotify(xC, yC, zC, 0, 7);
		world.setBlockToAir(xC, yC, zC);

		isBuy = false;
		buyer = null;
		amount = 0;
		damage = 0;
		world = null;
		xC = 0;
		yC = 0;
		zC = 0;
	}

	public boolean isBuyMode()
	{
		return isBuy;
	}

	public int getBuyAmount()
	{
		return amount;
	}

	public ItemStack createItemStack()
	{
		return new ItemStack(AnimalCrossing.Furniture, 1, damage);
	}
}
