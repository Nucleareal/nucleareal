package net.minecraft.src.nucleareal.animalcrossing.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.UtilNumber;
import net.minecraft.src.nucleareal.animalcrossing.UtilFloatingBalloon;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public class CommandSpawnFloatingBalloon extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "SpawnFloatingBalloon";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "Unknown";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params)
	{
		if(sender instanceof Entity)
		{
			Entity senderEntity = (Entity)sender;

			if(params.length == 1 && params[0].equals("ToPlayer"))
			{
				UtilFloatingBalloon.spawnToDirectionalEntity(senderEntity.worldObj, senderEntity);
				return;
			}

			switch(params.length)
			{
				case 0 : UtilFloatingBalloon.spawnAt(senderEntity.worldObj, (int)senderEntity.posX, (int)senderEntity.posY + 8, (int)senderEntity.posZ); break;
				case 1 :
				{
					if(params[0].equals("ToPlayer"))
						UtilFloatingBalloon.spawnToDirectionalEntity(senderEntity.worldObj, senderEntity);
					else
						UtilFloatingBalloon.spawnAt(senderEntity.worldObj, (int)senderEntity.posX, (int)senderEntity.posY+UtilNumber.getParsedInt(params[0]), (int)senderEntity.posZ);
					break;
				}
				case 2 : UtilFloatingBalloon.spawnAt(senderEntity.worldObj, UtilNumber.getParsedInt(params[0]), (int)senderEntity.posY + 8, UtilNumber.getParsedInt(params[2])); break;
				case 3 : UtilFloatingBalloon.spawnAt(senderEntity.worldObj, UtilNumber.getParsedInt(params[0]), UtilNumber.getParsedInt(params[1]), UtilNumber.getParsedInt(params[2])); break;
			}

			sender.sendChatToPlayer(new ChatMessageComponent().addText("Success."));
		}
	}

}
