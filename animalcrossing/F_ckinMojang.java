package net.minecraft.src.nucleareal.animalcrossing;

public enum F_ckinMojang
{
	DestroyingBlockX("partiallyDestroyedBlockX", "field_73086_f"),
	DestroyingBlockY("partiallyDestroyedBlockY", "field_73087_g"),
	DestroyingBlockZ("partiallyDestroyedBlockZ", "field_73099_h"),
	DestroyingBlock("isDestroyingBlock", "field_73088_d"),
	OneShotStats("oneShotStats", "field_75942_a"),
	;

	private String debugName;
	private String runName;

	private F_ckinMojang(String dName, String rName)
	{
		debugName = dName;
		runName = rName;
	}

	public String getFieldName()
	{
		return AnimalCrossing.DEBUG ? debugName : runName;
	}
}
