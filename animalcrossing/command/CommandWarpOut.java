package net.minecraft.src.nucleareal.animalcrossing.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.GeneratorInukichiShop;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public class CommandWarpOut extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "acwarpout";
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
			if(sender instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer)sender;

				double px = p.posX;
				double py = p.posY;
				double pz = p.posZ;

				double d = p.getDistance(GeneratorInukichiShop.generatedX+7, GeneratorInukichiShop.generatedY+3, GeneratorInukichiShop.generatedZ+7);

				if(d <= Math.sqrt(7*7+7*7+3*3))
				{
					ObjectPair<World, EntityPlayer> pair = UtilMinecraft.getWorldAndPlayer(p.username);
					pair.getV1().removeEntity(pair.getV2());
					pair.getV2().setPosition(GeneratorInukichiShop.generatedX+7, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ-2);
					pair.getV1().spawnEntityInWorld(pair.getV2());
				}
				else
				{
					sender.sendChatToPlayer(new ChatMessageComponent().addText("This point isn't shop area."));
				}
			}
		}
		else
		{
			sender.sendChatToPlayer(new ChatMessageComponent().addText("A shop has not found."));
		}
	}

	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
