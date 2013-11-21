package net.minecraft.src.nucleareal.animalcrossing.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.MoneyManager;
import net.minecraft.src.nucleareal.animalcrossing.inukichi.InukichiBuySellController;
import net.minecraft.util.ChatMessageComponent;

public class CommandBuy extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "acshopbuy";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "unknown";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if(InukichiBuySellController.get().isBuyMode())
		{
			if(MoneyManager.get().useMoney(InukichiBuySellController.get().getBuyAmount()))
			{
				ItemStack ist = InukichiBuySellController.get().createItemStack();

				if(sender instanceof EntityPlayer)
				{
					EntityPlayer p = (EntityPlayer)sender;

					EntityItem ei = new EntityItem(p.worldObj);
					ei.setPosition(p.posX, p.posY, p.posZ);
					ei.setEntityItemStack(ist);
					p.worldObj.spawnEntityInWorld(ei);
				}

				sender.sendChatToPlayer(new ChatMessageComponent().addText("Completed."));

				InukichiBuySellController.get().reciveCompletedBuyCommand();
			}
			else
			{
				sender.sendChatToPlayer(new ChatMessageComponent().addText("You don't have enough money."));
			}
		}
	}

	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
