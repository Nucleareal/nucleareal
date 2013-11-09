package net.minecraft.src.nucleareal;

import net.minecraft.util.Tuple;

public class ObjectTriple<T1, T2, T3>
{
	private T1 v1;
	private T2 v2;
	private T3 v3;

	public ObjectTriple(T1 o1, T2 o2, T3 o3)
	{
		v1 = o1;
		v2 = o2;
		v3 = o3;
	}

	public T1 getV1()
	{
		return v1;
	}

	public T2 getV2()
	{
		return v2;
	}

	public T3 getV3()
	{
		return v3;
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof ObjectTriple && equals((ObjectTriple<T1, T2, T3>)obj);
	}

	public boolean equals(ObjectTriple<T1, T2, T3> obj)
	{
		return getV1().equals(obj.getV1()) && getV2().equals(obj.getV2()) && getV3().equals(obj.getV3());
	}
}
