package net.minecraft.src.nucleareal.animalcrossing;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemInWorldManager;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.AchievementRegisterBase;
import net.minecraft.src.nucleareal.Direction;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.UtilDirection;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.UtilPosition;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.src.nucleareal.animalcrossing.command.LoginCommandRegisterer;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements IScheduledTickHandler, IPlayerTracker
{
	private EntityPlayer Player;
	private EnumSet<TickType> tick;
	private ItemInWorldManager manager;

	private boolean isNowBreaking;
	private boolean isPrevBreaking;
	private Position pos;
	private ItemStack drop;

	private int id;
	private int meta;

	private Class<ItemInWorldManager> ManagerClass;
	private Field Breaking;
	private Field xg;
	private Field yg;
	private Field zg;
	private int x;
	private int y;
	private int z;
	private HashMap<String, Boolean> SpawnMap;
	private Queue<Position> WideSearchQueue;
	private int WideSearchCount;

	public TickHandler()
	{
		tick = EnumSet.of(TickType.PLAYER);
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		tickInTree();
		tickInRunningSquid();
		tickInSpawnChests();
	}

	private void tickInSpawnChests()
	{
		if(Player.worldObj.rand.nextInt(AnimalCrossing.BalloonSpawnChance) == 0 && isDistanced() && Player.worldObj.canBlockSeeTheSky((int)Player.posX, (int)Player.posY, (int)Player.posZ))
				{
					Minecraft minecraft = ModLoader.getMinecraftInstance();

					PacketSpawnFloatingChest packet = new PacketSpawnFloatingChest(Player.username);

					double[] ts = UtilFloatingBalloon.getMoveAddtions(Player.worldObj);
					packet.X = (int)(Player.posX + ts[0] * 50);
					packet.Y = (int)Player.posY + 8;
					packet.Z = (int)(Player.posZ + ts[1] * 50);

					//UtilFloatingBalloon.spawnAt(Player.worldObj, packet.X, packet.Y, packet.Z);
					beforeSpawnTime = System.currentTimeMillis();

		            minecraft.getNetHandler().addToSendQueue(packet);
				}
	}

	private long beforeSpawnTime;

	private boolean isDistanced()
	{
		return System.currentTimeMillis() - beforeSpawnTime > (1000 * 60) &&
				!Player.worldObj.isRaining();
	}

	private void tickInRunningSquid()
	{
		if(Player.isSprinting())
		{
			ObjectPair<World, EntityPlayer> pair = UtilMinecraft.getWorldAndPlayer(Player.username);
			World world = pair.getV1();
			EntityPlayer player = pair.getV2();

			EnumSet<Direction> set = UtilDirection.getHorizonalAroundDirections();
			for(Direction dir : set)
			{
				Position pos = UtilPosition.fromEntity(player);

				pos = pos.moveWith(dir).moveWith(dir);
				if(UtilWorld.getBlockMaterial(world, pos).isLiquid())
				{
					UtilWorld.foreachEntities(world, player, 8D,
						new UtilWorld.EntityHandler()
						{
							@Override
							public boolean onEntity(Entity e)
							{
								if(e instanceof EntitySquid)
								{
									EntitySquid ika = (EntitySquid)e;
									ika.setDead();
								}
								return false;
							}
						}
					);
				}
			}
		}
	}

	private void tickInTree()
	{
		if(Player.worldObj.isRemote) return;

		ItemStack ist = Player.getCurrentEquippedItem();

		if((null != ist) && (ist.getItem() instanceof ItemAxe || AnimalCrossing.isAxeTool(ist)))
		{
			if(isStartBreakingBlock() && (Block.blocksList[id] instanceof BlockLog || AnimalCrossing.isWood(id, meta)))
			{
				Minecraft minecraft = ModLoader.getMinecraftInstance();

				PacketTreePart packet = new PacketTreePart(Player.username);
				packet.X = x;
				packet.Y = y;
				packet.Z = z;
				packet.blockID = id;
				packet.metadata = meta;

	            minecraft.getNetHandler().addToSendQueue(packet);
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return tick;
	}

	@Override
	public String getLabel()
	{
		return "";
	}

	@Override
	public int nextTickSpacing()
	{
		return 0;
	}

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		Player = player;
		beforeSpawnTime = 0L;

		new LoginCommandRegisterer().registerAll();
		doUnlockAchievements();

		refreshManager();
	}

	private void doUnlockAchievements()
	{
		while(!unlockAchievements.isEmpty())
		{
			Achievement ach = unlockAchievements.poll();
			AchievementRegisterBase.triggerAchievement(Player, ach);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		AnimalCrossing.NBT.save();
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
	}

	private boolean isStartBreakingBlock()
	{
		boolean result = false;
		try
		{
			isNowBreaking = Breaking.getBoolean(manager);

			if(isNowBreaking && !isPrevBreaking)
			{
				x = xg.getInt(manager);
				y = yg.getInt(manager);
				z = zg.getInt(manager);
				id = Player.worldObj.getBlockId(x, y, z);
				meta = Player.worldObj.getBlockMetadata(x, y, z);
				result = true;
			}
			if(!isNowBreaking && isPrevBreaking)
			{
				x = xg.getInt(manager);
				y = yg.getInt(manager);
				z = zg.getInt(manager);
				if(UtilMinecraft.getWorldAndPlayer(Player.username).getV1().isAirBlock(x, y, z))
				{
					id = Block.wood.blockID;
					meta = -1;
					result = true;
				}
			}
			isPrevBreaking = isNowBreaking;
		}
		catch(Exception e)
		{
		}
		return result;
	}

	private void refreshManager()
	{
		if(Player instanceof EntityPlayerMP)
		{
			manager = ((EntityPlayerMP)Player).theItemInWorldManager;
			try
			{
				ManagerClass = ItemInWorldManager.class;
				Breaking = ManagerClass.getDeclaredField(F_ckinMojang.DestroyingBlock.getFieldName());
				xg = ManagerClass.getDeclaredField(F_ckinMojang.DestroyingBlockX.getFieldName());
				yg = ManagerClass.getDeclaredField(F_ckinMojang.DestroyingBlockY.getFieldName());
				zg = ManagerClass.getDeclaredField(F_ckinMojang.DestroyingBlockZ.getFieldName());
				Breaking.setAccessible(true);
				xg.setAccessible(true);
				yg.setAccessible(true);
				zg.setAccessible(true);
			}
			catch(NoSuchFieldException e)
			{
			}
		}
	}

	private static Queue<Achievement> unlockAchievements;

	static
	{
		unlockAchievements = new LinkedList<Achievement>();
	}

	public static void addAchievementForUnlock(Achievement achi)
	{
		unlockAchievements.offer(achi);
	}
}
