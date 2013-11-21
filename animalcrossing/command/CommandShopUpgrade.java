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
import net.minecraft.src.nucleareal.animalcrossing.ShopLevelController;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.util.ChatMessageComponent;

public class CommandShopUpgrade extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "acshopupgrade";
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
			if(ShopLevelController.get().isNoMoreUpgrade())
			{
				sender.sendChatToPlayer(new ChatMessageComponent().addText("This shop has been upgraded to Max Level, aborting."));
				return;
			}

			ShopLevelController.get().removeNextWall();
			sender.sendChatToPlayer(new ChatMessageComponent().addText("This shop has been upgraded to level"+ShopLevelController.get().getLevel()));
		}
		else
		{
			sender.sendChatToPlayer(new ChatMessageComponent().addText("A shop has not found"));
		}
	}
}
