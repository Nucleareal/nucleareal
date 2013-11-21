package net.minecraft.src.nucleareal.animalcrossing.inukichi;

public class AreaData
{
	public AreaData(int x, int y, int z, int sx, int sy, int sz, InukichiValue v, boolean isStair)
	{
		this(x, y, z, sx, sy, sz, v.getMeta(), isStair);
	}

	public AreaData(int x, int y, int z, int sx, int sy, int sz, int meta, boolean isStair)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.sx = sx;
		this.sy = sy;
		this.sz = sz;
		this.meta = meta;
		this.isStair = isStair;
	}

	public int x;
	public int y;
	public int z;
	public int sx;
	public int sy;
	public int sz;
	public int meta;
	public boolean isStair;
}
