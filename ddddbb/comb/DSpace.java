package ddddbb.comb;

//this is a quick hack
public class DSpace extends DLocation {
	public DSpace(DLocation dloc) {
		super(dloc);
		//origin has to be orthogonal to spat/span
		for (int i=0;i<dloc.dim();i++) {
			origin[spat[i]] = 0;
		}
	}
}
