package net.minecraft.src.nucleareal.animalcrossing.command;

import java.util.Random;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.GeneratorInukichiShop;
import net.minecraft.src.nucleareal.animalcrossing.MoneyManager;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.util.ChatMessageComponent;

public class CommandWarpToShop extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "acwarptoshop";
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
			//sender.sendChatToPlayer(new ChatMessageComponent().addText("/tp ").addText(String.format("%d %d %d",
			//		GeneratorInukichiShop.generatedX, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ)));

			if(sender instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer)sender;

				String f = String.format("/tp %d %d %d", GeneratorInukichiShop.generatedX+7, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ-2);

				MinecraftServer.getServer().getCommandManager().executeCommand(p, f);
			}
		}
		else
		{
			sender.sendChatToPlayer(new ChatMessageComponent().addText("A shop has not found"));
		}
	}
}
