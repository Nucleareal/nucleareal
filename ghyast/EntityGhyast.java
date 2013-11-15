package net.minecraft.src.nucleareal.ghyast;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.world.World;

public class EntityGhyast extends EntityGhast
{
	public int state;

	public EntityGhyast(World world)
	{
		super(world);
	}

	@Override
	protected void updateEntityActionState()
    {
		attackCounter = 0;

		state++;

		super.updateEntityActionState();
    }

	protected String getLivingSound()
    {
        return worldObj.rand.nextBoolean() ? "random.chestclosed" : "random.chestopen";
    }

    protected String getHurtSound()
    {
        return "random.chestopen";
    }

    protected String getDeathSound()
    {
        return "random.chestclosed";
    }
}
