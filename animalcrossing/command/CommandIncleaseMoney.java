package net.minecraft.src.nucleareal.animalcrossing.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.src.nucleareal.animalcrossing.MoneyManager;

public class CommandIncleaseMoney extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "incleasemoney";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "unknown";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] args)
	{
		int amount = 1000;

		if(args.length != 0)
		{
			try
			{
				amount = Integer.valueOf(args[0]);
			}
			catch(Exception e)
			{
			}
		}

		MoneyManager.get().addMoney(amount);
	}
}
