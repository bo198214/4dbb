package ddddbb.gen;

public abstract class AChangeListener implements MyChangeListener {
	public void addTo(Model m) {
		stateChanged();
		m.addChangeListener(this);
	}
}
