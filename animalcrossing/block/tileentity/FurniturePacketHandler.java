package net.minecraft.src.nucleareal.animalcrossing.block.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class FurniturePacketHandler implements IPacketHandler
{
	public static Packet getPacket(TileEntityFurniture tile)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		int x = tile.xCoord;
		int y = tile.yCoord;
		int z = tile.zCoord;
		int meta = tile.getMetadata();
		int rotation = tile.getRotation();
		boolean isLighting = tile.getLighting();

		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeInt(meta);
			dos.writeInt(rotation);
			dos.writeBoolean(isLighting);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "ACFurniture";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("ACFurniture"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			int x, y, z;
			int meta, rotation;
			boolean isLighting;
			try
			{
				x = data.readInt();
				y = data.readInt();
				z = data.readInt();
				meta = data.readInt();
				rotation = data.readInt();
				isLighting = data.readBoolean();

				World world = AnimalCrossing.proxy.getClientWorld();
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

				if (tileEntity instanceof TileEntityFurniture)
				{
					TileEntityFurniture tile = (TileEntityFurniture)tileEntity;
					tile.setTileMetadata(meta);
					tile.setTileRotation(rotation);
					tile.setLighting(isLighting);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
