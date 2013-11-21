package net.minecraft.src.nucleareal;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class NBTTool
{
	private String modname;

	public NBTTool(String modname)
	{
		this.modname = modname;
		Tag = new NBTTagCompound();
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

	public NBTTagCompound getTagCompound()
	{
		return Tag;
	}

	public int getIntValueSafely(String name)
	{
		return getIntValueSafely(name, 0);
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
		return incleaseValue(name, 1);
	}

	public int incleaseValue(String name, int size)
	{
		int nowValue = getIntValueSafely(name);
		nowValue += size;
		Tag.setInteger(name, nowValue);
		return nowValue;
	}

	public void setIntValue(String key, int val)
	{
		Tag.setInteger(key, val);
	}

	public void setWorldOriginalValue(World world, String okey, String value)
	{
		Tag.setString(createOriginalKey(world, okey), value);
	}

	public String getWorldOriginalValue(World world, String okey)
	{
		String key = createOriginalKey(world, okey);
		if(Tag.hasKey(key))
		{
			return Tag.getString(key);
		}
		return null;
	}

	private String createOriginalKey(World world, String key)
	{
		return String.valueOf(world.getSeed())+":"+world.getWorldInfo().getWorldName()+":"+key;
	}

	public void setWorldOriginalIntegerValue(World world, String okey, int value)
	{
		Tag.setInteger(createOriginalKey(world, okey), value);
	}

	public int getWorldOriginalIntegerValue(World world, String okey)
	{
		String key = createOriginalKey(world, okey);

		return getIntValueSafely(key);
	}
}
