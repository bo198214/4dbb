/**
 * 
 */
package ddddbb.game;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Objective {
	public String name;
	public int[][] goal;
	public int[][][] compounds;
	
	private Objective() {}
	
	public Objective(String _name,int[][] _goal,int[][][] _compounds) {
		goal=_goal;
		compounds =_compounds;			
		name=_name;
	}
	public Objective(File file) throws ParserConfigurationException, SAXException, IOException {
		this();
		read(file);
	}
	
	public String toString() {
		if (name!=null) { return name; }
		return super.toString();
	}
	
	private static int val(NamedNodeMap m,String name) {
		assert m != null;
		assert m.getNamedItem(name)!=null;
		assert m.getNamedItem(name).getNodeValue()!=null;
		return Integer.parseInt(m.getNamedItem(name).getNodeValue());
	}
	
	public static int[][] readCompound(Node elem) {
		NodeList nodeList = elem.getChildNodes();
		int n=0;
		for (int i=0;i<nodeList.getLength();i++) {
			Node point = nodeList.item(i);
			if (point.getNodeName().equals("point")) {
				n+=1;
			}
		}
		int[][] res = new int[n][];
		n=0;
		for (int i=0; i<nodeList.getLength();i++) {
			Node point = nodeList.item(i);
			NamedNodeMap pA = point.getAttributes();
			if (point.getNodeName().equals("point")) {
				res[n] = new int[] {
						val(pA,"x"),
						val(pA,"y"),
						val(pA,"z"),
						val(pA,"w")
				};
				n+=1;
			}
		}
		return res;
	}
	
	public void read(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		//System.out.println("Root element " + doc.getDocumentElement().getNodeName());
		assert doc.getDocumentElement().getNodeName().equals("objective") : doc.getDocumentElement().getNodeName();
		
		Node goal = doc.getElementsByTagName("goal").item(0);
		this.goal = readCompound(goal);

		Node compoundsNode = doc.getElementsByTagName("compounds").item(0);

		NodeList compounds = compoundsNode.getChildNodes();
		int n=0;
		for (int i=0;i<compounds.getLength();i++) {
			Node compound = compounds.item(i);
			if (compound.getNodeName().equals("compound")) { n+=1; }
		}
		this.compounds = new int[n][][];
		n=0;
		for (int i=0;i<compounds.getLength();i++) {
			Node compound = compounds.item(i);
			if (compound.getNodeName().equals("compound")) { 
				this.compounds[n] = readCompound(compound);
				n+=1; 
			}
		}
	}
	
	public void save(File file) throws IOException {
		FileWriter f = new FileWriter(file);
		f.write("<objective>\n");
		f.write("    <goal>\n");
		int[][] goal = this.goal;
		for (int i=0;i<goal.length;i++) {
			int[] p = goal[i];
			f.write("        <point x=\"" + p[0] + "\" y=\"" + p[1] + "\" z=\"" + p[2] + "\" w=\"" + p[3] + "\"/>\n" );
		}
		f.write("    </goal>\n");
		f.write("    <compounds>\n");
		int[][][] compounds = this.compounds;
		for (int n=0;n<compounds.length;n++) {
			f.write("        <compound>\n");
			int[][] compound = compounds[n];
			for (int i=0;i<compound.length;i++) {
				int[] p = compound[i];
				f.write("            <point x=\"" + p[0] + "\" y=\"" + p[1] + "\" z=\"" + p[2] + "\" w=\"" + p[3] + "\"/>\n" );
			}
			f.write("        </compound>\n");
		}
		f.write("    </compounds>\n");
		f.write("</objective>\n");
		f.close();
	}

}