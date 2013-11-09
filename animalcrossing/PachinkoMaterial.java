package net.minecraft.src.nucleareal.animalcrossing;

public enum PachinkoMaterial
{
	Gold(8, 22);

	public static String[] Names_enUS = new String[]
			{ "Gold" };
	public static String[] Names_jaJP = new String[]
			{ "金" };

	private int maxUse;
	private int enchantablity;

	private PachinkoMaterial(int maxUse, int enchantablity)
	{
		this.maxUse = maxUse;
		this.enchantablity = enchantablity;
	}

	public int getMaxUse()
	{
		return maxUse;
	}

	public int getEnchantability()
	{
		return enchantablity;
	}
}
