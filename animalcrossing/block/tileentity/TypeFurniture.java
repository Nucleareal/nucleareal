package net.minecraft.src.nucleareal.animalcrossing.block.tileentity;

import static net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TypeAtmosphere.Calm;
import static net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TypeAtmosphere.Old;
import static net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TypeColor.Black;
import static net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TypeColor.White;

import java.util.Arrays;
import java.util.List;

public enum TypeFurniture
{
	Mono(Arrays.asList(Calm, Old), Arrays.asList(White, Black)),
	;

	private TypeAtmosphere[] _atms;
	private TypeColor[] _colors;

	private TypeFurniture(List<TypeAtmosphere> atms, List<TypeColor> colors)
	{
		_atms = atms.toArray(new TypeAtmosphere[0]);
		_colors = colors.toArray(new TypeColor[0]);
	}
}
