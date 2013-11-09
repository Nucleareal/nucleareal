package net.minecraft.src.nucleareal;

public class Position
{
	private double x;
	private double y;
	private double z;
	private Direction direction;

	public Position(double x, double y, double z, Direction direction)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
	}

	public Position(double x, double y, double z)
	{
		this(x, y, z, Direction.Nothing);
	}

	public Position moveWith(Direction dir)
	{
		return dir.move(this);
	}

	public Position moveWith(int mx, int my, int mz, Direction dir)
	{
		return new Position(x + mx, y + my, z + mz, dir);
	}

	public double getDistanceTo(Position other)
	{
		double dx = other.getX() - x;
		double dy = other.getY() - y;
		double dz = other.getZ() - z;
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	public int[] getConvertToIntArray()
	{
		return new int[] { (int)getX(), (int)getY(), (int)getZ() };
	}

	public String getVisualityValue()
	{
		return String.format("%d,%d,%d", (int)x, (int)y, (int)z);
	}

	public static Position from(String spos)
	{
		Position res = null;
		try
		{
			String[] strs = spos.split(",");
			Integer x = Integer.valueOf(strs[0]);
			Integer y = Integer.valueOf(strs[1]);
			Integer z = Integer.valueOf(strs[2]);
			res = new Position(x, y, z);
		}
		catch(Exception e)
		{
			System.out.println("Failed Create Position From: "+spos);
		}
		return res;
	}
}
