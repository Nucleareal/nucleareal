package net.minecraft.src.nucleareal;

import java.util.EnumSet;
import java.util.Queue;

import net.minecraft.entity.player.EntityPlayer;

public class UtilPosition
{
	public static void putAroundNextPositions(Position now, Queue<Position> queue)
	{
		EnumSet<Direction> allEffectiveDirections = UtilDirection.getHorizonalAndUpperAroundDirections();

		for(Direction dir : allEffectiveDirections)
		{
			queue.offer(now.moveWith(dir));
		}
		return;
	}

	public static Position fromEntity(EntityPlayer player)
	{
		return new Position(player.posX, player.posY - 2F, player.posZ);
	}
}
