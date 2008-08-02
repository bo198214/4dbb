package ddddbb.gen;

public abstract class AChangeListener implements MyChangeListener {
	/** performs stateChanged() (to initialize) and then adds this
	 * MyChangeListener to m.
	 */
	public void addTo(Model m) {
		stateChanged();
		m.addChangeListener(this);
	}
}
