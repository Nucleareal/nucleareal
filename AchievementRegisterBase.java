package net.minecraft.src.nucleareal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.F_ckinMojang;
import net.minecraft.src.nucleareal.animalcrossing.TickHandler;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementMap;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class AchievementRegisterBase
{
	private String modname;
	private List<Achievement> list;
	private int count = 0;
	private Map oneShotStats;

	public AchievementRegisterBase(String modname)
	{
		this.modname = modname;
		count = 0;
		list = new ArrayList<Achievement>();
		tryReflection();
	}

	public void doRegister()
	{
		if(isForceRegister()) FMLLog.warning("%s: FORCEING REGISTER ACHIEVEMENTS", modname);
		register();
		if(list.size() > 0)
		{
			AchievementPage page = new AchievementPage(modname, list.toArray(new Achievement[0]));
			AchievementPage.registerAchievementPage(page);
		}
	}

	protected abstract int getBeginAchievementValue();

	protected abstract void register();

	protected abstract boolean isForceRegister();

	protected abstract void onForceRegister(Achievement achi, boolean isExist);

	protected Achievement addAchievement(String name, String desc, int x, int y, ItemStack ist, Achievement parent, boolean isSpecial, boolean isSecret)
	{
		if(getBeginAchievementValue() == 0) return null;
		int iid = getBeginAchievementValue() + count;
		String defName = "Achievement:"+modname +":"+ iid;
		Achievement achi = new Achievement(iid, defName, x, y, ist, parent);
		if(isSpecial) achi.setSpecial();

		int achivementID = achi.statId;

		if(isForceRegister())
		{
			AchievementCheckResult result = getNothingOrExistRemoveID(achivementID);

			if(result != AchievementCheckResult.ReflectionFailed && result != AchievementCheckResult.False)
			{
				onForceRegister(achi, result == AchievementCheckResult.Exist);
			}
			else
			{
				return null;
			}
		}

		if(!isSecret)
		{
			achi = achi.registerAchievement();
		}
		else
		{
			StatList.allStats.add(achi);
			oneShotStats.put(Integer.valueOf(achivementID), achi);
			achi.statGuid = AchievementMap.getGuid(achi.statId);
		}
		LanguageRegistry.instance().addStringLocalization("achievement."+defName, "en_US", name);
		LanguageRegistry.instance().addStringLocalization("achievement."+defName+".desc", "en_US", desc);
		count++;
		list.add(achi);
		return achi;
	}

	private AchievementCheckResult getNothingOrExistRemoveID(int id)
	{
		//Reflection Failed.
		if(oneShotStats == null) return AchievementCheckResult.ReflectionFailed;

		if(oneShotStats.containsKey(Integer.valueOf(id)))
		{
			if("Unknown stat".equals(((StatBase)oneShotStats.get(Integer.valueOf(id))).getName()))
			{
				//Exists And Removed Key!
				oneShotStats.remove(Integer.valueOf(id));
				return AchievementCheckResult.Exist;
			}
			else
			{
				//Exists, but Not Equal to Unknown.
				return AchievementCheckResult.False;
			}
		}
		else
		{
			//Not Contains Key!
			return AchievementCheckResult.Nothing;
		}
	}

	private void tryReflection()
	{
		try
		{
			Class<StatList> clazz = StatList.class;
			Field field = clazz.getDeclaredField(F_ckinMojang.OneShotStats.getFieldName());
			field.setAccessible(true);
			oneShotStats = (Map)field.get(null);
		}
		catch(Exception e)
		{
			FMLLog.warning("ACHIEVEMENT REFLECTION FAILED", "");
		}
	}

	public boolean isRegisterCompleted()
	{
		return list.size() > 0;
	}

	public static void triggerAchievement(EntityPlayer p, Achievement a)
	{
		if(null == p)
		{
			p = UtilMinecraft.getWorldAndPlayer("").getV2();
		}
		if(null != a)
		{
			p.triggerAchievement(a);
		}
	}

	public static void triggerAchievement(Achievement a)
	{
		triggerAchievement(null, a);
	}

	protected Achievement addAchievement(String name, String desc, int x, int y, ItemStack ist, Achievement parent)
	{ return addAchievement(name, desc, x, y,                  ist, parent, false, false); }
	protected Achievement addAchievement(String name, String desc, int x, int y, Item item, Achievement parent)
	{ return addAchievement(name, desc, x, y,  new ItemStack(item), parent, false, false); }
	protected Achievement addAchievement(String name, String desc, int x, int y, Block block, Achievement parent)
	{ return addAchievement(name, desc, x, y, new ItemStack(block), parent, false, false); }

	protected Achievement addAchievement(String name, String desc, int x, int y, ItemStack ist, Achievement parent, boolean isSpecial)
	{ return addAchievement(name, desc, x, y,                  ist, parent, isSpecial, false); }
	protected Achievement addAchievement(String name, String desc, int x, int y, Item item, Achievement parent, boolean isSpecial)
	{ return addAchievement(name, desc, x, y,  new ItemStack(item), parent, isSpecial, false); }
	protected Achievement addAchievement(String name, String desc, int x, int y, Block block, Achievement parent, boolean isSpecial)
	{ return addAchievement(name, desc, x, y, new ItemStack(block), parent, isSpecial, false); }

	protected Achievement addSecretAchievement(String name, String desc, int x, int y, ItemStack ist, Achievement parent)
	{ return addAchievement(name, desc, x, y, ist, parent, false, true); }
	protected Achievement addSecretAchievement(String name, String desc, int x, int y, ItemStack ist, Achievement parent, boolean isSpecial)
	{ return addAchievement(name, desc, x, y, ist, parent, isSpecial, true); }
}
