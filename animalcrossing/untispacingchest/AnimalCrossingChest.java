package net.minecraft.src.nucleareal.animalcrossing.untispacingchest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.world.World;

public class AnimalCrossingChest implements IInventory
{
	private AnimalCrossingChest()
	{
	}

	private static final AnimalCrossingChest instance = new AnimalCrossingChest();

	{
		init();
	}

	public static AnimalCrossingChest get()
	{
		return instance;
	}

	private void init()
	{
		contents = new ItemStack[getSizePages()][getSizePage()];
	}

	private ItemStack[][] contents;
	private int page;

	public int getSizePages()
	{
		return 16;
	}

	public int getSizePage()
	{
		return 10;
	}

	@Override
	public int getSizeInventory()
	{
		return getSizePage() * getSizePages();
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return contents[i / getSizePage()][i % getSizePage()];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		getStackInSlot(i).stackSize -= j;
		check(i);
		return getStackInSlot(i);
	}

	private void check(int i)
	{
		ItemStack ist = getStackInSlot(i);
		if (ist != null && ist.stackSize <= 0)
		{
			setInventorySlotContents(i, null);
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		ItemStack ist = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return ist;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack ist)
	{
		setInventorySlotContents(i / getSizePage(), i % getSizePage(), ist);
	}

	public void setInventorySlotContents(int page, int slot, ItemStack ist)
	{
		contents[page][slot] = ist;
	}

	@Override
	public String getInvName()
	{
		return "";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public void onInventoryChanged()
	{
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return true;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	public void openChestAt(World world, int x, int y, int z)
	{
		readFromNBT();

		world = UtilMinecraft.getWorldAndPlayer("").getV1();

		world.playSoundEffect(x + .5D, y + .5D, z + .5D, "random.chestopen", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		world.playSoundEffect(x + .5D, y + .5D, z + .5D, "tile.piston.out", 0.5F, world.rand.nextFloat() * 0.25F + 0.6F);

	}

	public void closeChestAt(World world, int x, int y, int z)
	{
		writeToNBT();
		setActivePage(0);

		world = UtilMinecraft.getWorldAndPlayer("").getV1();

		world.playSoundEffect(x + .5D, y + .5D, z + .5D, "random.chestclosed", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		world.playSoundEffect(x + .5D, y + .5D, z + .5D, "tile.piston.in", 0.5F, world.rand.nextFloat() * 0.15F + 0.6F);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	public void readFromNBT()
	{
		NBTTagCompound NBT = AnimalCrossing.NBT.getTagCompound();
		NBTTagList tags = NBT.getTagList("AC:ChestItems");
		init();

		for (int i = 0; i < tags.tagCount(); ++i)
		{
			NBTTagCompound itemtag = (NBTTagCompound) tags.tagAt(i);
			int page = itemtag.getInteger("Page");
			int slot = itemtag.getInteger("Slot");
			setInventorySlotContents(page, slot, ItemStack.loadItemStackFromNBT(itemtag));
		}
	}

	public void writeToNBT()
	{
		NBTTagCompound NBT = AnimalCrossing.NBT.getTagCompound();
		NBTTagList tags = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); i++)
		{
			ItemStack ist = getStackInSlot(i);
			if (ist != null)
			{
				NBTTagCompound itemtag = new NBTTagCompound();
				itemtag.setInteger("Page", getPageFromIndex(i));
				itemtag.setInteger("Slot", getSlotFromIndex(i));
				ist.writeToNBT(itemtag);
				tags.appendTag(itemtag);
			}
		}
		NBT.setTag("AC:ChestItems", tags);
	}

	private int getPageFromIndex(int i)
	{
		return i / getSizePage();
	}

	private int getSlotFromIndex(int i)
	{
		return i % getSizePage();
	}

	private int getSplitterCount()
	{
		return 2;
	}

	public int getYCount()
	{
		return getSplitterCount();
	}

	public int getXCount()
	{
		return getSizePage() / getSplitterCount();
	}

	public void setActivePage(int page)
	{
		if (page < 0) page += getSizePages();
		this.page = page % getSizePages();
	}

	public int getActivePage()
	{
		return page;
	}
}
