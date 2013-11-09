package net.minecraft.src.nucleareal;

import java.util.EnumSet;

public class UtilDirection
{

	public static EnumSet<Direction> getAllEffectiveDirections()
	{
		return EnumSet.of(Direction.XNeg, Direction.XPos, Direction.ZNeg, Direction.ZPos, Direction.YNeg, Direction.YPos);
	}

	public static EnumSet<Direction> getHorizonalAroundDirections()
	{
		return EnumSet.of(Direction.XNeg, Direction.XPos, Direction.ZNeg, Direction.ZPos);
	}

	public static EnumSet<Direction> getHorizonalAndUpperAroundDirections()
	{
		return EnumSet.of(Direction.XNeg, Direction.XPos, Direction.ZNeg, Direction.ZPos, Direction.YPos);
	}

	public static EnumSet<Direction> getHorizonalAroundMultiDirections()
	{
		return EnumSet.of(Direction.XNegZNeg, Direction.ZNeg, Direction.XPosZNeg, Direction.XPos, Direction.XPosZPos, Direction.ZPos, Direction.XNegZPos, Direction.XNeg);
	}

}
