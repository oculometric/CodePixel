package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.util.ListIterator;

public class CodePixelPanel extends JPanel {	
	boolean isFirstUpdate = true;
	Pixel singleToRefresh;
	
	CodePixelWindow parent;
	
	public CodePixelPanel (CodePixelWindow cp) {
		super ();
		parent = cp;
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		if (isFirstUpdate) {
			g.setColor(Color.white);
			g.fillRect (0, 0, parent.frameSize, parent.frameSize);
			isFirstUpdate = false;
		}
		if (parent.shouldRefreshInstantly) {
			paintPixel (g, singleToRefresh);
			singleToRefresh = null;
		} else {
//			g.setColor(Color.black);
//			g.fillRect (0, 0, parent.frameSize, parent.frameSize);
//			isFirstUpdate = false;
//			ListIterator<Pixel> it = pixels.listIterator();
//			while (it.hasNext()) {
//				Pixel p = it.next();
//				paintPixel (g, p);
//			}
		}
	}

	public boolean pixelExists (int x, int y) {
		for (Pixel p : pixels) {
			if (p.x == x && p.y == y)
				return true;
		}
		return false;
	}
	
	public void paintPixel (Graphics g, Pixel p) {
		//System.out.println(p);
		if (p == null)
			return;
		int brightness = 255 - (int)((float)p.remainingLifetime/(float)parent.lifetimeLength*255.0);
		//rgbNum = 100; 
		
		float[] hsb = new float[3];
		Color.RGBtoHSB(brightness, brightness, brightness, hsb);
		Color c = Color.getHSBColor(p.tint, hsb[1], hsb[2]);
		//System.out.println(c);
		Color cc = new Color (c.getRGB()); /*new Color (brightness, brightness, brightness);*/
		g.setColor(cc);
		g.fillRect((p.x * parent.pixelSize) + parent.frameSize/2, (p.y * parent.pixelSize) + parent.frameSize/2, parent.pixelSize, parent.pixelSize);
	}
	
	public ArrayList<Pixel> pixels = new ArrayList<Pixel> ();
}