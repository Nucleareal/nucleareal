package net.minecraft.src.nucleareal.animalcrossing.block.tileentity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.src.nucleareal.ModelNuclearealBase;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.ModelMonoChest;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.ModelMonoDesk;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.ModelMonoLamp;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.ModelMonoLowTable;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.ModelMonoSofa;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.ModelMonoTable;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.ModelMonoWallClock;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.IBlockProxy;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.LogicMonoChest;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.LogicMonoDesk;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.LogicMonoLamp;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.LogicMonoLowTable;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.LogicMonoSofa;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.LogicMonoTable;
import net.minecraft.src.nucleareal.animalcrossing.furnituremodel.logic.LogicMonoWallClock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public enum EnumFurniture
{
	MonoTable("Mono Table", new ModelMonoTable(), new LogicMonoTable(), TypeFurniture.Mono),
	MonoDesk("Mono Desk", new ModelMonoDesk(), new LogicMonoDesk(), TypeFurniture.Mono),
	MonoWallClock("Mono WallClock", new ModelMonoWallClock(), new LogicMonoWallClock(), TypeFurniture.Mono),
	MonoLamp("Mono Lamp", new ModelMonoLamp(), new LogicMonoLamp(), TypeFurniture.Mono),
	MonoLowTable("Mono LowTable", new ModelMonoLowTable(), new LogicMonoLowTable(), TypeFurniture.Mono),
	MonoSofa("Mono Sofa", new ModelMonoSofa(), new LogicMonoSofa(), TypeFurniture.Mono),
	MonoChest("Mono Chest", new ModelMonoChest(), new LogicMonoChest(), TypeFurniture.Mono),
	;

	private static EnumFurniture[] instance;
	private String furnitureName;
	private IBlockProxy furniturelogic;
	private ObjectPair<ResourceLocation, ModelNuclearealBase> renderSource;
	private static ResourceLocation defalutLocation = new ResourceLocation("nc:B_Furniture");
	private TypeFurniture type;

	static
	{
		instance = values();
	}

	private EnumFurniture(String fname, ModelNuclearealBase model, IBlockProxy furniturelogic, TypeFurniture type)
	{
		furnitureName = fname;
		renderSource = new ObjectPair<ResourceLocation, ModelNuclearealBase>(new ResourceLocation(getTextureLocation()), model);
		this.furniturelogic = furniturelogic;
		this.type = type;
	}

	public static EnumFurniture of(int i)
	{
		return instance[i];
	}

	private static void init()
	{
		instance = values();
	}

	public static int size()
	{
		return instance.length;
	}

	public String getFurnitureName()
	{
		return furnitureName;
	}

	public String getConvertedFurnitureName()
	{
		return getFurnitureName().replace(" ", "_");
	}

	private String getTextureLocation()
	{
		return "nc:textures/entity/furniture/Furniture_"+getConvertedFurnitureName()+".png";
	}

	public ResourceLocation getTexture()
	{
		return renderSource.getV1();
	}

	public ModelNuclearealBase getModel()
	{
		return renderSource.getV2();
	}

	public EnumRarity getRarity()
	{
		return furniturelogic.getRarity();
	}

	public boolean canStayInThisPosition(World world, int x, int y, int z)
	{
		return furniturelogic.canStayInThisPosition(world, x, y, z);
	}

	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return furniturelogic.getLightValue(world, x, y, z);
	}

	public void setBlockBounds(Block block, IBlockAccess world, int x, int y, int z)
	{
		furniturelogic.setBlockBounds(block, world, x, y, z);
	}

	public int getRenderCount()
	{
		return furniturelogic.getRenderCount();
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z,ForgeDirection side)
	{
		return furniturelogic.isBlockSolidOnSide(world, x, y, z, side);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz)
	{
		return furniturelogic.onBlockActivated(world, x, y, z, player, i, fx, fy, fz);
	}

	public float getSubtractYParam()
	{
		return furniturelogic.getSubtractYParam();
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int side)
	{
		furniturelogic.onNeighborBlockChange(world, x, y, z, side);
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		furniturelogic.onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity, int layer)
	{
		furniturelogic.addCollisionBoxesToList(world, x, y, z, axis, list, entity, layer);
	}

	public int getCollisionBoxesCount()
	{
		return furniturelogic.getCollisionBoxesCount();
	}

	public int getValueBuy()
	{
		return 1000;
	}
}
