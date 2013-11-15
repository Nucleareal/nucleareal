package net.minecraft.src.nucleareal.animalcrossing;

public enum BalloonType
{
	Normal,
	Silver(true, 0xC0C0C0)
	{
		@Override
		public float getLife()
		{
			return 2F;
		}
	},
	Gold(true, 0xFFD700)
	{
		public float getLife()
		{
			return 3F;
		}
	},
	;

	private boolean hasEffect;
	private int color;

	private BalloonType()
	{
		this(false, 0xFFFFFF);
	}

	private BalloonType(boolean hasEffect, int color)
	{
		this.hasEffect = hasEffect;
		this.color = color;
	}

	public boolean hasEffect()
	{
		return hasEffect;
	}

	public int getColor()
	{
		return color;
	}

	public float getLife()
	{
		return 1F;
	}
}
