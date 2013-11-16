package net.minecraft.src.nucleareal.animalcrossing.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.untispacingchest.AnimalCrossingChest;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFurniture extends TileEntity implements IInventory
{
	private int metadata;
	private int rotation;
	private boolean isLighting;
	private int livingcount;

	public TileEntityFurniture()
	{
		this(0);
	}

	public TileEntityFurniture(int metadata)
	{
		this.metadata = metadata;
	}

	public int getFurnitureIndex()
	{
		return metadata;
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		System.out.println("TileEntity Packet.");
    }

	public void readFromNBT(NBTTagCompound nbt)
    {
		super.readFromNBT(nbt);
		metadata = nbt.getInteger("NC:Metadata");
		rotation = nbt.getInteger("NC:Rotation");
		isLighting = nbt.getBoolean("NC:Lighting");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);
    	nbt.setInteger("NC:Metadata", metadata);
    	nbt.setInteger("NC:Rotation", rotation);
    	nbt.setBoolean("NC:Lighting", isLighting);
    }

    public void updateEntity()
    {
    	super.updateEntity();
    	livingcount++;
    }

	public float getBlockRotation(int i)
	{
		return (float)(90D*rotation);
	}

	public void onRender(int i)
	{
	}

	public void setTileRotation(int l)
	{
		rotation = l;
	}

	public int getRotation()
	{
		return rotation;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		return FurniturePacketHandler.getPacket(this);
	}

	public void setTileMetadata(int meta)
	{
		metadata = meta;
	}

	public boolean getLighting()
	{
		return isLighting;
	}

	public void setLighting(boolean isLighting)
	{
		this.isLighting = isLighting;
	}

	public void changeLighting()
	{
		isLighting = !isLighting;
	}

	public int getLivingCount()
	{
		return livingcount;
	}

	/* IInventory Methods */

	public int getSizeInventory()
	{
		return AnimalCrossingChest.get().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return AnimalCrossingChest.get().getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		return AnimalCrossingChest.get().decrStackSize(i, j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return AnimalCrossingChest.get().getStackInSlotOnClosing(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack ist)
	{
		AnimalCrossingChest.get().setInventorySlotContents(i, ist);
	}

	@Override
	public String getInvName()
	{
		return AnimalCrossingChest.get().getInvName();
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return AnimalCrossingChest.get().isInvNameLocalized();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return AnimalCrossingChest.get().getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return AnimalCrossingChest.get().isUseableByPlayer(entityplayer);
	}

	@Override
	public void openChest()
	{
		AnimalCrossingChest.get().openChestAt(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public void closeChest()
	{
		AnimalCrossingChest.get().closeChestAt(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return AnimalCrossingChest.get().isItemValidForSlot(i, itemstack);
	}
}
