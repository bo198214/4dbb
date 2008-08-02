package sandbox;

public class Main {
	final Huhu attribute;
	final Huhu attribute2;
	
	public Main() {
		attribute = new Huhu(this);
		attribute2 = attribute;
	}
}

class Huhu {
	public Huhu(Main m) {
		m.attribute2.doSomething();
	}
	public void doSomething() {
		
	}
}
