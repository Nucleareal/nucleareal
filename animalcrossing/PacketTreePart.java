package net.minecraft.src.nucleareal.animalcrossing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.UtilBlock;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.UtilNumber;
import net.minecraft.src.nucleareal.UtilPosition;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFallingTreePart;
import net.minecraft.world.World;

public class PacketTreePart extends Packet
{
	private String PlayerName;

	private HashMap<String, Boolean> SpawnMap;
	private HashMap<String, String> DataMap;

	private Queue<Position> WideSearchQueue;
	private int WideSearchCount;
	private World world;
	private EntityPlayer player;

	public int X = 0;
	public int Y = 0;
	public int Z = 0;
	public int blockID = 0;
	public int metadata = 0;

	public PacketTreePart(String player)
	{
		PlayerName = player;
	}

	@Override
	public void readPacketData(DataInput in) throws IOException
	{
		X = in.readInt();
		Y = in.readInt();
		Z = in.readInt();
		blockID = in.readInt();
		metadata = in.readInt();
		PlayerName = in.readUTF();
	}

	@Override
	public void writePacketData(DataOutput out) throws IOException
	{
		out.writeInt(X);
		out.writeInt(Y);
		out.writeInt(Z);
		out.writeInt(blockID);
		out.writeInt(metadata);
		out.writeUTF(PlayerName);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		ObjectPair<World, EntityPlayer> pair = UtilMinecraft.getWorldAndPlayer(PlayerName);
		world = pair.getV1();
		player = pair.getV2();
		if (metadata == -1)
		{
			AnimalCrossing.reset(world, X, Y, Z);
			return;
		}

		world.playAuxSFX(1022, X, Y, Z, 0);

		if (AnimalCrossing.counting(world, X, Y, Z))
		{
			SearchAndSpawnBlocks();
		}
	}

	private void SearchAndSpawnBlocks()
	{
		SpawnMap = new HashMap<String, Boolean>();
		DataMap = new HashMap<String, String>();
		WideSearchQueue = new LinkedList<Position>();
		WideSearchCount = 0;

		WideSearchQueue.offer(new Position(X, Y, Z));

		while (WideSearchCount++ < AnimalCrossing.WideSearchMax && !WideSearchQueue.isEmpty())
		{
			SearchNext(world, WideSearchQueue.poll());
		}

		Set<String> Keys = SpawnMap.keySet();

		int SpawnedCount = 0;

		for (String key : Keys)
		{
			if (!SpawnMap.get(key)) continue;

			Position pos = Position.from(key);

			int wx = (int) pos.getX();
			int wy = (int) pos.getY();
			int wz = (int) pos.getZ();

			world.setBlockToAir(wx, wy, wz);

			String[] vals = DataMap.get(key).split(":");

			int eID = UtilNumber.getParsedInt(vals[0]);
			int eMeta = UtilNumber.getParsedInt(vals[1]);

			EntityFallingTreePart entity = new EntityFallingTreePart(world,
					pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D, eID, eMeta, new Position(X, Y, Z),
					Block.blocksList[eID].getBlockDropped(world, wx, wy, wz, eMeta, EnchantmentHelper.getFortuneModifier(player)));
			entity.setPosition(wx+.5D, wy+.5D, wz+.5D);
			world.spawnEntityInWorld(entity);
			ModLoader.getMinecraftInstance().theWorld.spawnEntityInWorld(entity);

			SpawnedCount++;
		}

		player.getCurrentEquippedItem().damageItem(SpawnedCount, player);

		player.triggerAchievement(Achievements.cutATree);
	}

	@Override
	public boolean isRealPacket()
	{
		return false;
	}

	private void SearchNext(World world, Position now)
	{
		if (SpawnMap.containsKey(now.getVisualityValue())) return;

		if (UtilBlock.isEqualBlocks(world, now, new Position(X, Y, Z)) ||
				AnimalCrossing.isLeaf(UtilWorld.getBlockID(world, now), UtilWorld.getBlockMetadata(world, now)))
		{
			SpawnMap.put(now.getVisualityValue(), Boolean.valueOf(true));
			DataMap.put(now.getVisualityValue(), String.format("%d:%d", UtilWorld.getBlockID(world, now), UtilWorld.getBlockMetadata(world, now)));

			UtilPosition.putAroundNextPositions(now, WideSearchQueue);
		}
		else
		{
			System.out.println(String.format("search %s: %d %d", now.getVisualityValue(), UtilWorld.getBlockID(world, now), UtilWorld.getBlockMetadata(world, now)));

			SpawnMap.put(now.getVisualityValue(), Boolean.valueOf(false));
		}
	}

	@Override
	public int getPacketSize()
	{
		return 1024;
	}
}
