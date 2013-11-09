package net.minecraft.src.nucleareal;

public class ReplaceablePair<T>
{
	private T v1;
	private T v2;

	public ReplaceablePair(T o1, T o2)
	{
		v1 = o1;
		v2 = o2;
	}

	public T getV1()
	{
		return v1;
	}

	public T getV2()
	{
		return v2;
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof ReplaceablePair && equals((ReplaceablePair<T>)obj);
	}

	public boolean equals(ReplaceablePair<T> pair)
	{
		boolean flag = getV1().equals(pair.getV1()) && getV2().equals(pair.getV2());
		flag |= getV1().equals(pair.getV2()) && getV2().equals(pair.getV1());
		return flag;
	}

	public ReplaceablePair<T> reverse()
	{
		return new ReplaceablePair<T>(getV2(), getV1());
	}
}
