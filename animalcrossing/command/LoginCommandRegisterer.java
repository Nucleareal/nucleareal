package net.minecraft.src.nucleareal.animalcrossing.command;

import java.util.Map;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class LoginCommandRegisterer
{
	private Map cmap;

	public LoginCommandRegisterer()
	{
	}

	public void registerAll()
	{
		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ICommandManager manager = server.getCommandManager();
		cmap = manager.getCommands();

		register(new CommandSpawnFloatingBalloon());
	}

	private void register(CommandBase command)
	{
		cmap.put(command.getCommandName(), command);
	}
}
