package net.minecraft.src.nucleareal;

public class ObjectPair<T1, T2>
{
	private T1 v1;
	private T2 v2;

	public ObjectPair(T1 o1, T2 o2)
	{
		v1 = o1;
		v2 = o2;
	}

	public T1 getV1()
	{
		return v1;
	}

	public T2 getV2()
	{
		return v2;
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof ObjectPair && equals((ObjectPair<T1, T2>)obj);
	}

	public boolean equals(ObjectPair<T1, T2> obj)
	{
		return getV1().equals(obj.getV1()) && getV2().equals(obj.getV2());
	}
}
