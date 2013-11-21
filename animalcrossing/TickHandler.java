package net.minecraft.src.nucleareal.animalcrossing;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemInWorldManager;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.AchievementRegisterBase;
import net.minecraft.src.nucleareal.Direction;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.UtilDirection;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.UtilPosition;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.src.nucleareal.UtilWorld.EntityHandler;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockFurniture;
import net.minecraft.src.nucleareal.animalcrossing.command.LoginCommandRegisterer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;
import net.minecraftforge.common.FakePlayerFactory;
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

	private double prevPosX;
	private double prevPosY;
	private double prevPosZ;
	private double rotx;
	private double roty;

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
		tickKillMobs();
		tickMove();

		//Minecraft.getMinecraft().fontRenderer.drawString("Money:"+MoneyManager.get().getMoney(), 0, 0, 0xFFFFFF);
	}

	private void tickMove()
	{
		double dx = Player.posX - prevPosX;
		double dy = Player.posY - prevPosY;
		double dz = Player.posZ - prevPosZ;
		double dd = Math.abs(Player.rotationPitch - rotx) + Math.abs(Player.rotationYaw - roty);
		prevPosX = Player.posX;
		prevPosY = Player.posY;
		prevPosZ = Player.posZ;
		rotx = Player.rotationPitch;
		roty = Player.rotationYaw;

		if(Math.sqrt(dx*dx+dy*dy+dz*dz + dd*dd) > .001D)
		{
			movingCount = 0;
			isMoving = true;
		}
		else
		{
			movingCount++;
			if(movingCount > 18)
			{
				isMoving = false;
			}
		}
	}

	public static int movingCount;
	public static boolean isMoving;

	private int killcount = 0;

	private void tickKillMobs()
	{
		if(GeneratorInukichiShop.getIsGenerated() && ++killcount % 30 == 0)
		{
			ObjectPair<World, EntityPlayer> pair = UtilMinecraft.getWorldAndPlayer("");
			World world = pair.getV1();

			double xPos = GeneratorInukichiShop.generatedX + 7;
			double zPos = GeneratorInukichiShop.generatedZ + 7;
			double yPos = GeneratorInukichiShop.generatedY + 3;

			final EntityPlayer p = new FakePlayerFactory().getMinecraft(world);
			p.setPosition(xPos, yPos, zPos);

			UtilWorld.foreachEntities(world, p, 7D, new EntityHandler()
			{
				@Override
				public boolean onEntity(Entity e)
				{
					if(e instanceof EntityTameable)
					{
						EntityTameable et = (EntityTameable)e;
						et.heal(Float.MAX_VALUE);
						et.isDead = false;
					}
					else
					if(e instanceof EntityPlayer)
					{
					}
					else
					if(e instanceof EntityWitch)
					{
						e.setDead();
					} else
					if(e instanceof EntityLiving)
					{
						e.attackEntityFrom(DamageSource.generic, 1F);
					}
					return false;
				}
			});
		}
	}

	private void tickInSpawnChests()
	{
		if(Player.worldObj.rand.nextInt(AnimalCrossing.BalloonSpawnChance) == 0 && isDistanced() && iplayerCanSeeTheSky())
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

	private boolean iplayerCanSeeTheSky()
	{
		return Player.worldObj.canBlockSeeTheSky((int)Math.floor(Player.posX), (int)Math.floor(Player.posY), (int)Math.floor(Player.posZ));
	}

	private long beforeSpawnTime;

	private boolean isDistanced()
	{
		return System.currentTimeMillis() - beforeSpawnTime > (1000 * 60) && !Player.worldObj.isRaining();
	}

	private void tickInRunningSquid()
	{
		if(Player == null) return;

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
		if(Player == null) return;

		ItemStack ist = Player.getCurrentEquippedItem();

		if(ist == null || ist.getItem() == null) return;

		if(isValidTool(ist) && isStartBreakingBlock() && isValidWood(Block.blocksList[id]))
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

	private boolean isValidTool(ItemStack ist)
	{
		return AnimalCrossing.isAxeTool(ist.itemID, ist.getItemDamage(), ist.getItem().getClass().getSimpleName());
	}

	private boolean isValidWood(Block block)
	{
		if(block == null) return false;
		return AnimalCrossing.isWood(id, meta, block.getClass().getSimpleName());
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if(GeneratorInukichiShop.getIsGenerated())
		{
			if(isOpening)
			{
				checkInukichiClose();
			}
			else
			{
				checkInukichiOpen();
			}
		}
	}

	private boolean isOpening = false;

	private void checkInukichiOpen()
	{
		long t = Player.worldObj.getWorldInfo().getWorldTime() % 24000L;
		if(t < 12000)
		{
			isOpening = true;
			openShop();
		}
	}

	private void checkInukichiClose()
	{
		long t = Player.worldObj.getWorldInfo().getWorldTime() % 24000L;
		if(t >= 12000)
		{
			isOpening = false;
			closeShop();
		}
	}

	private void openShop()
	{
		World world = UtilMinecraft.getWorldAndPlayer("").getV1();
		ChangeDoorState(world, GeneratorInukichiShop.generatedX+6, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ, true);
		ChangeDoorState(world, GeneratorInukichiShop.generatedX+7, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ, true);

		placeFurnitures(world);
	}

	private void closeShop()
	{
		World world = UtilMinecraft.getWorldAndPlayer("").getV1();
		ChangeDoorState(world, GeneratorInukichiShop.generatedX+6, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ, false);
		ChangeDoorState(world, GeneratorInukichiShop.generatedX+7, GeneratorInukichiShop.generatedY, GeneratorInukichiShop.generatedZ, false);

		removeFurnitures(world);
	}

	private List<Position> lv0Furnitures;
	private List<Position> lv1Furnitures;
	private List<Position> lv2Furnitures;
	private List<Position> lv3Furnitures;
	{
		lv0Furnitures = new LinkedList<Position>();
		lv1Furnitures = new LinkedList<Position>();
		lv2Furnitures = new LinkedList<Position>();
		lv3Furnitures = new LinkedList<Position>();

		lv0Furnitures.add(new Position(8, 0, 2));

		lv1Furnitures.add(new Position(8, 0, 5));
		lv1Furnitures.add(new Position(5, 0, 2));
		lv1Furnitures.add(new Position(5, 0, 5));

		lv2Furnitures.add(new Position(5, 0, 8));
		lv2Furnitures.add(new Position(8, 0, 8));
		lv2Furnitures.add(new Position(11, 0, 5));
		lv2Furnitures.add(new Position(11, 0, 2));
		lv2Furnitures.add(new Position(11, 0, 5));
		lv2Furnitures.add(new Position(11, 0, 8));

		lv2Furnitures.add(new Position(2, 0, 2));
		lv2Furnitures.add(new Position(2, 0, 5));
		lv2Furnitures.add(new Position(2, 0, 8));
	}

	private void placeFurnitures(World world)
	{
		if(ShopLevelController.get().getLevel() >= 0) doPlaceFurnitures(world, lv0Furnitures);
		if(ShopLevelController.get().getLevel() >= 1) doPlaceFurnitures(world, lv1Furnitures);
		if(ShopLevelController.get().getLevel() >= 2) doPlaceFurnitures(world, lv2Furnitures);
	}

	private void removeFurnitures(World world)
	{
		if(ShopLevelController.get().getLevel() >= 0) doRemoveFurnitures(world, lv0Furnitures);
		if(ShopLevelController.get().getLevel() >= 1) doRemoveFurnitures(world, lv1Furnitures);
		if(ShopLevelController.get().getLevel() >= 2) doRemoveFurnitures(world, lv2Furnitures);
	}

	private void doPlaceFurnitures(World world, List<Position> list)
	{
		for(Position p : list)
		{
			int fx = GeneratorInukichiShop.generatedX + (int)p.getX();
			int fy = GeneratorInukichiShop.generatedY + (int)p.getY();
			int fz = GeneratorInukichiShop.generatedZ + (int)p.getZ();
			if(world.isAirBlock(fx, fy, fz))
			{
				world.setBlock(fx, fy, fz, AnimalCrossing.Furniture.blockID, 15, 7);
				AnimalCrossing.getTileFurniture(world, fx, fy, fz).setFurnitureToShop();
				AnimalCrossing.getTileFurniture(world, fx, fy, fz).setRotation(2);
			}
		}
	}

	private void doRemoveFurnitures(World world, List<Position> list)
	{
		for(Position p : list)
		{
			int fx = GeneratorInukichiShop.generatedX + (int)p.getX();
			int fy = GeneratorInukichiShop.generatedY + (int)p.getY();
			int fz = GeneratorInukichiShop.generatedZ + (int)p.getZ();
			world.setBlockMetadataWithNotify(fx, fy, fz, 0, 7);
			world.setBlockToAir(fx, fy, fz);
		}
	}

	private FakePlayer createFakePlayer(World world)
	{
		return new FakePlayerFactory().getMinecraft(world);
	}

	// m & b 1011
	// m | b 0100
	private void ChangeDoorState(World world, int x, int y, int z, boolean isOpenMode)
	{
		if(world.getBlockId(x, y, z) != AnimalCrossing.InukichiDoor.blockID) { isOpening = !isOpening; return; }

		int meta = world.getBlockMetadata(x, y, z);
		int smeta = meta & 7;

		if(isOpenMode)
		{
			smeta |= 4;
		}
		else
		{
			smeta &= 11;
		}

        if ((meta & 8) == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, smeta, 2);
            world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
        }
        else
        {
            world.setBlockMetadataWithNotify(x, y-1, z, smeta, 2);
            world.markBlockRangeForRenderUpdate(x, y-1, z, x, y, z);
        }
        world.playAuxSFXAtEntity((EntityPlayer)null, 1003, x, y, z, 0);
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

		MoneyManager.get().load(player);
		ShopLevelController.get().load(player);
		GeneratorInukichiShop.onWorldChanged(UtilMinecraft.getWorldAndPlayer(player.username).getV2());

		isOpening = false;
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		MoneyManager.get().save(player);
		ShopLevelController.get().save(player);

		AnimalCrossing.NBT.save();
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
