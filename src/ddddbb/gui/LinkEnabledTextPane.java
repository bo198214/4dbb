package ddddbb.gui;

import java.awt.Container;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import ddddbb.Applet;

@SuppressWarnings("serial")
public class LinkEnabledTextPane extends JPanel {
	protected final JTextPane tp;
	protected final JScrollPane sp;
	
	public LinkEnabledTextPane(URL document,final Container window) {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		tp = new JTextPane();
		tp.setEditable(false);
		HyperlinkListener listener = new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (window instanceof Applet) {
						((Applet)window).getAppletContext().showDocument(e.getURL(), "_blank");
					}
					else {
						showDocument(e.getURL());
					}
//					try {
//						tp.setPage(e.getURL());
//					} catch (IOException ioe) {
//						System.err.println("Error loading: " + ioe);
//					}
				}
			}
		};
		tp.addHyperlinkListener(listener);
		try {
			tp.setPage(document);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sp = new JScrollPane();
		sp.setViewportView(tp);
		add(sp);
	}

	private static Object  getBasicServiceObject ( )	{
	  try  {
	    Class  serviceManagerClass = Class.forName ( "javax.jnlp.ServiceManager" );
	    Method  lookupMethod = serviceManagerClass.getMethod (
	      "lookup", new Class [ ] { String.class } );

	    return lookupMethod.invoke (
	      null, new Object [ ] { "javax.jnlp.BasicService" } );
	  }

	  catch ( Exception  ex )	  {
	    return null;
	  }
	}
	
	public static boolean  showDocument ( URL  url )	{
	  Object basicServiceObject = getBasicServiceObject();
	  Class basicServiceClass = basicServiceObject.getClass();
	  
	  if ( basicServiceObject == null )	  {   return false;  }
	  try	  {
	    Method  method = basicServiceClass.getMethod ("showDocument", new Class[] { URL.class } );
	    Boolean  resultBoolean = (Boolean) method.invoke (
	      basicServiceObject, new Object[] { url } );
	    return resultBoolean.booleanValue ( );
	  }
	  catch ( Exception  ex ) {
	    ex.printStackTrace ( );
	    throw new RuntimeException ( ex.getMessage ( ) );
	  }
	}
}
