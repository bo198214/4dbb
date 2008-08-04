package ddddbb.gen;


public interface SelArray<T> {

	public abstract int selInt();

	public abstract void setSelInt(int i);

	public abstract void setSel(T anObject);

	public abstract T sel();

}