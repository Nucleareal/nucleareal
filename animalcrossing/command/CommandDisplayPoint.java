package net.minecraft.src.nucleareal.animalcrossing.command;

import java.util.Random;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.GeneratorInukichiShop;
import net.minecraft.src.nucleareal.animalcrossing.MoneyManager;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.util.ChatMessageComponent;

public class CommandDisplayPoint extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "acshoppoint";
	}

	@Override
	public String getCommandUsage(ICommandSender sr)
	{
		return "unknown";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if(GeneratorInukichiShop.getIsGenerated())
		{
			sender.sendChatToPlayer(new ChatMessageComponent().addText(
				String.format("The Shop is %d %d %d", GeneratorInukichiShop.generatedX, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ)));
		}
		else
		{
			sender.sendChatToPlayer(new ChatMessageComponent().addText("a Shop not found"));
		}
	}
}
