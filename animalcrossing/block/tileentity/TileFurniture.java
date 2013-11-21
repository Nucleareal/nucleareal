package net.minecraft.src.nucleareal.animalcrossing.block.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.src.nucleareal.animalcrossing.untispacingchest.AnimalCrossingChest;
import net.minecraft.tileentity.TileEntity;

public class TileFurniture extends TileEntity implements IInventory
{
	private int metadata;
	private int rotation;
	private boolean isLighting;
	private int livingcount;

	public TileFurniture()
	{
	}

	public TileFurniture(int metadata)
	{
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

    private static final int MaxMoving = 6;

    private int openCounter = -1;
    private int closeCounter = -1;
    private double rate = (Math.PI / 2) / MaxMoving;

    private double movingRate;

    public void updateEntity()
    {
    	super.updateEntity();
    	livingcount++;

    	if(openCounter >= 0)
    	{
    		movingRate = Math.sin(rate*(MaxMoving-openCounter));
    		openCounter--;
    	} else
    	if(closeCounter >= 0)
    	{
    		movingRate = Math.cos(rate*(MaxMoving-closeCounter));
    		closeCounter--;
    	} else
    	if(usingPlayers > 0)
    	{
    		movingRate = 1D;
    	} else
    	{
    		movingRate = 0D;
    	}
    }

	public float getBlockRenderRotation(int i)
	{
		return (float)(90D*rotation);
	}

	public void onRender(int i)
	{
	}

	public void setRotation(int l)
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

	public void setFurnitureIndex(int damage)
	{
		metadata = damage;
	}

	public void setFurnitureToShop()
	{
		setFurnitureIndex(new Random().nextInt(EnumFurniture.size()));
	}

	public int getFurnitureIndex()
	{
		return metadata;
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

	private int usingPlayers;

	@Override
	public void openChest()
	{
		if(usingPlayers <= 0)
		{
			openCounter = MaxMoving;
		}
		usingPlayers++;

		AnimalCrossingChest.get().openChestAt(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public void closeChest()
	{
		usingPlayers--;
		if(usingPlayers <= 0)
		{
			closeCounter = MaxMoving;
		}

		AnimalCrossingChest.get().closeChestAt(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return AnimalCrossingChest.get().isItemValidForSlot(i, itemstack);
	}

	public double getOpeningOffset()
	{
		return -(movingRate / 2D);
	}
}
