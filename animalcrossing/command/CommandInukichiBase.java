package net.minecraft.src.nucleareal.animalcrossing.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.UtilNumber;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.src.nucleareal.UtilWorld.EntityHandler;
import net.minecraft.src.nucleareal.animalcrossing.UtilFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityInukichi;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public abstract class CommandInukichiBase extends CommandBase implements EntityHandler
{
	private boolean hasInukichiFound;

	@Override
	public void processCommand(ICommandSender sender, String[] params)
	{
		hasInukichiFound = false;

		if(sender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)sender;
			UtilWorld.foreachEntities(player.worldObj, player, 8D, this);

			if(hasInukichiFound && doProcessCommand(sender, player, params))
			{
				sender.sendChatToPlayer(new ChatMessageComponent().addText("Success."));
			}
		}
	}

	protected abstract boolean doProcessCommand(ICommandSender sender, EntityPlayer player, String[] params);

	@Override
	public boolean onEntity(Entity e)
	{
		if(e instanceof EntityInukichi)
		{
			return hasInukichiFound = true;
		}
		return false;
	}
}
