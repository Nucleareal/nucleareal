package net.minecraft.src.nucleareal.animalcrossing.maplesisters.mydesign;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileFurniture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMyDesignEdit extends GuiScreen
{
	private static final ResourceLocation grid = new ResourceLocation("nc:textures/gui/container/mydesign_grid.png");
	private static final int gridguisize = 206;
	private static final int gridsize = 4;
	private static final int gridbeginX = 8;
	private static final int gridbeginY = 8;
	private static final int gridDistance = gridsize+2;

	private World world;
	private String playerName;

	public GuiMyDesignEdit(World world, EntityPlayer player)
	{
		super();
		this.world = world;
		this.playerName = player.username;
	}

	@Override
	public void initGui()
    {
		super.initGui();
        buttonList.clear();
    }

	@Override
	protected void actionPerformed(GuiButton gb)
	{
	}

	@Override
	public void onGuiClosed()
    {
		super.onGuiClosed();
	}
}
