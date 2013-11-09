package net.minecraft.src.nucleareal;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;

public class NBTTool
{
	private String modname;

	public NBTTool(String modname)
	{
		this.modname = modname;
		init();
	}

	private NBTTagCompound Tag;
	private File statsFile;

	public void init()
	{
		try
		{
			Minecraft mc = ModLoader.getMinecraftInstance();
			File mcFile = mc.mcDataDir;

			statsFile = new File(mcFile, "stats");
			statsFile = new File(statsFile, modname+".dat");

			if(!statsFile.exists())
			{
				save();
			}

			Tag = CompressedStreamTools.read(statsFile);
		}
		catch(Exception e)
		{
			Tag = new NBTTagCompound();
		}
	}

	public void save()
	{
		try
		{
			CompressedStreamTools.write(Tag, statsFile);
		}
		catch(Exception e)
		{
		}
	}

	public int getIntValueSafely(String name)
	{
		return getIntValueSafely(name, 0);
	}

	public NBTTagCompound getTagCompound()
	{
		return Tag;
	}

	public int getIntValueSafely(String name, int defValue)
	{
		int result = 0;
		if(!Tag.hasKey(name))
		{
			result = defValue;
			Tag.setInteger(name, defValue);
		}
		return Tag.getInteger(name);
	}

	public int incleaseValue(String name)
	{
		int nowValue = getIntValueSafely(name);
		Tag.setInteger(name, ++nowValue);
		return nowValue;
	}
}
