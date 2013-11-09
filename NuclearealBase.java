package net.minecraft.src.nucleareal;

import net.minecraft.src.BaseMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;

public abstract class NuclearealBase extends BaseMod
{
	@Override
	public String getVersion()
	{
		return new StringBuilder().append(Version()).append("f").append(MCVersion()).toString();
	}

	@Override
	public void load()
	{
	}

	protected String MCVersion()
	{
		return "1.6.2";
	}

	protected abstract String Version();
}
