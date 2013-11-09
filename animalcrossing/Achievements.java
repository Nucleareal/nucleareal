package net.minecraft.src.nucleareal.animalcrossing;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.AchievementRegisterBase;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;

public class Achievements extends AchievementRegisterBase
{
	public Achievements(String modname)
	{
		super(modname);
	}

	public static Achievement pickUpRose;
	public static Achievement pickUpBaseRoses;
	public static Achievement pickUpRareRose;
	public static Achievement pickUpBlueRose;
	public static Achievement pickUpGoldRose;
	public static Achievement pickUpManyRose;
	public static Achievement breakOutRose;
	public static Achievement breakOutManyRose;
	public static Achievement createWateringCan;
	public static Achievement regenRose;

	public static Achievement createPachinko;
	public static Achievement shootedBalloon;
	public static Achievement regenManyRose;

	public static Achievement cutATree;

	@Override
	protected void register()
	{
		pickUpRose 			= addAchievement("Flower Picker", "Pickup a rose.", 2, 2, AnimalCrossing.Rose, null);
		pickUpBaseRoses		= addAchievement("Gardening Beginner", "Pickup roses: Red, White, and Yellow.", 4, 2, new ItemStack(AnimalCrossing.Rose, 1, RoseColor.White.ordinal()), pickUpRose);
		pickUpRareRose		= addAchievement("Great Gardener!", "Pickup a rare rose: one of Pink, Orange, Black And Purple", 6, 2, new ItemStack(AnimalCrossing.Rose, 1, RoseColor.Purple.ordinal()), pickUpBaseRoses);
		pickUpBlueRose		= addAchievement("Miracle Gardener!", "Pickup a BLUE ROSE!", 8, 2, new ItemStack(AnimalCrossing.Rose, 1, RoseColor.Blue.ordinal()), pickUpRareRose, true);
		pickUpGoldRose		= addAchievement("True Brightness!", "Pickup a GOLD ROSE.", 6, 4, new ItemStack(AnimalCrossing.Rose, 1, RoseColor.Gold.ordinal()), pickUpRareRose, true);
		pickUpManyRose		= addAchievement("Many Flowers!", "Pick up over THOUSAND ROSES.", 4, 4, AnimalCrossing.Rose, pickUpBaseRoses);
		breakOutRose		= addAchievement("Trampled Flower", "Rose blew away by YOUR SPRINTING!", 2, 4, new ItemStack(AnimalCrossing.Rose,1,RoseColor.Black.ordinal()), pickUpRose);
		breakOutManyRose	= addAchievement("Many Flower Blew away", "MANY MANY ROSE BLEW AWAY BY YOUR SPRINTING!!!", 2, 6, new ItemStack(AnimalCrossing.Rose,1,RoseColor.Black.ordinal()), breakOutRose);
		createWateringCan	= addAchievement("Time to Regen Rose!", "Create a WateringCan.", 3, 1, new ItemStack(AnimalCrossing.WateringCanGold,1,1), pickUpBaseRoses);
		regenRose			= addAchievement("Flower Encourager!", "Use a WateringCan to Withered Rose.", 4, 0, new ItemStack(AnimalCrossing.WateringCanGold,1,1), createWateringCan);
		regenManyRose		= addAchievement("Flower Master!", "Roses regenerated many times by your help!", 6, 0, new ItemStack(AnimalCrossing.WateringCanGold,1,1), regenRose);

		createPachinko		= addAchievement("Time to shooting", "Create a Pachinko.", 2, -2, new ItemStack(AnimalCrossing.PachinkoGold,1,1), null);
		shootedBalloon		= addAchievement("Shooter Beginner", "Break a Balloon.", 2, -4, Block.chest, createPachinko);

		cutATree			= addAchievement("Cut a tree", "Cut a tree by Axe", 0, 0, Item.axeStone, null);
	}

	@Override
	protected int getBeginAchievementValue()
	{
		return 40000;
	}
}
