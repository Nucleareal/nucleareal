package net.minecraft.src.nucleareal.animalcrossing;

public enum FishingRodMaterial
{
	Gold(8, 24),
	;

	public static String[] Names_enUS = new String[]
			{ "Gold", };
	public static String[] Names_jaJP = new String[]
			{ "金", };

	private int maxUse;
	private int enchantibility;

	private FishingRodMaterial(int maxUse, int enchantibility)
	{
		this.maxUse = maxUse;
		this.enchantibility = enchantibility;
	}

	public int getMaxUse()
	{
		return maxUse;
	}

	public int getEnchantability()
	{
		return enchantibility;
	}
}
