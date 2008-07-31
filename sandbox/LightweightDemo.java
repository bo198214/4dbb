package sandbox;

import java.awt.*;
import java.awt.event.*;

/*
 ***************************************************************
 * Silly Sample program which demonstrates the basic paint
 * mechanism for lightweight components in the AWT.
 ***************************************************************
 */
public class LightweightDemo {
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        Frame f = new Frame("Have a nice day!");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container panel = new StripedPanel();
        panel.add(new RoundSmileyCanvas(Color.red));
        f.add(panel, BorderLayout.CENTER);
        f.pack();
        f.show();
    }
}

/**
 * A lightweight container that renders a striped background.
 */
@SuppressWarnings("serial")
class StripedPanel extends Container {
    protected Color colors[] = {Color.red, Color.yellow, Color.blue,
                                Color.green, Color.pink, Color.orange,
                                Color.white, Color.magenta, Color.cyan};

    public StripedPanel() {
        // Container's default layout is null
        setLayout(new FlowLayout());
    }

    public void paint(Graphics g) {
        Dimension size = getSize();
        int thickness = 5;
        for (int y = 0; y + thickness <= size.height; y+=thickness) {
            g.setColor(colors[y % colors.length]);
            g.fillRect(0, y, size.width, thickness);
        }
        super.paint(g); // without this, smiley wouldn't paint...
    }
}

/**
 * A lightweight canvas which renders a smiley-face.
 * The area around the smiley face is transparent, allowing
 * whatever is behind it to show through.
 */
@SuppressWarnings("serial")
class RoundSmileyCanvas extends Component {
    protected Color faceColor;

    public RoundSmileyCanvas(Color faceColor) {
        this.faceColor = faceColor;
    }

    public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }

   /*
    * Paint when the AWT tells us to...
    */
    public void paint(Graphics g) {
        // Dynamically calculate size information
        // (the canvas may have been resized externally...)
        Dimension size = getSize();
        int d = Math.min(size.width, size.height); // diameter
        int ed = d/20; // eye diameter
        int x = (size.width - d)/2;
        int y = (size.height - d)/2;

        // draw head
        g.setColor(faceColor);
        //g.fillOval(x, y, d, d);
        g.setColor(Color.black);
        g.drawOval(x, y, d, d);

        // draw eyes
        g.fillOval(x+d/3-(ed/2), y+d/3-(ed/2), ed, ed);
        g.fillOval(x+(2*(d/3))-(ed/2), y+d/3-(ed/2), ed, ed);

        //draw mouth
        g.drawArc(x+d/4, y+2*(d/5), d/2, d/3, 0, -180);
    }
}
