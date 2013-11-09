package net.minecraft.src.nucleareal;

public enum Direction
{
	XNeg(-1, 0, 0),
	XPos(+1, 0, 0),
	ZNeg(0, 0, -1),
	ZPos(0, 0, +1),
	YNeg(0, -1, 0),
	YPos(0, +1, 0),
	XNegZNeg(-1, 0, -1),
	XNegZPos(-1, 0, +1),
	XPosZNeg(+1, 0, -1),
	XPosZPos(+1, 0, +1),
	Nothing(0, 0, 0);

	private Direction(int dx, int dy, int dz)
	{
		mx = dx;
		my = dy;
		mz = dz;
	}

	public Position move(Position pos)
	{
		return pos.moveWith(mx, my, mz, this);
	}

	private int mx, my, mz;
}
