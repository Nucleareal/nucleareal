package net.minecraft.src.nucleareal.animalcrossing;

import java.util.EnumSet;

public enum RoseColor
{
	Red(0xFF0000, 0),
	White(0xFFFFFF, 0),
	Yellow(0xFFFF00, 0),
	Pink(0xFF00FF, 1),
	Orange(0xFF7F00, 1),
	Purple(0x7F00FF, 1),
	Black(0x2F0F0F, 1)
	{
		@Override
		public boolean isEvolutionToGold()
		{
			return true;
		}
	},
	Blue(0x0000FF, 2),
	Gold(0xE6B422, 3, true, 13)
	{
		@Override
		public int getPlantColor()
		{
			return getColor();
		}
	},
	Tin(0xDFDFEF, 2),
	Silver(0xC0C0C0, 3, true)
	{
		@Override
		public int getPlantColor()
		{
			return getColor();
		}
	},
	Iron(0xD7D7D7, 2),
	Uranium(0x7FFF7F, 2, 11)
	{
		@Override
		public float getDamageValue()
		{
			return 4F;
		}
	},
	Diamond(0x7FDFBF, 4, true, 3)
	{
		@Override
		public int getPlantColor()
		{
			return getColor();
		}

		@Override
		public float getDamageValue()
		{
			return 1F;
		}
	},
	RedStone(0xBF0000, 3, 7),
	Copper(0x8C4841, 2, true)
	{
		@Override
		public int getPlantColor()
		{
			return getColor();
		}
	};

	private int color;
	private int Rarity;
	private int Light;
	private boolean hasEffect;

	public static final String[] RoseColorNames = new String[]
			{ "Red", "White", "Yellow", "Pink", "Orange", "Purple", "Black", "Blue", "Gold", "Tin", "Silver", "Iron", "Uranium", "Diamond", "RedStone", "Copper"};
	public static final String[] RoseColorNames_jaJP = new String[]
			{ "赤", "白", "黄色", "桃", "橙", "紫", "黒", "青", "金", "Tin", "銀", "鉄", "ウラン", "ダイヤ", "赤石", "銅"};

	public static EnumSet<RoseColor> getAllColors()
	{
		return EnumSet.of(Red, White, Yellow, Pink, Orange, Purple, Black, Blue, Gold, Tin, Silver, Iron, Uranium, Diamond, RedStone, Copper);
	}

	public static EnumSet<RoseColor> getCanGenerateTypes()
	{
		return EnumSet.of(Red, White, Yellow);
	}

	private RoseColor(int color, int Rarity)
	{
		this(color, Rarity, false, 0);
	}

	private RoseColor(int color, int Rarity, boolean hasEffect)
	{
		this(color, Rarity, hasEffect, 0);
	}

	private RoseColor(int color, int Rarity, int LightValue)
	{
		this(color, Rarity, false, LightValue);
	}

	private RoseColor(int color, int Rarity, boolean isEffect, int LightValue)
	{
		this.color = color;
		this.Rarity = Rarity;
		hasEffect = isEffect;
		Light = LightValue;
	}

	public boolean isSpawnXP()
	{
		return getRarity() >= Blue.getRarity();
	}

	public int getLightValue()
	{
		return Light;
	}

	public int getColor()
	{
		return color;
	}

	public int getRarity()
	{
		return Rarity;
	}

	public boolean hasEffect()
	{
		return hasEffect;
	}

	public static RoseColor of(int num)
	{
		return arr[num % arr.length];
	}

	private static RoseColor[] arr = getAllColors().toArray(new RoseColor[0]);

	public boolean hasBlink()
	{
		return ordinal() >= Gold.ordinal();
	}

	public int getPlantColor()
	{
		return 0x007F00;
	}

	public static int convertRoseToCloth(int meta)
	{
		switch(meta)
		{
			default: return -1;
			case 0 : return 15-1;
			case 1 : return 15-15;
			case 2 : return 15-11;
			case 3 : return 15-9;
			case 4 : return 15-14;
			case 5 : return 15-5;
			case 6 : return 15-0;
			case 7 : return 15-4;
		}
	}

	public boolean isEvolutionToGold()
	{
		return false;
	}

	public boolean hasCollisionDamage()
	{
		return getDamageValue() != 0;
	}

	public float getDamageValue()
	{
		return 0F;
	}
}
