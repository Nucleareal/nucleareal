package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.entity.Entity;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingChest;
import net.minecraft.world.World;

public class UtilFloatingBalloon
{

	public static void spawnAt(World world, int x, int y, int z)
	{
		double[] cossin = getMoveAddtions(world);

		EntityFloatingChest chest = new EntityFloatingChest(world, x, y, z, cossin[0], cossin[1]);;
		EntityFloatingBalloon balloon = new EntityFloatingBalloon(world, x+.5D, y+.5D, z+.5D, chest);
		chest.setPosition(x+.5D, y+.5D, z+.5D);
		balloon.setPosition(x+.5D, y+.5D, z+.5D);
		world.spawnEntityInWorld(chest);
		world.spawnEntityInWorld(balloon);
	}

	public static double[] getMoveAddtions(World world)
	{
		double t = (Math.PI * 2D) * (world.rand.nextDouble());
		double rate = .03D;
		double cos = Math.cos(t) * rate;
		double sin = Math.sin(t) * rate;
		return new double[]{ cos, sin };
	}

	public static double[] getPositionAddtions(World world)
	{
		double t = (Math.PI * 2D) * (world.rand.nextDouble());
		double rate = 64D;
		double cos = Math.cos(t) * rate;
		double sin = Math.sin(t) * rate;
		return new double[] { cos, sin };
	}

	public static void spawnToDirectionalEntity(World world, Entity entity)
	{
		double[] posadd = getPositionAddtions(world);
		double sx = entity.posX + posadd[0];
		double sy = entity.posY + 8D;
		double sz = entity.posZ + posadd[1];
		double t = Math.atan2(entity.posZ-sz, entity.posX-sx);
		double rate = .03D + (.025D * world.difficultySetting);
		double dx = Math.cos(t) * rate;
		double dz = Math.sin(t) * rate;
		EntityFloatingChest chest = new EntityFloatingChest(world, sx, sy, sz, dx, dz);
		EntityFloatingBalloon balloon = new EntityFloatingBalloon(world, sx+.5D, sy+.5D, sz+.5D, chest);
		chest.setPosition(sx+.5D, sy+.5D, sz+.5D);
		balloon.setPosition(sx+.5D, sy+.5D, sz+.5D);
		world.spawnEntityInWorld(chest);
		world.spawnEntityInWorld(balloon);
	}

}
