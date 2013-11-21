package net.minecraft.src.nucleareal.animalcrossing.inukichi.sell;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.MoneyManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiSellItems extends GuiContainer
{
	public GuiSellItems(EntityPlayer player)
	{
		super(new ContainerSellItems(player.inventory));
		sellInv = player.inventory;
		isSell = new boolean[verticalcount][horizonalcount];
		xSize = 194;
		ySize = 132;
		ply = player;
	}

	private static final ResourceLocation texture = new ResourceLocation("nc:textures/gui/container/sellitems.png");

	private EntityPlayer ply;
	private IInventory sellInv;
	private static final int btnbeginid = 100;
	private int btncount;

	private GuiButton[] verticals;
	private static final int verticalcount = 4;
	private GuiButton[] horizonals;
	private static final int horizonalcount = 9;
	private GuiButton all;
	private GuiButton accept;

	private int xs;
	private int ys;

	private boolean[][] isSell;

	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.clear();
		xs = (width - xSize) / 2;
		ys = (height - ySize) / 2;


		accept = new GuiButton(0, xs+151, ys+107, 36, 18, "Sell");
		buttonList.add(accept);


		btncount = btnbeginid - 1;

		all = new GuiButton(btncount++, xs+7, ys+17, 18, 18, "R");
		buttonList.add(all);

		verticals = new GuiButton[verticalcount];
		for (int i = 0; i < verticalcount; i++)
		{
			verticals[i] = new GuiButton(btncount++, xs + 7, ys + 35 +18*i, 18, 18, "R");
			buttonList.add(verticals[i]);
		}
		horizonals = new GuiButton[horizonalcount];
		for(int i = 0; i < horizonalcount; i++)
		{
			horizonals[i] = new GuiButton(btncount++, xs+25+18*i, ys+17, 18, 18, "R");
			buttonList.add(horizonals[i]);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j)
	{
		/*String str = String.valueOf(page + 1);
		int swidth = fontRenderer.getStringWidth(str);
		fontRenderer.drawString(str, xBegin - swidth, yBegin, 0x000000);
		fontRenderer.drawString("/" + AnimalCrossingChest.get().getSizePages(), xBegin, yBegin, 0x000000);
		super.drawGuiContainerForegroundLayer(par1, par2);*/
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(xs, ys, 0, 0, xSize, ySize);

		GL11.glColor4f(1F, 1F, 1F, .5F);
		mc.getTextureManager().bindTexture(texture);
		for(int y = 0; y < verticalcount; y++)
		{
			for(int x = 0; x < horizonalcount; x++)
			{
				drawTexturedModalRect(xs+26+x*18, ys+36+y*18, (isSell[y][x] ? 1 : 0)*16, 132, 16, 16);
			}
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int button)
    {
		int rx = x - xs - 25;
		int ry = y - ys - 35;

		if(rx < 0 || 18*horizonalcount <= rx || ry < 0 || 18*verticalcount <= ry)
		{
			super.mouseClicked(x, y, button);
			return;
		}
		else
		{
			int vx = rx / 18;
			int vy = ry / 18;
			isSell[vy][vx] = !isSell[vy][vx];
		}
    }

	@Override
	protected void actionPerformed(GuiButton gb)
	{
		int id = gb.id;

		if(id == 0)
		{
			int amount = 0;

			ply = UtilMinecraft.getWorldAndPlayer(ply.username).getV2();
			sellInv = ply.inventory;

			for(int y = 0; y < verticalcount; y++)
			{
				for(int x = 0; x < horizonalcount; x++)
				{
					if(isSell[y][x])
					{
						ItemStack ist = sellInv.getStackInSlot(y*horizonalcount+x);

						//TODO Replace value "100"
						if(ist != null)
						{
							amount += 100 * ist.stackSize;

							sellInv.setInventorySlotContents(y*horizonalcount+x, null);
						}
					}
				}
			}

			MoneyManager.get().addMoney(amount);

			onGuiClosed();
		}
		else
		if(id < btnbeginid)
		{
			for(int y = 0; y < verticalcount; y++)
			{
				for(int x = 0; x < horizonalcount; x++)
				{
					isSell[y][x] = !isSell[y][x];
				}
			}
			//All
		}
		else
		if(id < btnbeginid+verticalcount)
		{
			int line = id - btnbeginid;
			//vertical
			for(int i = 0; i < horizonalcount; i++)
			{
				isSell[line][i] = !isSell[line][i];
			}
		}
		else
		{
			int line = id - btnbeginid - verticalcount;
			//horizonal
			for(int i = 0; i < verticalcount; i++)
			{
				isSell[i][line] = !isSell[i][line];
			}
		}
	}
}
