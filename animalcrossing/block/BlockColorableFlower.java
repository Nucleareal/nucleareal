package net.minecraft.src.nucleareal.animalcrossing.block;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.nucleareal.Direction;
import net.minecraft.src.nucleareal.ObjectPair;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.ReplaceablePair;
import net.minecraft.src.nucleareal.UtilConvert;
import net.minecraft.src.nucleareal.UtilDirection;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.UtilWorld;
import net.minecraft.src.nucleareal.animalcrossing.AnimalCrossing;
import net.minecraft.src.nucleareal.animalcrossing.Achievements;
import net.minecraft.src.nucleareal.animalcrossing.FlowerTable;
import net.minecraft.src.nucleareal.animalcrossing.RoseColor;
import net.minecraft.src.nucleareal.animalcrossing.UtilFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.UtilRose;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockColorableFlower extends BlockFlower
{
	@SideOnly(Side.CLIENT)
    public Icon IconBase;
	public Icon IconMulti;

	private static final Random rand = new Random();

	public BlockColorableFlower(int par1)
	{
		super(par1);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		setCreativeTab(AnimalCrossing.AnimalCrossingTab);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
    {
		super.updateTick(world, x, y, z, rand);

		if(isWilted(world, x, y, z))
		{
			updateWiltedFlower(world, x, y, z, rand);
		}
		else
		{
			updateLivingFlower(world, x, y, z, rand);
		}
    }

	private void updateLivingFlower(World world, int x, int y, int z, Random rand)
	{
		if(checkWilt(world, x, y, z, rand))
		{
			world.setBlock(x, y, z, AnimalCrossing.RoseWither.blockID, world.getBlockMetadata(x, y, z), 0x7);
		}
		else
		{
			spawnNewFlower(world, x, y, z, rand);
		}
	}

	@Override
	public int quantityDropped(Random par1Random)
    {
		return blockID == AnimalCrossing.Rose.blockID ? 1 : 0;
    }

	private static int[][] addtions = new int[][]
		{
			{-1, +0}, {+1, +0}, {+0, -1}, {+0, +1},
			{-2, +0}, {+2, +0}, {+0, -2}, {+0, +2},
			{-2, -2}, {-2, +2}, {+2, -2}, {+2, +2},
			{-4, +0}, {+4, +0}, {+0, -4}, {+0, +4},
			{-3, -3}, {-3, +3}, {+3, -3}, {+3, +3},
		};

	private boolean checkWilt(World world, int x, int y, int z, Random rand)
	{
		return checkAroundNoWaters(world, x, y, z) && rand.nextInt(AnimalCrossing.RoseWiltChance) == 0;
	}

	private boolean checkAroundNoWaters(World world, int x, int y, int z)
	{
		for(int[] adder : addtions)
		{
			if(Material.water.equals(world.getBlockMaterial(x+adder[0], y-1, z+adder[1])))
			{
				return false;
			}
		}
		return true;
	}

	private void spawnNewFlower(World world, int x, int y, int z, Random rand)
	{
		EnumSet<Direction> set = UtilDirection.getHorizonalAroundMultiDirections();

		Position now = new Position(x, y, z);
		Position newPos = null;

		List<ObjectPair<Position, Position>> list = new ArrayList<ObjectPair<Position, Position>>();

		for(Direction dir : set)
		{
			if(UtilWorld.isSameBlockIDs(world, now, now.moveWith(dir)) && rand.nextInt(AnimalCrossing.RoseSpawnChance) == 0 && isGlowableBrightness(world, x, y, z) && null != (newPos = getGenAvailablePosition(world, now, set)))
			{
				list.add(new ObjectPair<Position, Position>(now.moveWith(dir), newPos));
			}
		}

		if(list.size() > 0)
		{
			ObjectPair<Position, Position> pop = list.get(rand.nextInt(list.size()));

			RoseColor r1 = RoseColor.of(UtilWorld.getBlockMetadata(world, now));
			RoseColor r2 = RoseColor.of(UtilWorld.getBlockMetadata(world, pop.getV1()));
			ReplaceablePair<Integer> pair = new ReplaceablePair<Integer>(Integer.valueOf(r1.ordinal()), Integer.valueOf(r2.ordinal()));
			List<RoseColor> nextList = FlowerTable.get().getGenList(r1, r2);

			if(nextList.size() <= 0) return;

			RoseColor next = UtilRose.getInRandomRose(nextList, rand);
			UtilWorld.setBlockWithMetadata(world, pop.getV2(), blockID, next.ordinal());

			if(!world.isRemote && next.isSpawnXP())
			{
				EntityXPOrb entity = new EntityXPOrb(world, x+.5D, y+.5D, z+.5D, 10);
				world.spawnEntityInWorld(entity);
			}
		}
	}

	private void updateWiltedFlower(World world, int x, int y, int z, Random rand)
	{
		if(!checkAroundNoWaters(world, x, y, z) || world.isRaining())
		{
			respawn(world, x, y, z, AnimalCrossing.Rose.blockID);
		}

		if(rand.nextInt(AnimalCrossing.RoseDeadChance) != 0) return;

		int meta = world.getBlockMetadata(x, y, z);

		//UtilFloatingBalloon.spawnAt(world, x, y + 4, z);

		for(int i = 0; i < 16; i++)
		{
			double d0 = (double)((float)x + rand.nextFloat());
	        double d1 = (double)((float)y + rand.nextFloat());
	        double d2 = (double)((float)z + rand.nextFloat());
	        double d3 = 0.0D;
	        double d4 = -.01D;
	        double d5 = 0.0D;
	        EntityFX entity = (new EntityExplodeFX(world, d0, d1, d2, d3, d4, d5));
	        float[] colors = UtilConvert.toRGBF(RoseColor.of(meta).getColor());
	        entity.setRBGColorF(colors[0], colors[1], colors[2]);
	        UtilMinecraft.get().effectRenderer.addEffect(entity);
		}
		world.setBlockToAir(x, y, z);
	}

	private boolean isWilted(World world, int x, int y, int z)
	{
		return isWilted(world.getBlockId(x, y, z));
	}

	private boolean isWilted(int ID)
	{
		return AnimalCrossing.Rose.blockID != ID;
	}

	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		super.randomDisplayTick(world, x, y, z, rand);
		if(!isWilted(world, x, y, z))
		{
			int meta = world.getBlockMetadata(x, y, z);
			if(!RoseColor.of(meta).hasBlink()) return;

			for (int l = 0; l < 4; ++l)
	        {
	            double d0 = (double)((float)x + rand.nextFloat());
	            double d1 = (double)((float)y + rand.nextFloat());
	            double d2 = (double)((float)z + rand.nextFloat());
	            double d3 = 0.0D;
	            double d4 = -.01D;
	            double d5 = 0.0D;
	            EntityFX entity = (new EntitySmokeFX(world, d0, d1, d2, d3, d4, d5));
	            float[] colors = UtilConvert.toRGBF(RoseColor.of(meta).getColor());
	            entity.setRBGColorF(colors[0], colors[1], colors[2]);
	            UtilMinecraft.get().effectRenderer.addEffect(entity);
	        }
		}
		else if(rand.nextInt(8) == 0 && world.isRaining())
		{
			respawn(world, x, y, z, AnimalCrossing.Rose.blockID);
		}
	}

	public boolean canBlockStay(World world, int x, int y, int z)
    {
		return super.canBlockStay(world, x, y, z) && world.getFullBlockLightValue(x, y, z) >= 4 && world.canBlockSeeTheSky(x, y, z);
    }

	private boolean isGlowableBrightness(World world, int x, int y, int z)
	{
		return world.getFullBlockLightValue(x, y, z) > 12;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
		int result = super.getLightValue(world, x, y, z);
		if(blockID == AnimalCrossing.Rose.blockID)
		{
			result = RoseColor.of(world.getBlockMetadata(x, y, z)).getLightValue();
		}
        return result;
    }

	private Position getGenAvailablePosition(World world, Position now, EnumSet<Direction> set)
	{
		for(Direction dir : set)
		{
			Position mov = now.moveWith(dir);
			if(		UtilWorld.isAirBlock(world, mov) &&
					UtilWorld.getBlockMaterial(world, mov.moveWith(Direction.YNeg)).equals(Material.grass) &&
					canBlockStay(world, (int)mov.getX(), (int)mov.getY(), (int)mov.getZ()))
			{
				return mov;
			}
		}
		return null;
	}

	public int getRenderType()
    {
		return AnimalCrossing.RoseRenderID;
    }

	public static int RenderLayer = 0;

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
    	return getByRenderLayer(RenderLayer);
    }

    public Icon getByRenderLayer(int layer)
    {
    	if(layer == 1) return IconMulti; else return IconBase;
    }

    public static void setRenderLayer(int param)
    {
    	RenderLayer = param;
    }

    @Override
    public int damageDropped(int damage)
    {
    	return damage;
    }

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
	}

    public void registerIcons(IconRegister par1IconRegister)
    {
    	IconBase = par1IconRegister.registerIcon(getTextureName() + "1");
    	IconMulti = par1IconRegister.registerIcon(getTextureName() + "2");
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
    	int meta = world.getBlockMetadata(x, y, z);

    	if(entity instanceof EntityPlayer)
    	{
    		EntityPlayer player = (EntityPlayer)entity;
    		if(player.isSprinting())
    		{
    			tryToDespawn(world, x, y, z, meta);
    		}
    	}
    	if(!isWilted(world, x, y, z) && entity instanceof EntityLivingBase)
    	{
    		EntityLivingBase ev = (EntityLivingBase)entity;
    		if(RoseColor.of(meta).hasCollisionDamage())
    		{
    			ev.attackEntityFrom(DamageSource.generic, RoseColor.of(meta).getDamageValue());
    		}
    	}
    }

    private void tryToDespawn(World world, int x, int y, int z, int meta)
	{
    	if(rand.nextInt(AnimalCrossing.SprintingDespawnChance) == 0)
    	{
    		UtilMinecraft.get().sndManager.playSound(soundClothFootstep.getBreakSound(), (float)x+.5F, (float)y+.5F, (float)z+.5F, 1F, 1F);
    		for(int i = 0; i < 24; i++)
    		{
    			Double r1 = world.rand.nextDouble();
    			Double r2 = world.rand.nextDouble();
    			Double r3 = world.rand.nextDouble();
    			EntityFX entity = (new EntityDiggingFX(world, x+.5D+r1, y+.25D+r2, z+.5D+r3, 0, r2, 0, this, meta)).applyRenderColor(RoseColor.of(meta).getColor());
    			UtilMinecraft.get().effectRenderer.addEffect((EntityFX)entity);
    		}
    		world.setBlockToAir(x, y, z);

    		EntityPlayer player = UtilMinecraft.getWorldAndPlayer("").getV2();

    		player.triggerAchievement(Achievements.breakOutRose);
    		int breaked = AnimalCrossing.NBT.incleaseValue("BreakOutRose:");
    		int breakedM = AnimalCrossing.NBT.incleaseValue("BreakOutRose:"+meta);
    		if(breaked > 100)
    		{
    			player.triggerAchievement(Achievements.breakOutManyRose);
    		}
    	}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    public int getMultiplierColor(int layer, int metadata)
    {
    	if(layer == 1)
    	{
    		return RoseColor.of(metadata).getColor();
    	}
    	else
    	{
    		return RoseColor.of(metadata).getPlantColor();
    	}
    }

    @Override
    public int getRenderColor(int damage)
    {
    	return RoseColor.of(damage).getColor();
    }

    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return RoseColor.of(world.getBlockMetadata(x, y, z)).getColor();
    }

	public void respawn(World world, int x, int y, int z, int ID)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if(RoseColor.of(meta).isEvolutionToGold() && world.rand.nextInt(AnimalCrossing.BlackToGoldChance) == 0)
		{
			world.setBlock(x, y, z, ID, RoseColor.Gold.ordinal(), 0x7);
		}
		else
		{
			world.setBlock(x, y, z, ID, meta, 0x7);
		}
	}
}
