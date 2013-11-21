package net.minecraft.src.nucleareal.animalcrossing.maplesisters;

import net.minecraft.entity.passive.EntityTameable;

public enum MapleSisters
{
	Akaha,
	Kiyo,
	;

	public static MapleSisters of(EntityMapleSistersBase e)
	{
		if(e instanceof EntityMapleAkaha) return Akaha;
		if(e instanceof EntityMapleKiyo) return Kiyo;
		return null;
	}
}
