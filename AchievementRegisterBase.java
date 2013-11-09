package net.minecraft.src.nucleareal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class AchievementRegisterBase
{
	private String modname;
	private List<Achievement> list;
	private int count = 0;

	public AchievementRegisterBase(String modname)
	{
		this.modname = modname;
		count = 0;
		list = new ArrayList<Achievement>();
	}

	public void doRegister()
	{
		register();
		AchievementPage page = new AchievementPage(modname, list.toArray(new Achievement[0]));
		AchievementPage.registerAchievementPage(page);
	}

	protected abstract int getBeginAchievementValue();

	protected abstract void register();

	protected Achievement addAchievement(String name, String desc, int x, int y, ItemStack ist, Achievement parent, boolean isSpecial)
	{
		int id = getBeginAchievementValue() + count;
		String defName = "Achievement:"+modname +":"+ id;
		Achievement achi = new Achievement(id, defName, x, y, ist, parent);
		if(isSpecial) achi.setSpecial();
		achi = achi.registerAchievement();
		LanguageRegistry.instance().addStringLocalization("achievement."+defName, "en_US", name);
		LanguageRegistry.instance().addStringLocalization("achievement."+defName+".desc", "en_US", desc);
		count++;
		list.add(achi);
		return achi;
	}

	protected Achievement addAchievement(String name, String desc, int x, int y, Item item, Achievement parent, boolean isSpecial)
	{
		return addAchievement(name, desc, x, y, new ItemStack(item), parent, isSpecial);
	}

	protected Achievement addAchievement(String name, String desc, int x, int y, Block block, Achievement parent, boolean isSpecial)
	{
		return addAchievement(name, desc, x, y, new ItemStack(block), parent, isSpecial);
	}

	protected Achievement addAchievement(String name, String desc, int x, int y, ItemStack ist, Achievement parent)
	{
		return addAchievement(name, desc, x, y, ist, parent, false);
	}

	protected Achievement addAchievement(String name, String desc, int x, int y, Item item, Achievement parent)
	{
		return addAchievement(name, desc, x, y, item, parent, false);
	}

	protected Achievement addAchievement(String name, String desc, int x, int y, Block block, Achievement parent)
	{
		return addAchievement(name, desc, x, y, block, parent, false);
	}
}
