package net.minecraft.src.nucleareal.animalcrossing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.nucleareal.UtilMinecraft;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemColorableFlower;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import static net.minecraft.src.nucleareal.animalcrossing.RoseColor.*;

public class PickupHandler
{
	private static String pickUpRoseKey = "PickUpFlowerOfColor:";

	@ForgeSubscribe
	public void onPickup(EntityItemPickupEvent event)
	{
		ItemStack ist = event.item.getEntityItem();
		if(ist != null)
		{
			EntityPlayer p = event.entityPlayer;
			Item pickup = ist.getItem();
			int meta = ist.getItemDamage();

			if(pickup instanceof ItemColorableFlower)
			{
				p.triggerAchievement(Achievements.pickUpRose);
				int pickupcount = AnimalCrossing.NBT.incleaseValue(pickUpRoseKey);
				if(pickupcount > 1000) p.triggerAchievement(Achievements.pickUpManyRose);

				AnimalCrossing.NBT.incleaseValue(pickUpRoseKey+meta);

				RoseColor color = RoseColor.of(meta);
				switch(color)
				{
					default: break;
					case Red:
					case Yellow:
					case White:
					{
						int r = AnimalCrossing.NBT.getIntValueSafely(pickUpRoseKey+Red.ordinal());
						int y = AnimalCrossing.NBT.getIntValueSafely(pickUpRoseKey+Yellow.ordinal());
						int w = AnimalCrossing.NBT.getIntValueSafely(pickUpRoseKey+White.ordinal());
						if(r > 0 && y > 0 && w > 0)
						{
							p.triggerAchievement(Achievements.pickUpBaseRoses);
						}
						break;
					}
					case Pink:
					case Orange:
					case Black:
					case Purple:
					{
						p.triggerAchievement(Achievements.pickUpRareRose); break;
					}
					case Blue:
					{
						p.triggerAchievement(Achievements.pickUpBlueRose); break;
					}
					case Gold:
					{
						p.triggerAchievement(Achievements.pickUpGoldRose);
						p.addChatMessage("Picked up gold rose.");
					}
				}
			}
		}
	}
}
