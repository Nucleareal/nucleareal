package net.minecraft.src.nucleareal.animalcrossing.untispacingchest;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiACChest extends GuiContainer
{
	public GuiACChest(EntityPlayer player, int page, World world, int x, int y, int z)
	{
		super(new ContainerACChest(player.inventory, page));
		playerInv = player.inventory;
		xSize = 256;
		ySize = 156;
		this.page = page;
		this.player = player;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	private static final ResourceLocation texture = new ResourceLocation(
			"nc:textures/gui/container/acchest.png");
	private IInventory playerInv;
	private EntityPlayer player;
	private World world;
	private int x;
	private int y;
	private int z;

	private static final int xBegin = 196;
	private static final int yBegin = 31;

	private static final int prevIndex = 0;
	private static final int nextIndex = 1;

	private int page;
	private GuiButton prev;
	private GuiButton next;

	@Override
	public void initGui()
    {
        buttonList.clear();
        prev = new GuiButton(prevIndex,  0, 0, 16, 16, "↑");
		next = new GuiButton(nextIndex, 16, 0, 16, 16, "↓");
		buttonList.add(prev);
		buttonList.add(next);
    }

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		int swidth = fontRenderer.getStringWidth(String.valueOf(page));
		fontRenderer.drawString(String.valueOf(page), xBegin - swidth, yBegin, 0x000000);
		fontRenderer.drawString("/" + AnimalCrossingChest.get().getSizePages(), xBegin, yBegin, 0x000000);
		super.drawGuiContainerForegroundLayer(par1, par2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		drawDefaultBackground();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(texture);
		int xs = (width - xSize)/2;
		int ys = (height - ySize)/2;
		drawTexturedModalRect(xs, ys, 0, 0, xSize, ySize);
	}

	@Override
	protected void actionPerformed(GuiButton gb)
	{
		switch(gb.id)
		{
			case prevIndex: AnimalCrossingChest.get().setActivePage(page-1); break;
			case nextIndex: AnimalCrossingChest.get().setActivePage(page+1); break;
		}
		player.openGui(AnimalCrossing.instance, AnimalCrossing.FurnitureChestGuiID, world, x, y, z);
	}
}
