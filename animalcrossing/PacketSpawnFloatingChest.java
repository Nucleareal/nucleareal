package net.minecraft.src.nucleareal.animalcrossing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.world.World;

public class PacketSpawnFloatingChest extends Packet
{
	private String PlayerName;
	private World world;
	private EntityPlayer player;

	public int X = 0;
	public int Y = 0;
	public int Z = 0;

	public PacketSpawnFloatingChest(String player)
	{
		PlayerName = player;
	}

	@Override
	public void readPacketData(DataInput in) throws IOException
	{
		X = in.readInt();
		Y = in.readInt();
		Z = in.readInt();
		PlayerName = in.readUTF();
	}

	@Override
	public void writePacketData(DataOutput out) throws IOException
	{
		out.writeInt(X);
		out.writeInt(Y);
		out.writeInt(Z);
		out.writeUTF(PlayerName);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		ObjectPair<World, EntityPlayer> pair = UtilMinecraft.getWorldAndPlayer(PlayerName);
		world = pair.getV1();
		player = pair.getV2();

		UtilFloatingBalloon.spawnToDirectionalEntity(world, player);
	}

	@Override
	public boolean isRealPacket()
	{
		return false;
	}

	@Override
	public int getPacketSize()
	{
		return 1024;
	}
}
